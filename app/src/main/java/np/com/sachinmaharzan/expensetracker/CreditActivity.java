package np.com.sachinmaharzan.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CreditActivity extends AppCompatActivity {

    TextView totalcr, addcr;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        totalcr= (TextView) findViewById(R.id.tvcrsum);
        addcr= (TextView) findViewById(R.id.addcr);
        databaseHelper=new DatabaseHelper(this);

        addcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(CreditActivity.this,AddCreditActivity.class));

            }
        });


        totalcr.setText(databaseHelper.getCreditSum()+"");
        totalcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreditActivity.this,CreditListActivity.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        totalcr.setText(databaseHelper.getCreditSum()+"");
    }
}
