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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FireBaseHandler handler;
    Button research, gallery;
    public static ListView postView;
    public static Post clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        initialize();
    }
    public void initialize(){
        setTitle(R.string.app_name);
        Firebase.setAndroidContext(this);
        handler = new FireBaseHandler(this);
        handler.findPosts();
        postView = (ListView) findViewById(R.id.postView);
        gallery = (Button)findViewById(R.id.gallery);
        research =(Button)findViewById(R.id.research);
        ArrayAdapter<String> adapter =
                new PostListAdapter(this, handler.getTitles().toArray(new String[handler.getTitles().size()]), handler.getPosts());
        postView.setAdapter(adapter);
        postView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clicked = handler.getPosts().get(position);
                startActivity(new Intent(MainActivity.this, PostView.class));
            }
        });
    }
    public void researchClicked(View view){
        startActivity(new Intent(MainActivity.this, Research.class));
    }
    public void galleryClicked(View view){
        startActivity(new Intent(MainActivity.this, Gallery.class));
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
                MainActivity.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                break;
            case R.id.information:
                AlertDialog ad = new AlertDialog.Builder(MainActivity.this).setMessage(R.string.information).setTitle("Information").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create();
                ad.show();
                break;
            case R.id.mission:
                AlertDialog ad0 = new AlertDialog.Builder(MainActivity.this).setMessage(R.string.mission).setTitle("Mission").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
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
