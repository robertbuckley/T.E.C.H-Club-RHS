package buckley.robert.tigertech;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class ImageListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] imageUrls;

    public ImageListAdapter(Context context, String[] imageUrls) {
        super(context, R.layout.listview_part, imageUrls);
        this.context = context;
        this.imageUrls = imageUrls;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_part, parent, false);
        }
        Picasso.with(context).load(imageUrls[position]).fit().centerCrop().into((ImageView) convertView);
        System.out.println("loaded");
        return convertView;
    }
}