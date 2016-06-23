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

public class GallerySubmit extends BaseClass {
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
            if(url.getText().toString().contains(".jpg") || (url.getText().toString().contains(".png"))){
                Calendar c = Calendar.getInstance();
                Project project = new Project(name.getText().toString(), description.getText().toString(), url.getText().toString(), c.getTime());
                Firebase f = new Firebase("https://tigertechgallery.firebaseio.com/");
                f.push().setValue(project);
                Toast.makeText(GallerySubmit.this, "Thank you!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Gallery.class));
            }
            else {
                Toast.makeText(GallerySubmit.this, "Invalid URL", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(GallerySubmit.this,"Could not submit", Toast.LENGTH_LONG).show();
        }
    }
}
