package np.com.sachinmaharzan.expensetracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by lazyboy on 6/23/17.
 */

public class GroupListAdapter extends ArrayAdapter<Group> {

    Context context;
    DatabaseHelper databaseHelper;

    public GroupListAdapter(@NonNull Context context, ArrayList<Group> list) {
        super(context, 0, list);
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_list, null);
        TextView grpname;
        grpname = (TextView) view.findViewById(R.id.grpname);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"PTN57F.ttf");
        grpname.setTypeface(typeface);
        final Group group = getItem(position);
        grpname.setText(group.g_name);
        grpname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,GroupHomeActivity.class);
                intent.putExtra("g_id",group.g_id);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
