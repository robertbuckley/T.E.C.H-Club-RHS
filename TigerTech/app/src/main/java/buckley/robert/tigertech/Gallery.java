package buckley.robert.tigertech;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
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
import android.widget.ImageView;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Gallery extends AppCompatActivity {
    ListView listView;
    Firebase fire;
    String currentUrl = "";
    ArrayList<String> urls;
    ArrayList<Project> projects;
    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialize();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Gallery.this, GallerySubmit.class));
            }
        });
    }
    public void initialize(){
        setTitle(R.string.app_name);
        listView = (ListView) findViewById(R.id.galleryView);
        fire = new Firebase("https://tigertechgallery.firebaseio.com/");
        urls = new ArrayList<String>();
        projects = new ArrayList<Project>();
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                num++;
                urls.clear();
                Project current = dataSnapshot.getValue(Project.class);
                projects.add(current);
                Collections.sort(projects);
                Collections.reverse(projects);
                for(int i = 0; i < projects.size(); ++i) {
                    currentUrl = projects.get(i).getUrl();
                    if (currentUrl.contains("http")) {
                        urls.add(currentUrl);
                    } else {
                        urls.add("http://" + currentUrl);
                    }
                }
                listView.setAdapter(new ImageListAdapter(Gallery.this, urls.toArray(new String[urls.size()])));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog l = new AlertDialog.Builder(Gallery.this)
                        .setMessage("Name: " + projects.get(position).getName() + "\n" + "Description: " + projects.get(position).getDescription()).setTitle("")
                        .create();
                l.show();
                System.out.println(position);
            }
        });
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
                Gallery.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                break;
            case R.id.information:
                AlertDialog ad = new AlertDialog.Builder(Gallery.this).setMessage(R.string.lorum).setTitle("Information").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create();
                ad.show();
                break;
            case R.id.mission:
                AlertDialog ad0 = new AlertDialog.Builder(Gallery.this).setMessage(R.string.lorum).setTitle("Mission").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
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
