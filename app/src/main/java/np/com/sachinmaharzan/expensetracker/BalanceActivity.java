package np.com.sachinmaharzan.expensetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BalanceActivity extends AppCompatActivity {

    TextView balance;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        balance= (TextView) findViewById(R.id.tvbalance);
        databaseHelper=new DatabaseHelper(this);
        int totalExpense=databaseHelper.getExpenseSum();
        int totalIncome=databaseHelper.getIncomeSum();
        int bal=totalIncome-totalExpense;
        balance.setText("Rs."+bal);

    }
}
