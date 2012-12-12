package infosec.bulletin.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import android.os.Environment;

public class FileIOManager {

	public final String NotePath = "bulletin-data";
	public final String fileExtension = ".bulletin";
	public final String signature = "\n---\n Message sent on: ";

	private String accountName;

	private File externalPath = Environment.getExternalStorageDirectory();
	
	public FileIOManager(String accountName) {
		this.accountName = accountName;
	}

	public void saveToExternal() throws IOException{
		saveToExternal("This is an auto-generated file by application Bulletin");
	}
	public void saveToExternal(String message) throws IOException {
		if(!isExternalWritable())
		{
			throw new IOException("External Storage is either not available or not writable. State: " + Environment.getExternalStorageState());
		}
		java.util.Date date = new java.util.Date();
		long timestamp = date.getTime();
		String dateTime = date.toString();
		File stub = new File(printWorkingDirectory() + "/" + timestamp + fileExtension);
		
		FileOutputStream out = new FileOutputStream(stub);
		out.write((accountName + " says:\n" + message + signature + dateTime).getBytes());
		out.close();
	}

	public String[] listUserFiles() throws IOException {
		if(!isExternalReadable()){
			throw new IOException("External Storage is either not available or not readable. State: " + Environment.getExternalStorageState());
		}
		String[] fileArray = externalPath.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String filename) {
				return filename.toLowerCase().endsWith(".bulletin");
			}
		});
		
		for(int i=0; i<fileArray.length; i++){
			fileArray[i] = printWorkingDirectory() + "/" + fileArray[i];
		}
		return fileArray;
	}
	
	public void deleteFiles(String[] fileList)
	{
		for(String f:fileList)
		{
			File file = new File(f);
			file.delete();
		}
	}
	
	public String getFileContents(String path) throws Exception
	{
		String contents = "";
		File f = new File(path);
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		
		while((line=br.readLine()) != null)
		{
			contents += line + "\n"; 
		}
		
		return contents;
	}

	public String printWorkingDirectory() {
		return externalPath.getAbsolutePath();
	}
	
	public boolean isExternalWritable()
	{
		String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);
	}
	
	public boolean isExternalReadable()
	{
		String state = Environment.getExternalStorageState();
		return (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state) || Environment.MEDIA_MOUNTED.equals(state)); 
	}
}
