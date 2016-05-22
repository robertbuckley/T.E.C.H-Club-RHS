package buckley.robert.tigertech;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Collections;

public class Research extends AppCompatActivity {
    ListView thumbnails;
    Firebase fire;
    ArrayList<String> urls;
    ArrayList<String> trueUrls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Research.this, ResearchSubmit.class));
            }
        });
        initialize();
    }
    public void initialize() {
        setTitle(R.string.app_name);
        thumbnails = (ListView)findViewById(R.id.thumbnails);
        fire = new Firebase("https://tigertechurls.firebaseio.com/");
        urls = new ArrayList<String>();
        trueUrls = new ArrayList<String>();
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String current = dataSnapshot.getValue(String.class);
                    trueUrls.add(current);
                    String id = current.substring(current.indexOf("=") + 1, current.length());
                    urls.add("http://img.youtube.com/vi/" + id + "/0.jpg");
                    Collections.reverse(urls);
                    Collections.reverse(trueUrls);
                    thumbnails.setAdapter(new ImageListAdapter(Research.this, urls.toArray(new String[urls.size()])));
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
        thumbnails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("http://www.youtube.com/watch?v=" + urls.get(position));
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trueUrls.get(position))));
            }
        });
    }
}
