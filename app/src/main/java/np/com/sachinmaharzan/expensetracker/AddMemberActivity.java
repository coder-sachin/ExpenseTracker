package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMemberActivity extends AppCompatActivity {

    EditText mname;
    Button enter, cancle;
    DatabaseHelper databaseHelper;
    int id, gid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

         gid = getIntent().getIntExtra("g_id", 0);
        Log.i("gid", "value: "+gid);
         id = getIntent().getIntExtra("m_id", 0);

        databaseHelper = new DatabaseHelper(this);
        mname = (EditText) findViewById(R.id.emname);
        enter = (Button) findViewById(R.id.enter);
        cancle = (Button) findViewById(R.id.cancel);

        if (id != 0) {
            Member me;
            me = databaseHelper.getMemberInfo(id);
            enter.setText("Update");
            mname.setText(me.m_name);
        }

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameValue = mname.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("m_name", nameValue);
                contentValues.put("g_id", gid);

                if (id != 0) {

                    databaseHelper.updateMember(id, contentValues);
                    Toast.makeText(AddMemberActivity.this, "Member updated successfully", Toast.LENGTH_SHORT).show();
                    finish();


                }
                else{

                    databaseHelper.insertMember(contentValues);
                    Toast.makeText(AddMemberActivity.this, "Member added", Toast.LENGTH_SHORT).show();
                    finish();

                }



            }
        });


    }
}
