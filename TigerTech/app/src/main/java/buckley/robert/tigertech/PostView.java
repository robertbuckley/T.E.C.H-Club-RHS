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
import android.widget.TextView;

public class PostView extends BaseClass {
    TextView title, post, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        initialize();
    }
    public void initialize() {
        setTitle(R.string.app_name);
        title = (TextView) findViewById(R.id.title);
        post = (TextView) findViewById(R.id.post);
        date = (TextView) findViewById(R.id.date);
        title.setText(MainActivity.clicked.getTitle());
        post.setText(MainActivity.clicked.getPost());
        date.setText(MainActivity.clicked.getDate().toString());
    }
}
