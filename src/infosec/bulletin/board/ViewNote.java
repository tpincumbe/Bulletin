package infosec.bulletin.board;

import java.io.IOException;

import infosec.bulletin.io.FileIOManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewNote extends Activity {
	private int noteIndex = 0;
	private String[] fileList;
	private FileIOManager io_manager;
	private TextView notesView, indexView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_notes);
		Button next = (Button) findViewById(R.id.next_button);
		Button prev = (Button) findViewById(R.id.prev_button);
		this.notesView = (TextView) findViewById(R.id.notes_view);
		this.indexView = (TextView) findViewById(R.id.note_index);

		Bundle extras = getIntent().getExtras();
		String accountName = "Unknown";
		if (extras != null) {
			accountName = extras.getString("accountName");
		}
		this.io_manager = new FileIOManager(accountName);
		displayNote();
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				noteIndex++;
				if (noteIndex >= fileList.length)
					noteIndex = fileList.length - 1;
				displayNote();
			}
		});

		prev.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				noteIndex--;
				if (noteIndex < 0)
					noteIndex = 0;
				displayNote();
			}
		});

	}

	private void displayNote() {
		if ((fileList == null) || (fileList.length == 0)) {
			notesView.setText("Fetching Notes..");
			try {
				
				this.fileList = io_manager.listUserFiles();
				reverseArray();
			} catch (IOException e) {
				notesView.setText("Exception: " + e.toString());
				return;
			}
		}
		this.indexView.setText((noteIndex + 1) + "/" + fileList.length);
		try {
			notesView.setText(io_manager
					.getFileContents(fileList[this.noteIndex]));
		} catch (Exception e) {
			notesView.setText(e.toString());
		}

	}
	
	private void reverseArray()
	{
		String temp;
		for(int i = 0; i < fileList.length/2; i++)
		{
			temp = fileList[i];
			fileList[i] = fileList[fileList.length -1];
			fileList[fileList.length -1] = temp; 
		}
	}
}
