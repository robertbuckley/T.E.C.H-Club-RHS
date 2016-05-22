package buckley.robert.tigertech;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class GallerySubmit extends AppCompatActivity {
    EditText name, description, url;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_submit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
            Project project = new Project(name.getText().toString(), description.getText().toString(), url.getText().toString());
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

}
