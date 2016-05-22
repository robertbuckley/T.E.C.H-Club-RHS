package buckley.robert.tigertech;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Robert on 5/13/2016.
 */
public class FireBaseHandler  {
    Firebase f;
    ArrayList<Post> posts;
    ArrayList<String> titles;
    Context context;
    public FireBaseHandler(Context context){
        f = new Firebase("https://tigertech.firebaseio.com");
        posts = new ArrayList<Post>();
        titles = new ArrayList<String>();
        this.context = context;
    }
    public void findPosts(){
        f.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                posts.add(dataSnapshot.getValue(Post.class));
                titles.clear();
                //titles.add(dataSnapshot.getValue(Post.class).getTitle() + "\n" + "\"" +dataSnapshot.getValue(Post.class).getPreview() + "\""+ ". . .");
                Collections.sort(posts);
                Collections.reverse(posts);
                for(Post p: posts){
                    titles.add(p.getTitle() + "\n" + "\"" +p.getPreview() + "\""+ ". . .");
                }
                ArrayAdapter<String> adapter =
                        new PostListAdapter(context, titles.toArray(new String[titles.size()]));
                MainActivity.postView.setAdapter(adapter);
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

    }
    public ArrayList<Post> getPosts(){
        return posts;
    }
    public ArrayList<String> getTitles() {
        return titles;
    }
}
