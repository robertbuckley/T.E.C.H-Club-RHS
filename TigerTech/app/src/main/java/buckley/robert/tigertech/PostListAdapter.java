package buckley.robert.tigertech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Robert on 5/21/2016.
 */
public class PostListAdapter extends ArrayAdapter{
    private Context context;
    private LayoutInflater inflater;
    private String[] titles;
    ArrayList<Post> posts;
    public PostListAdapter(Context context, String[] titles, ArrayList<Post> posts){
        super(context, R.layout.postview_part,titles);
        this.context = context;
        this.titles = titles;
        this.posts = posts;
        inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(null == convertView){
            convertView = inflater.inflate(R.layout.postview_part, parent, false);
        }
        TextView name = (TextView)convertView.findViewById(R.id.nametxt);
        TextView datetxt = (TextView)convertView.findViewById(R.id.datetxt);
        TextView monthtxt = (TextView)convertView.findViewById(R.id.monthtxt);
        name.setText(titles[position]);
        datetxt.setText(Integer.toString(posts.get(position).getDate().getDate()));
        monthtxt.setText(Post.MONTHS[posts.get(position).getDate().getMonth()]);
        return convertView;
    }
}
