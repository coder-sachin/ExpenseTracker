package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDebtActivity extends AppCompatActivity {

    EditText dbfrom, dbdesc, dbamt;
    Button enter, cancel;
    int id;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);

        dbfrom= (EditText) findViewById(R.id.edbfrom);
        dbdesc= (EditText) findViewById(R.id.edbdesc);
        dbamt= (EditText) findViewById(R.id.edbamt);
        enter= (Button) findViewById(R.id.enter);
        cancel= (Button) findViewById(R.id.cancel);
        databaseHelper=new DatabaseHelper(this);

        id=getIntent().getIntExtra("db_id",0);
        if(id!=0){
            Debit d;
            d=databaseHelper.getDebitInfo(id);
            dbfrom.setText(d.db_from);
            dbamt.setText(d.db_amt+"");
            dbdesc.setText(d.db_desc);
            enter.setText("Update");

        }
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dbfromValue=dbfrom.getText().toString();
                String dbdescValue=dbdesc.getText().toString();
                int dbamtValue= Integer.parseInt(dbamt.getText().toString());
                if(dbfrom.getText().toString().isEmpty() | (dbdesc.getText().toString().isEmpty()) | dbamt.getText().toString().isEmpty()){


                    Toast.makeText(AddDebtActivity.this,"Please enter in all fields", Toast.LENGTH_SHORT).show();

                }
                else{

                    ContentValues contentValues=new ContentValues();
                    contentValues.put("db_from",dbfromValue);
                    contentValues.put ("db_amt",dbamtValue);
                    contentValues.put("db_desc",dbdescValue);
                    if(id!=0){

                        databaseHelper.updateDebit(id,contentValues);
                        Toast.makeText(AddDebtActivity.this,"Debt updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        databaseHelper.insertDebit(contentValues);
                        Toast.makeText(AddDebtActivity.this,"Debt amount added successfully", Toast.LENGTH_SHORT).show();
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
