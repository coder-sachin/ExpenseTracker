package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddIncomeActivity extends AppCompatActivity {

    Button enter, cancel;
    EditText incomesrc, incomeamt;
    DatabaseHelper databaseHelper;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        databaseHelper=new DatabaseHelper(this);
        enter= (Button) findViewById(R.id.enter);
        cancel= (Button) findViewById(R.id.cancel);
        incomesrc= (EditText) findViewById(R.id.eincomedesc);
        incomeamt= (EditText) findViewById(R.id.eincomeamt);

        id=getIntent().getIntExtra("income_id",0);

        if(id!=0){
            Income income=new Income();
            income=databaseHelper.getIncomeInfo(id);
            incomesrc.setText(income.income_desc);
            incomeamt.setText(income.income_amt+"");
            enter.setText("Update");
        }

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String incomesrcValue=incomesrc.getText().toString();
                int incomeamtValue= Integer.parseInt(incomeamt.getText().toString());
                if(incomesrc.getText().toString().isEmpty() | (incomeamt.getText().toString().isEmpty())){


                    Toast.makeText(AddIncomeActivity.this,"Please enter in all fields", Toast.LENGTH_SHORT).show();

                }
                else{

                    ContentValues contentValues=new ContentValues();
                    contentValues.put("income_desc",incomesrcValue);
                    contentValues.put ("income_amt",incomeamtValue);
                    databaseHelper= new DatabaseHelper(AddIncomeActivity.this);
                    if(id!=0){

                        databaseHelper.updateIncome(id,contentValues);
                        Toast.makeText(AddIncomeActivity.this,"Income updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        databaseHelper.insertIncome(contentValues);
                        Toast.makeText(AddIncomeActivity.this,"Income added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
