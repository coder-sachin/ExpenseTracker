package np.com.sachinmaharzan.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SettleActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    int id,memNumbers, totalexpense,dividedamt;
    ArrayList<Member> members=new ArrayList<>();
    TextView expenseVal,numMembers,settledamt,excess,transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle);
        databaseHelper= new DatabaseHelper(this);

        id=getIntent().getIntExtra("g_id",0);
        Log.i("dafteri", "gupid is : "+id);
        totalexpense=databaseHelper.getGexpenseSum(id);
        members=databaseHelper.getMemberList(id);
        memNumbers=members.size();
        dividedamt=settleAmt(totalexpense,memNumbers);
        Log.i("all", "calculated values: "+totalexpense+memNumbers+dividedamt);
        expenseVal= (TextView) findViewById(R.id.expenseVal);
        numMembers= (TextView) findViewById(R.id.numMembers);
        settledamt= (TextView) findViewById(R.id.settledamt);
        excess= (TextView) findViewById(R.id.excessvalue);
        transaction= (TextView) findViewById(R.id.transaction);

        expenseVal.setText(totalexpense+"");
        numMembers.setText(memNumbers+"");
        settledamt.setText(dividedamt+"");
        excess.setText(totalexpense-(memNumbers*dividedamt)+"");

        numMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettleActivity.this,MemberListActivity.class).putExtra("g_id",id));
            }
        });
        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettleActivity.this,TransactionToSettle.class).putExtra("g_id",id).putExtra("divided_amt",dividedamt));
            }
        });

    }

    private int settleAmt(int totalexpense, int memNumbers) {
        int settle=totalexpense/memNumbers;
        while((settle%5) !=0){
            settle++;
        }
        return settle;
    }
}
