package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static np.com.sachinmaharzan.expensetracker.DatabaseHelper.name;
import static np.com.sachinmaharzan.expensetracker.R.id.eexpensein;

public class AddGroupExpenseActivity extends AppCompatActivity {

    Spinner spinner;
    EditText expensedesc, expenseamt;
    DatabaseHelper databaseHelper;
    int gid;
    Button enter, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_expense);

        gid=getIntent().getIntExtra("g_id",0);

        spinner= (Spinner) findViewById(R.id.spinnerr);
        expensedesc= (EditText) findViewById(eexpensein);
        expenseamt= (EditText) findViewById(R.id.eexpenseamt);
        enter= (Button) findViewById(R.id.enter);
        cancel= (Button) findViewById(R.id.cancel);

        databaseHelper=new DatabaseHelper(this);

        spinner.setAdapter(new MemberSpinnerActivity(AddGroupExpenseActivity.this,databaseHelper.getMemberList(gid)));

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String expensedescValue=expensedesc.getText().toString();
                int expenseamtValue= Integer.parseInt(expenseamt.getText().toString());
                Member memer= (Member) spinner.getSelectedItem();
                Log.i("name", "is: "+spinner.getSelectedItem()+"to sting is"+name);
                int memberidValue=databaseHelper.getMemberId(memer.m_name);
                Log.i("Memberid", "is: "+memberidValue);
                ContentValues contentValues=new ContentValues();
                contentValues.put("m_id",memberidValue);
                contentValues.put("gexpense_amt",expenseamtValue);
                contentValues.put("gexpense_desc",expensedescValue);
                contentValues.put("g_id",gid);
                Log.i("belwo insert", ": ");

                databaseHelper.insertGexpense(contentValues);


                Toast.makeText(AddGroupExpenseActivity.this, "Inserted succesfully", Toast.LENGTH_SHORT).show();
                finish();

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
