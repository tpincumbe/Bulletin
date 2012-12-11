package infosec.bulletin.board;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * 
 */
public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		ImageButton newNote = (ImageButton) findViewById(R.id.newNote);
		ListView notes = (ListView) findViewById(R.id.notes);
		
		newNote.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(Main.this, NewNote.class);
				startActivity(i);
			}
		});
		
//		final AccountManager am = AccountManager.get(this); // "this" references the current Context
//		
//		dummyB.setOnClickListener(new OnClickListener(){
//			public void onClick(View v) {				
//				Account[] accounts = am.getAccounts();
//				String accountDescs = accounts.length + " Accounts: ";
//				for(Account a:accounts)
//					accountDescs += a.toString() + ", ";
//				
//				dummyT.setText(accountDescs);
//			}
//		});
	}
}
