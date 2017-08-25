package np.com.sachinmaharzan.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class IncomeActivity extends AppCompatActivity {

    TextView income, remamt, addincome,incomelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        income= (TextView) findViewById(R.id.income);
        remamt= (TextView) findViewById(R.id.remincome);
        addincome= (TextView) findViewById(R.id.addincome);
        incomelist= (TextView) findViewById(R.id.incomelist);

        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(IncomeActivity.this,AddIncomeActivity.class);
                startActivity(intent);

            }
        });

        incomelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IncomeActivity.this,IncomeListActivity.class));
            }
        });


    }
}
