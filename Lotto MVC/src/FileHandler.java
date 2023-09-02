import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
	private String fileName = "lottrad.txt";
	
	
	public String openFile() {
		String content = "";
		 
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(fileName) ) );
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
        return content;
		
	}
	
	public void saveFile(String text) {
		try(FileWriter fileWriter = new FileWriter(fileName)) {
		    String fileContent = text;
		    fileWriter.write(fileContent);
		    fileWriter.close();
		    System.out.println("Sparade Följande Lottrader \n" + text);
		} catch (IOException e) {
		    // Cxception handling
		}
	}
	
	public static void main(String[] args) {
	}

}
