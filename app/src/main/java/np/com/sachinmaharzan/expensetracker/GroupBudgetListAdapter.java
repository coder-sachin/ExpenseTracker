package np.com.sachinmaharzan.expensetracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by lazyboy on 11/25/17.
 */

public class GroupBudgetListAdapter extends ArrayAdapter<Gbudget> {

    Context context;
    DatabaseHelper databaseHelper;

    public GroupBudgetListAdapter(@NonNull Context context, ArrayList<Gbudget> list) {
        super(context, 0,list);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(context).inflate(R.layout.gbudget_layout,null);
        Button edit, delete;
        TextView mname, budgetamt;

        edit=view.findViewById(R.id.edit);
        delete=view.findViewById(R.id.delete);
        mname= (TextView) view.findViewById(R.id.budgetby);
        budgetamt=(TextView) view.findViewById(R.id.budgetamt);

        databaseHelper=new DatabaseHelper(context);

        final Gbudget gbudget=getItem(position);
        Member member=new Member();
        member=databaseHelper.getMemberInfo(gbudget.m_id);
        String membername=member.m_name;
        Log.i(TAG, "membername: "+membername);
        mname.setText(membername);
        budgetamt.setText("Rs."+gbudget.budget_amt);

        return  view;

    }
}
