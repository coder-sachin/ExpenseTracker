package np.com.sachinmaharzan.expensetracker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import static android.content.ContentValues.TAG;

public class GroupExpenseListActivity extends Activity {

    int gid;
    ListView container1, container2;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_expense_list);
        databaseHelper=new DatabaseHelper(this);
        gid=getIntent().getIntExtra("g_id",0);
        Log.i("received gid is", ""+gid);

        container1= (ListView)findViewById(R.id.container11);
        container2= (ListView)findViewById(R.id.container22);
        container1.setAdapter(new GroupExpenseListAdapter(GroupExpenseListActivity.this,databaseHelper.getGexpenseList(gid)));
        container2.setAdapter(new GroupBudgetExpenseListAdapter(GroupExpenseListActivity.this,databaseHelper.getGbexpenseList(gid)));


    }

    @Override
    protected void onResume() {
        super.onResume();
        container1.setAdapter(new GroupExpenseListAdapter(GroupExpenseListActivity.this,databaseHelper.getGexpenseList(gid)));
        container2.setAdapter(new GroupBudgetExpenseListAdapter(GroupExpenseListActivity.this,databaseHelper.getGbexpenseList(gid)));
    }


}
