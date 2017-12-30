package np.com.sachinmaharzan.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class GroupBudgetListActivity extends AppCompatActivity {

    ListView listView;
    int gid;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_budget_list);
        databaseHelper=new DatabaseHelper(this);

        gid=getIntent().getIntExtra("g_id",0);

        listView= (ListView) findViewById(R.id.listview);
        listView.setAdapter(new GroupBudgetListAdapter(GroupBudgetListActivity.this,databaseHelper.getGbudetList(gid)));


    }
}
