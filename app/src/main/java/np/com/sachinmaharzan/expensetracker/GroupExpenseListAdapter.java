package np.com.sachinmaharzan.expensetracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by lazyboy on 6/28/17.
 */

public class GroupExpenseListAdapter extends ArrayAdapter<Gexpense> {

    Context context;
    DatabaseHelper databaseHelper;

    public GroupExpenseListAdapter(@NonNull Context context, ArrayList<Gexpense> list) {

        super(context,0,list);
        Log.i(TAG, "constructor: ");
        this.context=context;
    }

   public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       Log.i(TAG, "first line of adapter view: ");
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        Button delete, edit;
        TextView expensedesc, expenseamt,grpid;
       grpid= (TextView) view.findViewById(R.id.grpid);
       expensedesc = (TextView) view.findViewById(R.id.expensedetail);
        expenseamt = (TextView) view.findViewById(R.id.expenseamt);
        edit = (Button) view.findViewById(R.id.edit);
        delete = (Button) view.findViewById(R.id.delete);
        final Gexpense expense = getItem(position);
        expensedesc.setText(expense.gexpense_desc);
       Log.i(TAG, "expense detail is: "+expense.gexpense_desc);
        expenseamt.setText("Rs " + expense.gexpense_amt);
        grpid.setText("grp id"+expense.g_id);

       Log.i(TAG, "gidis: "+expense.g_id);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddGroupExpenseActivity.class);
                intent.putExtra("gexpense_id", expense.gexpense_id);
                context.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(expense.gexpense_id);
            }
        });
        return view;
    }



    public void showAlertDialog(final int gexpense_id){
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setTitle("DELETE RECORD!!");
        dialog.setMessage("Are you sure?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper=new DatabaseHelper(context);
                databaseHelper.deleteGexpense(gexpense_id);
                Toast.makeText(context,"Expense Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }



}
