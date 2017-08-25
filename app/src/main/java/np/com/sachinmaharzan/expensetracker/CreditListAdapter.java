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

public class CreditListAdapter extends ArrayAdapter<Credit> {

    Context context;
    DatabaseHelper databaseHelper;



    public CreditListAdapter(@NonNull Context context, ArrayList<Credit> list) {
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

        final Credit credit = getItem(position);
        crto.setText("Money lent to "+credit.cr_to);
        crdesc.setText("Description:"+credit.cr_desc);
        cramt.setText("Worth Rs."+ Integer.toString(credit.cr_amt));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddCreditActivity.class);
                intent.putExtra("cr_id", credit.cr_id);
                context.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialog(credit.cr_id);

            }
        });

        return view;
    }

    public void showAlertDialog(final int cr_id){
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setTitle("DELETE RECORD!!");
        dialog.setMessage("Are you sure?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper=new DatabaseHelper(context);
                databaseHelper.deleteCredit(cr_id);
                Toast.makeText(context,"Credit Deleted Successfully", Toast.LENGTH_SHORT).show();

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
