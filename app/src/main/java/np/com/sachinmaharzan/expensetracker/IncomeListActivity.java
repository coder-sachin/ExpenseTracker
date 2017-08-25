package np.com.sachinmaharzan.expensetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

public class IncomeListActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_list);

        listView= (ListView) findViewById(R.id.listview);
        databaseHelper=new DatabaseHelper(this);
        listView.setAdapter(new IncomeListAdapter(this,databaseHelper.getIncomeList()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(new IncomeListAdapter(this,databaseHelper.getIncomeList()));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listView.setAdapter(new IncomeListAdapter(this,databaseHelper.getIncomeList()));


    }
}
