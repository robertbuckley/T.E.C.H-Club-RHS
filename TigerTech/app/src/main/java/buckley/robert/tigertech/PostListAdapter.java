package buckley.robert.tigertech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Robert on 5/21/2016.
 */
public class PostListAdapter extends ArrayAdapter{
    private Context context;
    private LayoutInflater inflater;
    private String[] titles;
    public PostListAdapter(Context context, String[] titles){
        super(context, R.layout.postview_part,titles);
        this.context = context;
        this.titles = titles;
        inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(null == convertView){
            convertView = inflater.inflate(R.layout.postview_part, parent, false);
        }
        ((TextView)convertView).setText(titles[position]);
        return convertView;
    }
}
