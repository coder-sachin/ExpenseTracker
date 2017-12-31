package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import static np.com.sachinmaharzan.expensetracker.DatabaseHelper.name;
import static np.com.sachinmaharzan.expensetracker.R.id.eexpensein;

public class AddGroupExpenseActivity extends AppCompatActivity {

    Spinner spinner;
    EditText expensedesc, expenseamt;
    DatabaseHelper databaseHelper;
    int gid,gexid,g;
    Button enter, cancel;
    RadioGroup rg;
    RadioButton grup, mem;
    Boolean grupbudget=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_expense);
        gexid=getIntent().getIntExtra("gexpense_id",0);
        gid=getIntent().getIntExtra("g_id",0);
        Log.i("", "Receive gexpenseid"+gid);
        spinner= (Spinner) findViewById(R.id.spinnerr);
        expensedesc= (EditText) findViewById(eexpensein);
        expenseamt= (EditText) findViewById(R.id.eexpenseamt);
        enter= (Button) findViewById(R.id.enter);
        cancel= (Button) findViewById(R.id.cancel);
        rg= (RadioGroup) findViewById(R.id.radio);
        grup= (RadioButton) findViewById(R.id.radiogrup);
        mem= (RadioButton) findViewById(R.id.radiomem);
        databaseHelper=new DatabaseHelper(this);

        //Deactive spinner at first
        spinner.setVisibility(View.GONE);
        spinner.setClickable(false);

        if(gexid != 0){
            Gexpense ge;
            ge=databaseHelper.getGexpenseinfo(gexid);
            g=ge.g_id;
            Log.i("", "groupid for memberlist: "+g);
            expensedesc.setText(ge.gexpense_desc);
            expenseamt.setText(ge.gexpense_amt+"");
            spinner.setAdapter(new MemberSpinnerActivity(AddGroupExpenseActivity.this,databaseHelper.getMemberList(g)));
            enter.setText("Update");
        }else{
            spinner.setAdapter(new MemberSpinnerActivity(AddGroupExpenseActivity.this,databaseHelper.getMemberList(gid)));
        }

        mem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    spinner.setVisibility(View.VISIBLE);
                    spinner.setClickable(true);
                    Log.d("if true", "onCheckedChanged: ");
                }
                else{
                    spinner.setVisibility(View.GONE);
                    spinner.setClickable(false);
                    Log.d("else","oncheckedchanged");
                }
            }
        });

        grup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                grupbudget=true;
                Toast.makeText(AddGroupExpenseActivity.this,"Budget amount checked",Toast.LENGTH_LONG).show();
            }
        });



        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grupbudget =paidFromBudget();
                if(grupbudget){
                    String expensedescValue=expensedesc.getText().toString();
                    int expenseamtValue= Integer.parseInt(expenseamt.getText().toString());
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("gbexpense_amt",expenseamtValue);
                    contentValues.put("gbexpense_desc",expensedescValue);


                    if(gexid !=0){
                        contentValues.put("g_id",g);
                        databaseHelper.updateGbudgetexpense(gexid,contentValues);
                        Toast.makeText(AddGroupExpenseActivity.this, "Updated succesfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        contentValues.put("g_id",gid);
                        databaseHelper.insertGbudgetExpense(contentValues);
                        Toast.makeText(AddGroupExpenseActivity.this, "Inserted succesfully to Grupbudgetexpense", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                }
                else{

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

                    Log.i("belwo insert", ": ");

                    if(gexid !=0){
                        contentValues.put("g_id",g);
                        databaseHelper.updateGexpense(gexid,contentValues);
                        Toast.makeText(AddGroupExpenseActivity.this, "Updated succesfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        contentValues.put("g_id",gid);
                        databaseHelper.insertGexpense(contentValues);
                        Toast.makeText(AddGroupExpenseActivity.this, "Inserted succesfully", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                }



            }

            private Boolean paidFromBudget() {
                return grupbudget;
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
