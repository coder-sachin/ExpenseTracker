package np.com.sachinmaharzan.expensetracker;

import android.content.Context;
import android.content.Intent;
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
import java.util.Collection;

import static android.content.ContentValues.TAG;

/**
 * Created by lazyboy on 12/31/17.
 */

public class GroupBudgetExpenseListAdapter extends ArrayAdapter<Gbudgetexpense> {

    Context context;
    DatabaseHelper databaseHelper;


    public GroupBudgetExpenseListAdapter(@NonNull Context context, ArrayList<Gbudgetexpense> list) {
        super(context, 0,list);
        Log.i(TAG, "constructor of budgetexpense");
        this.context=context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.i(TAG, "first line of adapter view of gbudgetexpense: ");
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        Button delete, edit;
        TextView expensedesc, expenseamt,grpid;
        grpid= (TextView) view.findViewById(R.id.grpid);
        expensedesc = (TextView) view.findViewById(R.id.expensedetail);
        expenseamt = (TextView) view.findViewById(R.id.expenseamt);
        edit = (Button) view.findViewById(R.id.edit);
        delete = (Button) view.findViewById(R.id.delete);

        final Gbudgetexpense expense = getItem(position);
        expensedesc.setText(expense.gbexpense_desc);
        Log.i(TAG, "expense detail is: "+expense.gbexpense_amt);
        expenseamt.setText("Rs " + expense.gbexpense_amt);
        grpid.setText("grp id"+expense.g_id);

        Log.i(TAG, "gidis: "+expense.g_id);
        /*
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddGroupExpenseActivity.class);
                intent.putExtra("gexpense_id", expense.gexpense_id);
                Log.i(TAG, "Sent gexpense id"+expense.gexpense_id);
                context.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(expense.gexpense_id);
            }
        });*/
        return view;
    }
}
