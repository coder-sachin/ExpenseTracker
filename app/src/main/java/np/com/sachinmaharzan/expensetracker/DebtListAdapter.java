package np.com.sachinmaharzan.expensetracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lazyboy on 6/18/17.
 */

public class DebtListAdapter extends ArrayAdapter<Debit> {

    Context context;
    DatabaseHelper databaseHelper;

    public DebtListAdapter(@NonNull Context context, ArrayList<Debit> list) {
        super(context, 0,list);
        this.context=context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.credit_itemlayout, null);
        Button delete, edit;
        TextView cramt, crto,crdesc;
        crto = (TextView) view.findViewById(R.id.crto);
        crdesc = (TextView) view.findViewById(R.id.crdesc);
        cramt= (TextView) view.findViewById(R.id.cramt);
        edit = (Button) view.findViewById(R.id.edit);
        delete = (Button) view.findViewById(R.id.delete);
        final Debit debit = getItem(position);
        crto.setText("Money taken from "+debit.db_from);
        crdesc.setText("Description:"+debit.db_desc);
        cramt.setText("Worth Rs."+ Integer.toString(debit.db_amt));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddDebtActivity.class);
                intent.putExtra("db_id", debit.db_id);
                context.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialog(debit.db_id);

            }
        });

        return view;
    }

    public void showAlertDialog(final int db_id){
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setTitle("DELETE RECORD!!");
        dialog.setMessage("Are you sure?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper=new DatabaseHelper(context);
                databaseHelper.deleteDebit(db_id);
                Toast.makeText(context,"Debt Deleted Successfully", Toast.LENGTH_SHORT).show();
                ((DebtListActivity)context).finish();

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
