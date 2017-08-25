package np.com.sachinmaharzan.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DebtActivity extends AppCompatActivity {

    TextView dbsum,adddb;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt);

        dbsum= (TextView) findViewById(R.id.tvdbsum);
        adddb= (TextView) findViewById(R.id.adddb);

        databaseHelper=new DatabaseHelper(this);

        dbsum.setText("Rs."+databaseHelper.getDebitSum());

        adddb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DebtActivity.this,AddDebtActivity.class));

            }
        });

        dbsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DebtActivity.this,DebtListActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbsum.setText("Rs."+databaseHelper.getDebitSum());

    }
}
