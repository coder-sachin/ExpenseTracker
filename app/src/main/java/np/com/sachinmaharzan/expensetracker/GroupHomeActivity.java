package np.com.sachinmaharzan.expensetracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GroupHomeActivity extends AppCompatActivity {


    TextView members, addmembers, expense, budget, overview, settle;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);
        id=getIntent().getIntExtra("g_id",0);

        members= (TextView) findViewById(R.id.members);
        addmembers= (TextView) findViewById(R.id.addmembers);
        expense= (TextView) findViewById(R.id.gexpense);
        budget= (TextView) findViewById(R.id.budget);
        overview= (TextView) findViewById(R.id.overview);
        settle= (TextView) findViewById(R.id.settle);

        Typeface typeface=Typeface.DEFAULT.createFromAsset(this.getAssets(),"PTN57F.ttf");
        members.setTypeface(typeface);
        addmembers.setTypeface(typeface);
        expense.setTypeface(typeface);
        budget.setTypeface(typeface);
        overview.setTypeface(typeface);
        settle.setTypeface(typeface);

        addmembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GroupHomeActivity.this,AddMemberActivity.class);
                intent.putExtra("g_id",id);
                startActivity(intent);
            }
        });

        members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GroupHomeActivity.this,MemberListActivity.class);
                intent.putExtra("g_id",id);
                startActivity(intent);
            }
        });

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GroupHomeActivity.this,GroupExpenseActivity.class);
                intent.putExtra("g_id",id);
                startActivity(intent);
            }
        });

        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GroupHomeActivity.this,GroupBudgetActivity.class);
                intent.putExtra("g_id",id);
                startActivity(intent);
            }
        });
        settle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GroupHomeActivity.this,SettleActivity.class);
                intent.putExtra("g_id",id);
                startActivity(intent);

            }
        });

        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GroupHomeActivity.this,GroupPieChartActivity.class);
                intent.putExtra("g_id",id);
                startActivity(intent);
            }
        });


    }
}
