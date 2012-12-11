package infosec.bulletin.board;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 */
public class Main extends Activity {
	
	public static String currentAccount = "NoAccountsAvailable";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		Button dummyB = (Button) findViewById(R.id.dummy_button);
		Button fileIOButton = (Button) findViewById(R.id.fileIO_button);
		
		final TextView dummyT = (TextView) findViewById(R.id.fullscreen_content);
		final AccountManager am = AccountManager.get(this); // "this" references the current Context		
		
		dummyB.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {				
				Account[] accounts = am.getAccounts();
				String accountDescs = accounts.length + " Accounts: ";
				for(Account a:accounts)
					accountDescs += a.toString() + ", ";
				
				dummyT.setText(accountDescs);
			}
		});
		
		
		//Begin code to pull current account (not exactly vital. but would be good to have)
		
		//End
				
		fileIOButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), FileIOActivity.class);
				i.putExtra("accountName", currentAccount);
				startActivity(i);
			}
		});
		
	}
}
