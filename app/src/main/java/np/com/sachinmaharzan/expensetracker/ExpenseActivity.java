package np.com.sachinmaharzan.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpenseActivity extends AppCompatActivity {

    TextView addexpense,expensesum;
    LinearLayout container;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        databaseHelper=new DatabaseHelper(this);
        addexpense= (TextView) findViewById(R.id.addexpense);
        container= (LinearLayout) findViewById(R.id.container);
        expensesum= (TextView) findViewById(R.id.texpensesum);
        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ExpenseActivity.this,AddExpenseActivity.class));
            }
        });

        expensesum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseActivity.this,ExpenseListActivity.class));
            }
        });

        int total= populateData();
        expensesum.setText("Rs."+Integer.toString(total));
    }

    public int populateData() {
        int total=databaseHelper.getExpenseSum();
        return total;
    }

    @Override
    protected void onResume() {
        super.onResume();
        int total= populateData();
        expensesum.setText("Rs."+Integer.toString(total));
    }
    }
}
