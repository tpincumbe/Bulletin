package infosec.bulletin.board;

import infosec.bulletin.io.FileIOManager;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FileIOActivity extends Activity{
	
	private FileIOManager io_manager;
	
	public void onCreate(Bundle savedInstaceState){
		super.onCreate(savedInstaceState);
		
		setContentView(R.layout.activity_fileio);
		Button writeButton = (Button)findViewById(R.id.write_button);
		Button listButton = (Button)findViewById(R.id.list_button);
		final TextView fileListView = (TextView)findViewById(R.id.file_list_viewer);
		
		Bundle extras = getIntent().getExtras();
		String accountName;
		if(extras !=null)
		{
			accountName = extras.getString("accountName");
		}else
		{
			accountName = "NoAccountsAvailable";
		}
		this.io_manager = new FileIOManager(accountName);
		
		writeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					io_manager.saveToExternal();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					fileListView.setText("Exception: " + e.getMessage());
					return;
				}
				fileListView.setText("Auto-gen File saved to :" + io_manager.printWorkingDirectory());
			}
		});
		
		listButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String fileList;
				try {
					fileList = io_manager.listUserFiles();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					fileListView.setText("Exception: " + e.getMessage());
					return;
				}
				fileListView.setText(fileList);
			}
		});
		
	}

}
