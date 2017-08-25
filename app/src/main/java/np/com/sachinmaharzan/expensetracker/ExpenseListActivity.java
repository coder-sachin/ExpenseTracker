package np.com.sachinmaharzan.expensetracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpenseListActivity extends AppCompatActivity {

    LinearLayout container;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);
        container= (LinearLayout) findViewById(R.id.container);
        populateData();
    }

    public void populateData(){
        container.removeAllViews();
        ArrayList<Expense> list=new ArrayList<Expense>();
        databaseHelper=new DatabaseHelper(this);
        list= databaseHelper.getExpenseList();
        for(int i=0;i<list.size();i++){

            Expense e=list.get(i);


        }
        for(final Expense e: list){

            TextView textView=new TextView(this);
            View view= LayoutInflater.from(this).inflate(R.layout.item_layout,null);
            TextView detail, amt;
            Button edit, delete;
            edit= (Button) view.findViewById(R.id.edit);
            delete= (Button) view.findViewById(R.id.delete);
            detail= (TextView) view.findViewById(R.id.expensedetail);
            amt= (TextView) view.findViewById(R.id.expenseamt);
            detail.setText("Expense in:"+e.expense_detail);
            amt.setText("Rs."+ Integer.toString(e.expense_amt));
            container.addView(view);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ExpenseListActivity.this,AddExpenseActivity.class);
                    intent.putExtra("expense_id",e.expense_id);
                    Log.i("After putextra", "id is: "+e.expense_id);
                    startActivity(intent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlertDialog(e.expense_id);

                }
            });
        }

    }

    public void showAlertDialog(final int expense_id){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("DELETE RECORD!!");
        dialog.setMessage("Are you sure?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                databaseHelper.deleteExpense(expense_id);
                Toast.makeText(ExpenseListActivity.this,"Expense Deleted Successfully", Toast.LENGTH_SHORT).show();
                finish();
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
