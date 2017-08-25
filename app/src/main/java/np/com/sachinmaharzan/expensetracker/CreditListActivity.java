package np.com.sachinmaharzan.expensetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class CreditListActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_list);
        listView= (ListView) findViewById(R.id.listview);
        databaseHelper=new DatabaseHelper(this);

        listView.setAdapter(new CreditListAdapter(this,databaseHelper.getCreditList()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(new CreditListAdapter(CreditListActivity.this,databaseHelper.getCreditList()));

    }


}
