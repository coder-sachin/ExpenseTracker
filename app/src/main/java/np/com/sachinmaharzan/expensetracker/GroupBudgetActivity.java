package np.com.sachinmaharzan.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GroupBudgetActivity extends AppCompatActivity {

    TextView budgetamt;
    Button addbudget;
    DatabaseHelper databaseHelper;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_budget);
        databaseHelper=new DatabaseHelper(this);
        id=getIntent().getIntExtra("g_id",0);
        budgetamt= (TextView) findViewById(R.id.budgetamt);
        addbudget= (Button) findViewById(R.id.addbudget);
        Log.i("budgetsum", "abovesetext: "+databaseHelper.getGbudgetSum(id));
        budgetamt.setText("Rs."+databaseHelper.getGbudgetSum(id));

        addbudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GroupBudgetActivity.this,AddGroupBudgetActivity.class).putExtra("g_id",id));

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        budgetamt.setText("Rs."+databaseHelper.getGbudgetSum(id));
    }
}
