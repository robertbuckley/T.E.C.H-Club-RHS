package buckley.robert.tigertech;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class ResearchSubmit extends AppCompatActivity {
    EditText editUrl;
    Button youtubeSubmit;
    Firebase fire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_submit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialize();
    }
    public void initialize(){
        setTitle(R.string.app_name);
        editUrl = (EditText) findViewById(R.id.editUrl);
        youtubeSubmit = (Button) findViewById(R.id.youtubeSubmit);
        fire = new Firebase("https://tigertechurls.firebaseio.com/");
    }
    public void youtubeSubmitted(View view){
        try {
            fire.push().setValue(editUrl.getText().toString());
            Toast.makeText(this, "Thank you!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Research.class));
        }
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Could not submit", Toast.LENGTH_LONG).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_splash, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.request:
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"robertbuckley@ridgefieldps.net"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Tiger Tech Creation Request");
                ResearchSubmit.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                break;
            case R.id.information:
                AlertDialog ad = new AlertDialog.Builder(ResearchSubmit.this).setMessage(R.string.lorum).setTitle("Information").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create();
                ad.show();
                break;
            case R.id.mission:
                AlertDialog ad0 = new AlertDialog.Builder(ResearchSubmit.this).setMessage(R.string.lorum).setTitle("Mission").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create();
                ad0.show();
                break;
        }
        return true;
    }
}
