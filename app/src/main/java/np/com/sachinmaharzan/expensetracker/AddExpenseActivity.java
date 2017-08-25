package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity {

    Button enter, cancel;
    EditText expense, expenseamt;
    DatabaseHelper databaseHelper;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        expense= (EditText) findViewById(R.id.eexpensein);
        expenseamt= (EditText) findViewById(R.id.eexpenseamt);
        enter= (Button) findViewById(R.id.enter);
        cancel= (Button) findViewById(R.id.cancel);
        databaseHelper=new DatabaseHelper(this);
        id=getIntent().getIntExtra("expense_id",0);
        Log.i("Below getintextra", "Id: "+id);
        if(id!=0){
            Expense e;
            e=databaseHelper.getExpenseInfo(id);
            expense.setText(e.expense_detail);
            expenseamt.setText(e.expense_amt+"");
            enter.setText("Update");
        }
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String expenseValue=expense.getText().toString();
                int expenseamtValue= Integer.parseInt(expenseamt.getText().toString());
                if(expense.getText().toString().isEmpty() | (expenseamt.getText().toString().isEmpty())){


                    Toast.makeText(AddExpenseActivity.this,"Please enter in all fields", Toast.LENGTH_SHORT).show();

                }
                else{

                    ContentValues contentValues=new ContentValues();
                    contentValues.put("expense_detail",expenseValue);
                    contentValues.put ("expense_amt",expenseamtValue);
                    databaseHelper= new DatabaseHelper(AddExpenseActivity.this);
                    if(id!=0){

                        databaseHelper.updateExpense(id,contentValues);
                        Toast.makeText(AddExpenseActivity.this,"Expense updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                    databaseHelper.insertExpense(contentValues);
                    Toast.makeText(AddExpenseActivity.this,"Expense added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    }

              }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
