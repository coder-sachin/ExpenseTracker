package np.com.sachinmaharzan.expensetracker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import static android.content.ContentValues.TAG;

public class GroupExpenseListActivity extends Activity {

    int gid;
    ListView container;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_expense_list);
        databaseHelper=new DatabaseHelper(this);

        gid=getIntent().getIntExtra("g_id",0);
        Log.i(TAG, "listactivity gid is: "+gid);

        container= (ListView) findViewById(R.id.container);

        container.setAdapter(new GroupExpenseListAdapter(GroupExpenseListActivity.this,databaseHelper.getGexpenseList(gid)));


    }

}
