package infosec.bulletin.board;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 */
public class Main extends Activity {
	public static String currentAccount = "Unknown";
	private EditText userAccountView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		ImageButton newNote = (ImageButton) findViewById(R.id.newNote);
		ListView notes = (ListView) findViewById(R.id.notes);
		Button fileIOButton = (Button) findViewById(R.id.fileIO_button);
		Button viewNotesButton = (Button) findViewById(R.id.view_notes_button);
		
		newNote.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				if(checkAccountName())
				{
					Intent i = new Intent(Main.this, NewNote.class);
					i.putExtra("accountName", currentAccount );
					startActivity(i);
				}else
				{
					getUserAccount();
				}				
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
		
		fileIOButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(checkAccountName()){
					Intent i = new Intent(getApplicationContext(), FileIOActivity.class);
					i.putExtra("accountName", currentAccount);
					startActivity(i);
				}else
				{
					getUserAccount();
				}
			}
		});
		viewNotesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(checkAccountName())
				{
					Intent i = new Intent(getApplicationContext(), ViewNote.class);
					i.putExtra("accountName", currentAccount);
					startActivity(i);
				}else
				{
					getUserAccount();
				}				
			}
		});
	}
	
	private boolean checkAccountName()
	{
		if(currentAccount.equals(null))
			return false;
		if(currentAccount.equalsIgnoreCase("Unknown"))
			return false;
		if(currentAccount.equalsIgnoreCase(""))
			return false;
		return true;
	}
	
	private void getUserAccount()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		userAccountView = new EditText(this);
		builder.setTitle("Specify Username");
		builder.setMessage("Please specify your username before proceeding");
		builder.setView(userAccountView);
		builder.setNeutralButton("Save", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				currentAccount = userAccountView.getText().toString();
				userAccountView = null;
			}
		});
		AlertDialog d = builder.create();
		d.show();
		
		
	}
}
