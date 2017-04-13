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
import com.chargebee.framework.api.InputParam;
import com.chargebee.framework.autoui.AutoUIWrap;
import com.chargebee.framework.autoui.EditUIComp;
import com.chargebee.framework.jooq.JQRecord;
import com.chargebee.framework.metamodel.Column;
import com.chargebee.framework.metamodel.DateCol;
import com.chargebee.framework.metamodel.EnumColumn;
import com.chargebee.framework.metamodel.HttpURLCol;
import com.chargebee.framework.metamodel.StringCol;
import com.chargebee.framework.metamodel.TimestampCol;
import com.chargebee.framework.pojo.PojoList;
import static com.chargebee.framework.util.GlobalUtil.csv;
import com.chargebee.framework.web.Forward;
import java.io.File;
import java.sql.SQLException;
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
static int index=0;
//Column col;
List<InputParam> values2=new ArrayList<InputParam>();
//InputParam get1;
public List<String> header=new ArrayList<String>();
public List<String> values1=new ArrayList<String>();
    private void fn() throws SQLException, Exception {
        ApiURLConfig c=ApiStore.configs.get("import_a_subscription");
        values2 = c._inputParams.values();//inputparams not in order
        //System.out.println(values2);//comment
        //get1 = values2.get(6);//comment
        //col=get1.col;
        //get1.col.
        //FilterDataType.Dtype filterType = get1.getFilterType(col);
        //String name = filterType.displayName();
        //String paramName = get1.getParamName();
        //get1.type
        //String name1 = valueOf.name();
        //String apiTypeDefn = col.
        //System.out.println();System.out.println();System.out.println();
        //System.out.println(paramName);
        //System.out.println(filterType);
        //System.out.println(filterType);
        //System.out.println(apiTypeDefn);
        
        //MimeType type = get1.col.getType();
        //ColumnType columnType = (ColumnType)get1.col.getColumnType();
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
        index++;
    }
    //public EditUIComp insertCustomFields() throws Exception{
    public String insertCustomFields() throws Exception{
        //CustomFieldConfig cfc = CreateCfConfigurations.createConfig(EntityType.subscription, "apiname", "dispname", EditUIComp.TEXT, "dbcolname", ColumnType.String, null, false);
        //return CFUIHelper.genEditDiv(EntityType.subscription,null);
        //EntityType n=new EntityType();
        //c=values2.get(1);
        //ColumnType fct = cfc.fieldDatatype_e();
        List<AutoUIWrap> cols = new ArrayList();
        int i=0;
        //JQRecord rec = CFRequestHelper._fetchCustomFields(entityId,enity.table(), enity);
        for(InputParam get1:values2)
        {
            if(header.contains(get1.getParamName())){
            System.out.println(get1);
            System.out.println(cols.size());
            System.out.println(header.size());
            cols.add(createAutoUIWrap(get1,i));i++;}
        }
       //new EnumColumn().getEntriesForCurrApi();
        return AutoUi.addCustomElem(cols);
    }
    private AutoUIWrap createAutoUIWrap(InputParam get1,int i) throws Exception{
        
        AutoUIWrap w = new AutoUIWrap();
        w.col = get1.col;
        w.displayName = get1.getParamName();
        w.paramName = get1.getParamName();
        w.value=values1.get(i);
        //w.editVal(values1.get(i));
        w.comp = getEditUI(w.col,get1);
        if (w.comp.equals(EditUIComp.DROP_DOWN)) {
            EnumColumn e=(EnumColumn)get1.col;
         if(e instanceof EnumColumn)
           w.allowedVals=e.entryVals();
        }
        return w;
    }
       
       
    /*
    //String(100,"StringCol",CFBaseType.STRING_TYPE,EditUIComp.TEXT),
    Long(200,"LongCol",CFBaseType.LONG_TYPE,EditUIComp.TEXT),        
    Double(300,"DoubleCol",CFBaseType.DOUBLE_TYPE,EditUIComp.TEXT),        
    Timestamp(400,"TimestampCol",CFBaseType.TIME_STAMP,EditUIComp.DATE_TIME_FIELD),
    Email(500,"EmailColumn",CFBaseType.STRING_TYPE,EditUIComp.TEXT),
    //URL(700,"HttpURLCol",CFBaseType.STRING_TYPE,EditUIComp.TEXT_AREA),
    Date(600,"DateCol",CFBaseType.DATE_TYPE,EditUIComp.DATE_FIELD);
        /*String jsonArrStr = FileUtils.readFileToString(new File(args[1]));
                CreateCfCSonfigurations inst = new CreateCfConfigurations();
                List<CustomFieldConfig> configs = inst.createFields(jsonArrStr, false);
                for (CustomFieldConfig cfc : configs) {
                    System.out.println("\nCreated custom field: " + csv(cfc.displayName(), cfc.apiName(),
                            cfc.dbColName()));
                }*/

    private EditUIComp getEditUI(Column col,InputParam get1) {
        if(col.allowedVals != null||col instanceof EnumColumn){
            System.out.println(col.displayName);
            return EditUIComp.DROP_DOWN;
        }else if(get1.col instanceof StringCol||get1.col instanceof HttpURLCol){
            return (col.getColSize() < 100)? EditUIComp.TEXT:EditUIComp.TEXT_AREA;
        }else if(get1.col instanceof TimestampCol){
            return EditUIComp.DATE_TIME_FIELD;
        }else if(get1.col instanceof DateCol){
            return EditUIComp.DATE_FIELD;
        }else{
            return EditUIComp.TEXT;
        }
    }
          
}

