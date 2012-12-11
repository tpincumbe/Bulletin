package infosec.bulletin.board;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

public class NewNote extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_note);
		
		Button cancel = (Button) findViewById(R.id.cancel);
		Button save = (Button) findViewById(R.id.save);
		TextView noteField = (TextView) findViewById(R.id.note);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		});
	}
}
