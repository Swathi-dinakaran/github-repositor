import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter {
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	private static final String FILE_HEADER = "id,firstName,lastName,gender,age";

	public static void main(String args[]) {
		
		
		
		
		FileWriter fileWriter = null;
				
		try {
			fileWriter = new FileWriter("csvfile.csv");

			fileWriter.append(FILE_HEADER.toString());
			
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			
				fileWriter.append("1, Ahmed, Mohamed, M, 25\n");
				fileWriter.append("2, Sara, Said, F, 23\n");
				fileWriter.append("3, Ali, Hassan, M, 24\n");
				fileWriter.append("4, Sama, Karim, F, 20\n");
				fileWriter.append("5, Khaled, Mohamed, M, 22\n");
				fileWriter.append("6, Ghada, Sarhan, F, 21\n");
				fileWriter.append("2, Sara, Said, F, 23\n");
				fileWriter.append("4, Sama, Karim, F, 20\n");
				fileWriter.append("6, Ghada, Sarhan, F, 21\n");


			
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
}