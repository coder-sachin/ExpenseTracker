package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddGroupBudgetActivity extends AppCompatActivity {

    EditText budgetamt;
    Spinner spinner;
    Button enter, cancel;
    int gid;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_budget);

        gid=getIntent().getIntExtra("g_id",0);

        databaseHelper=new DatabaseHelper(this);

        budgetamt= (EditText) findViewById(R.id.ebudgetamt);
        spinner= (Spinner) findViewById(R.id.spinnerr);
        enter= (Button) findViewById(R.id.enter);
        cancel= (Button) findViewById(R.id.cancel);

        spinner.setAdapter(new MemberSpinnerActivity(AddGroupBudgetActivity.this,databaseHelper.getMemberList(gid)));

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int budgetamtValue=Integer.parseInt(budgetamt.getText().toString());
                Member member= (Member) spinner.getSelectedItem();
                int memberidValue=member.m_id;

                ContentValues contentValues=new ContentValues();
                contentValues.put("g_id",gid);
                contentValues.put("m_id",memberidValue);
                contentValues.put("budget_amt",budgetamtValue);
                databaseHelper.insertGbudget(contentValues);

                Toast.makeText(AddGroupBudgetActivity.this,"Inserted successfully",Toast.LENGTH_SHORT).show();

                finish();



            }
        });


    }
}
