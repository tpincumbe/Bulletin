package infosec.bulletin.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.accounts.Account;
import android.os.Environment;

public class FileIOManager {

	public final String NotePath = "bulletin-data";
	public final String StubFileName = "-bulletin.txt";

	public static java.util.Date date = new java.util.Date();

	private String accountName;

	private File externalPath = Environment.getExternalStorageDirectory();

	public FileIOManager(String accountName) {
		this.accountName = accountName;
	}

	public void saveToExternal() throws IOException {
		if(!isExternalWritable())
		{
			throw new IOException("External Storage is either not available or not writable. State: " + Environment.getExternalStorageState());
		}
		File stub = new File(printWorkingDirectory() + "/" + accountName + "-" + date.getTime() + StubFileName);
		
		FileOutputStream out = new FileOutputStream(stub);
		out.write("This is an auto-generated file by application Bulletin".getBytes());
		out.close();
	}

	public String listUserFiles() throws IOException {
		if(!isExternalReadable()){
			throw new IOException("External Storage is either not available or not readable. State: " + Environment.getExternalStorageState());
		}
		String[] fileArray = externalPath.list();
		String fileList = "";

		for (String c : fileArray) {
			fileList += printWorkingDirectory() + "/" + c + "\n";
		}
		return fileList;
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
