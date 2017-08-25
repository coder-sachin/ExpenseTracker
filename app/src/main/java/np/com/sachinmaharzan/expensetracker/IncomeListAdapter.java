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
 * Created by lazyboy on 6/17/17.
 */

public class IncomeListAdapter extends ArrayAdapter<Income> {

    Context context;
    DatabaseHelper databaseHelper;


    public IncomeListAdapter(@NonNull Context context, ArrayList<Income> list) {
        super(context, 0, list);
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        Button delete, edit;
        TextView incomedesc, incomeamt;
        incomedesc = (TextView) view.findViewById(R.id.expensedetail);
        incomeamt = (TextView) view.findViewById(R.id.expenseamt);
        edit = (Button) view.findViewById(R.id.edit);
        delete = (Button) view.findViewById(R.id.delete);

        final Income income = getItem(position);
        incomedesc.setText(income.income_desc);
        incomeamt.setText("Rs " + income.income_amt);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddIncomeActivity.class);
                intent.putExtra("income_id", income.income_id);
                context.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialog(income.income_id);

            }
        });

        return view;
    }


    public void showAlertDialog(final int income_id){
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setTitle("DELETE RECORD!!");
        dialog.setMessage("Are you sure?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper=new DatabaseHelper(context);
                databaseHelper.deleteIncome(income_id);
                Toast.makeText(context,"Income Deleted Successfully", Toast.LENGTH_SHORT).show();
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
