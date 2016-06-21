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

public class ResearchSubmit extends BaseClass {
    EditText editUrl;
    Button youtubeSubmit;
    Firebase fire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_submit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
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
}
