package buckley.robert.tigertech;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
                Project current = dataSnapshot.getValue(Project.class);
                currentUrl = current.getUrl();
                if(currentUrl.contains("http")) {
                    urls.add(currentUrl);
                }
                else{
                    urls.add("http://" + currentUrl);
                }
                projects.add(current);
                Collections.reverse(projects);
                Collections.reverse(urls);
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

}
