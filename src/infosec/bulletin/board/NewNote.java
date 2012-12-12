package infosec.bulletin.board;

import java.io.IOException;

import infosec.bulletin.io.FileIOManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class NewNote extends Activity {

	private FileIOManager io_manager;
	private TextView noteField;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_note);
		
		Button cancel = (Button) findViewById(R.id.cancel);
		Button save = (Button) findViewById(R.id.save);
		this.noteField = (TextView) findViewById(R.id.note);
		
		Bundle extras = getIntent().getExtras();
		String accountName;
		if(extras !=null)
		{
			accountName = extras.getString("accountName");
		}else
		{
			accountName = "Unknown";
		}
		this.io_manager = new FileIOManager(accountName);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String message = noteField.getText().toString();
				Toast toastSuccess = Toast.makeText(getApplicationContext(), "Note has been saved.", Toast.LENGTH_SHORT);
				toastSuccess.show();
				finish();
				try {
					io_manager.saveToExternal(message);
				} catch (IOException e) {
					Toast toastFail = Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT);
					toastFail.show();					
				}
			}
		});
	}
}
