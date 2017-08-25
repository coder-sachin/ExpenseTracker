package np.com.sachinmaharzan.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IndividualHomeActivity extends AppCompatActivity {
    TextView expense, income, balance, creditamt, debtamt, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_home);expense= (TextView) findViewById(R.id.expense);
        income= (TextView) findViewById(R.id.incomes);
        balance= (TextView) findViewById(R.id.balance);
        creditamt= (TextView) findViewById(R.id.cramt);
        debtamt= (TextView) findViewById(R.id.debtamt);
        overview= (TextView) findViewById(R.id.overview);


        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IndividualHomeActivity.this,ExpenseActivity.class);
                startActivity(intent);
            }
        });

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IndividualHomeActivity.this,IncomeActivity.class);
                startActivity(intent);
            }
        });

        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndividualHomeActivity.this,BalanceActivity.class));
            }
        });
/*
        creditamt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndividualHomeActivity.this,CreditActivity.class));
            }
        });

        debtamt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndividualHomeActivity.this,DebtActivity.class));
            }
        });
        */



    }
}
