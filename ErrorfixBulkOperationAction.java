/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */
package com.chargebee.app.bulk_operations.actions;

import com.chargebee.app.models.*;
import java.util.*;

import static com.chargebee.app.models.Tables.*;
import com.chargebee.app.AppActionModel;
import com.chargebee.app.AppActionMeta;
import com.chargebee.app.bulk_operations.helpers.meta.SheetMeta;

import com.chargebee.app.configweb.Tabs.*;

import static com.chargebee.framework.web.HttpMethod.*;
import static com.chargebee.app.config.AppRole.*;
import com.chargebee.app.config.CFBaseType;
import com.chargebee.app.config.ColumnType;
import com.chargebee.app.config.EntityType;
import com.chargebee.app.custom_fields.CFRequestHelper;
import com.chargebee.app.custom_fields.CFSiteConfig;
import com.chargebee.app.custom_fields.CFUIHelper;
import static com.chargebee.app.custom_fields.CFUIHelper.getEditUI;
import com.chargebee.app.custom_fields.CFWrapper;
import com.chargebee.app.custom_fields.CreateCfConfigurations;
import static com.chargebee.app.merchants.helpers.MerchantSiteCfUtil.signedupPlan;
import com.chargebee.app.models.base.InvoiceNoteBase;
import com.chargebee.app.tpls.AutoUi;
import com.chargebee.components.table.filter.FilterDataType;
import com.chargebee.framework.api.ApiStore;
import com.chargebee.framework.api.ApiURLConfig;
import com.chargebee.framework.api.ApiVersion;
import com.chargebee.framework.api.InputParam;
import com.chargebee.framework.api.RequestWrapper;
import com.chargebee.framework.autoui.AutoUIWrap;
import com.chargebee.framework.autoui.EditUIComp;
import com.chargebee.framework.gen.JavaGenUtil;
import com.chargebee.framework.jooq.JQColumn;
import com.chargebee.framework.jooq.JQRecord;
import com.chargebee.framework.metamodel.BooleanCol;
import com.chargebee.framework.metamodel.Column;
import com.chargebee.framework.metamodel.DateCol;
import com.chargebee.framework.metamodel.EnumColumn;
import com.chargebee.framework.metamodel.FileCol;
import com.chargebee.framework.metamodel.HttpURLCol;
import com.chargebee.framework.metamodel.NumberCol;
import com.chargebee.framework.metamodel.StringCol;
import com.chargebee.framework.metamodel.TextCol;
import com.chargebee.framework.metamodel.TimestampCol;
import com.chargebee.framework.pojo.PojoList;
import com.chargebee.framework.util.DateFmts;
import static com.chargebee.framework.util.GlobalUtil.csv;
import com.chargebee.framework.web.FormatterUtil;
import com.chargebee.framework.web.Forward;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import javax.activation.MimeType;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ErrorfixBulkOperationAction extends AppActionModel<ErrorfixBulkOperationAction>{
    void _defn(AppActionMeta m)
    {
        m.$method(get).$roles(USER);
        m.$tab(MainTabs.SETTINGS, SettingsTabs.SETTINGS);        
    }
MigrationLog migLog;
public PojoList<OperationErrorLog> oelist;
  @Override
    public Forward callLogic() throws Exception {
        migLog = get(qmigration_logs);
        long miglogid=6;
        oelist=qoperation_error_logs.dbFetchAll(qoperation_error_logs.migration_log_id.equal(miglogid));
        fn();
        return super.callLogic();
    }
static int index=1;//error record number;
List<InputParam> values2=new ArrayList<InputParam>();
public List<String> header=new ArrayList<String>();
public List<String> values1=new ArrayList<String>();
    private void fn() throws SQLException, Exception {
        ApiURLConfig c = ((ApiURLConfig)ApiStore.configs.getIfPresent("import_a_subscription", ApiVersion.V2));
        
            OperationErrorLog oel=oelist.get(index);
            JSONObject errorRecord= oel.errorListObj();
            JSONArray data=errorRecord.getJSONArray("data");
            int i=1;
            while(i<data.length())
            {
                JSONObject col=data.getJSONObject(i);
                header.add(col.getString("col_name"));
                values1.add(col.getString("col_val"));
                i++;
            }
            
            List<InputParam> values3 = c.inputParams();//inputparams not in order
            values2.addAll(values3);
            for(InputParam p:values3)
            {
                int indexOf = header.indexOf(p.getParamName());
                if(header.contains(p.getParamName())||header.contains("subscription["+p.getParamName()+"]")){
                    if(indexOf==-1)
                        indexOf=header.indexOf("subscription["+p.getParamName()+"]");
                    values2.set(indexOf, p);
                    if(values2.get(values3.indexOf(p))==p)
                         values2.set(values3.indexOf(p), null);
                }
                else{
                    int flag=0;
                    for (String header1 : header) {     
                        String index3 = RequestWrapper.parseIndex(p.getParamName(),header1);
                                if(index3!=null){
                                    indexOf=header.indexOf(header1);
                                    values2.set(indexOf, p);
                                    p.getParamName();
                                    flag++;
                                }
                    }
                    if(flag==0)
                        values2.set(values3.indexOf(p), null);
                }
            }
            
        index++;
    }
    
    public String insertCustomFields() throws Exception{
        /*InputParam get1=values2.get(index);
        AutoUIWrap w = new AutoUIWrap();
        w.col = get1.col;
        w.displayName =header.get(index);  
        w.paramName = header.get(index);
        w.helpText = get1.col.getDesc();
        if (get1.col.allowedVals != null) {
            w.allowedVals = get1.col.allowedVals;
        } else if (get1.col instanceof EnumColumn) {
            w.allowedVals = ((EnumColumn) get1.col).entryVals();
        } 
        return AutoUi.genEditComp(w);*/
        List<AutoUIWrap> cols = new ArrayList();
        for (InputParam get1 : values2)
        if(get1!=null)
        {
            int index1;
            index1=header.indexOf(get1.getParamName());
            if(index1==-1)
                index1=header.indexOf("subscription["+get1.getParamName()+"]");
            if(index1==-1){
                for (String header1 : header) {
                    String index3 = RequestWrapper.parseIndex(get1.getParamName(),header1);
                    if(index3!=null){
                        index1=header.indexOf(header1);
                        int flag=0;
                        for (AutoUIWrap col : cols) 
                            if(header1.equals(col.paramName))
                                flag=1;
                        if(flag==1){continue;}
                        AutoUIWrap wrap = createAutoUIWrap(get1.col,index1);
                        cols.add(wrap);
                        break;
                    }
                }
            }
            else
            {
                AutoUIWrap wrap = createAutoUIWrap(get1.col,index1);
                cols.add(wrap);
            }
        }
        return AutoUi.addCustomElem(cols);
    }
    
    private AutoUIWrap createAutoUIWrap(Column c,int i) throws Exception{
        AutoUIWrap w = new AutoUIWrap();
        w.col = c;
        w.displayName =header.get(i);  
        w.paramName = header.get(i);
        w.helpText = c.getDesc();
        if (c.allowedVals != null) {
            w.allowedVals = c.allowedVals;
        } else if (c instanceof EnumColumn) {
            w.allowedVals = ((EnumColumn) c).entryVals();
        } 
        getEditUI(c,w,i);
        
        return w;
    }
       
       
    

    private void getEditUI(Column col,AutoUIWrap w,int index) throws ParseException {
        if (w.allowedVals != null) {
            w.comp=EditUIComp.DROP_DOWN;
            if(!values1.get(index).isEmpty())
                w.value=values1.get(index);
        }else if (col instanceof TimestampCol) {
            w.comp = EditUIComp.DATE_TIME_FIELD;
            if(!values1.get(index).isEmpty())
                w.value=new Date(DateFmts.YYYY_MM_DD_HH_MM_SS.get().parse(values1.get(index)).getTime());
            } else if (col instanceof BooleanCol) {
            w.comp = ((BooleanCol) col).getDispComp() != null ? EditUIComp.valueOf(((BooleanCol) col).getDispComp()) : EditUIComp.RADIO_BUTTON_HORIZONTAL;
            if(!values1.get(index).isEmpty())
                w.value=((values1.get(index)!=null)?((values1.get(index)=="true")?true:false):null);
        } else if(col instanceof FileCol) {
                w.comp = EditUIComp.FILE;        
        } else if (col instanceof TextCol || (col instanceof StringCol && col.getColSize() > 100)) {
            w.comp = EditUIComp.TEXT_AREA;
            if(!values1.get(index).isEmpty())
                w.value=values1.get(index);
        } else if(col instanceof StringCol && ((StringCol)col).isPassword){
            w.comp=EditUIComp.PASSWORD;
        }else if (col instanceof NumberCol) {
            w.comp = EditUIComp.TEXT;
            if(!values1.get(index).isEmpty())
                w.value=Integer.parseInt(values1.get(index));
        }
        else{
            w.comp = EditUIComp.TEXT;
            if(!values1.get(index).isEmpty())
                w.value=values1.get(index);
        }
    }
}

