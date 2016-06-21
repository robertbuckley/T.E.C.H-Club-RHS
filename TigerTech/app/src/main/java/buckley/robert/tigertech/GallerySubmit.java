package buckley.robert.tigertech;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.Calendar;
import java.util.Date;

public class GallerySubmit extends AppCompatActivity {
    EditText name, description, url;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_submit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        initialize();
    }
    public void initialize(){
        setTitle(R.string.app_name);
        url = (EditText) findViewById(R.id.url);
        description = (EditText) findViewById(R.id.description);
        name = (EditText) findViewById(R.id.name);
        submit = (Button)findViewById(R.id.submit);
    }
    public void submitted(View view){
        try {
            Calendar c = Calendar.getInstance();
            Project project = new Project(name.getText().toString(), description.getText().toString(), url.getText().toString(), c.getTime());
            Firebase f = new Firebase("https://tigertechgallery.firebaseio.com/");
            f.push().setValue(project);
            Toast.makeText(GallerySubmit.this, "Thank you!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Gallery.class));
        }
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(GallerySubmit.this,"Could not submit", Toast.LENGTH_LONG).show();
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
                GallerySubmit.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                break;
            case R.id.information:
                AlertDialog ad = new AlertDialog.Builder(GallerySubmit.this).setMessage(R.string.information).setTitle("Information").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create();
                ad.show();
                break;
            case R.id.mission:
                AlertDialog ad0 = new AlertDialog.Builder(GallerySubmit.this).setMessage(R.string.mission).setTitle("Mission").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
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
