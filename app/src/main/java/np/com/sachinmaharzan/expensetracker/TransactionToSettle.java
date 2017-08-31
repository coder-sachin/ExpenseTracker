package np.com.sachinmaharzan.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TransactionToSettle extends AppCompatActivity {
    int id, dividedamt;
    DatabaseHelper databaseHelper;
    ArrayList<Member> members;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_to_settle);

        id=getIntent().getIntExtra("g_id",0);
        dividedamt=getIntent().getIntExtra("divided_amt",0);
        databaseHelper=new DatabaseHelper(this);
        members=databaseHelper.getMemberList(id);

        listView= (ListView) findViewById(R.id.listview);
        listView.setAdapter(new TransactionSettleAdapter(this,databaseHelper.getMemberList(id),dividedamt));


    }
}
