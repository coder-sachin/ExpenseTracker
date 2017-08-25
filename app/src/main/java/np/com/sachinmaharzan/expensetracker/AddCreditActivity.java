package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCreditActivity extends AppCompatActivity {

    Button enter, cancel;
    EditText crman, cramt,crdesc;
    DatabaseHelper databaseHelper;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit);
        crman= (EditText) findViewById(R.id.eexpensein);
        cramt= (EditText) findViewById(R.id.eexpenseamt);
        enter= (Button) findViewById(R.id.enter);
        cancel= (Button) findViewById(R.id.cancel);
        crdesc= (EditText) findViewById(R.id.ecrdesc);
        databaseHelper=new DatabaseHelper(this);
        id=getIntent().getIntExtra("cr_id",0);
        Log.i("Below getintextra", "Id: "+id);
        if(id!=0){
            Credit c;
            c=databaseHelper.getCreditInfo(id);
            crman.setText(c.cr_to);
            cramt.setText(c.cr_amt+"");
            crdesc.setText(c.cr_desc);
            enter.setText("Update");
        }
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String crtoValue=crman.getText().toString();
                String crdescValue=crdesc.getText().toString();

                int cramtValue= Integer.parseInt(cramt.getText().toString());
                if(crman.getText().toString().isEmpty() | (cramt.getText().toString().isEmpty()) | crdesc.getText().toString().isEmpty()){


                    Toast.makeText(AddCreditActivity.this,"Please enter in all fields", Toast.LENGTH_SHORT).show();

                }
                else{

                    ContentValues contentValues=new ContentValues();
                    contentValues.put("cr_to",crtoValue);
                    contentValues.put ("cr_amt",cramtValue);
                    contentValues.put("cr_desc",crdescValue);
                    databaseHelper= new DatabaseHelper(AddCreditActivity.this);
                    if(id!=0){

                        databaseHelper.updateCredit(id,contentValues);
                        Toast.makeText(AddCreditActivity.this,"Credit updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        databaseHelper.insertCredit(contentValues);
                        Toast.makeText(AddCreditActivity.this,"Credited amount added successfully", Toast.LENGTH_SHORT).show();
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

