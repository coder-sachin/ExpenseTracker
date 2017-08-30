package np.com.sachinmaharzan.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        budgetamt.findViewById(R.id.budgetamt);
        addbudget.findViewById(R.id.addbudget);

        budgetamt.setText(databaseHelper.getGbudgetSum(id));

    }
}
