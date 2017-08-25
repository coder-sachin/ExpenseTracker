package np.com.sachinmaharzan.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GroupExpenseActivity extends AppCompatActivity {

    int gid;
    TextView expensesum;
    Button addexpense;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_expense);


        databaseHelper=new DatabaseHelper(this);
        gid=getIntent().getIntExtra("g_id",0);

        expensesum= (TextView) findViewById(R.id.expenseamt);
        addexpense= (Button) findViewById(R.id.addexpense);

        Log.i("line before get sum", "gid is : "+gid);
        int total=0;
        expensesum.setText("Rs"+ Integer.toString(databaseHelper.getGexpenseSum(gid)));

        expensesum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GroupExpenseActivity.this,GroupExpenseListActivity.class);
                intent.putExtra("g_id",gid);
                startActivity(intent);
            }
        });

        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GroupExpenseActivity.this,AddGroupExpenseActivity.class);
                intent.putExtra("g_id",gid);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        expensesum.setText("Rs"+ Integer.toString(databaseHelper.getGexpenseSum(gid)));
    }
}
