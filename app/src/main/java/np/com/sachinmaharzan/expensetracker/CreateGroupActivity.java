package np.com.sachinmaharzan.expensetracker;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateGroupActivity extends AppCompatActivity {

    EditText grpname;
    Button create, cancel;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        grpname= (EditText) findViewById(R.id.egname);
        create= (Button) findViewById(R.id.create);
        cancel= (Button) findViewById(R.id.cancel);
        databaseHelper=new DatabaseHelper(this);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gname=grpname.getText().toString();
                ContentValues contentValues=new ContentValues();
                contentValues.put("g_name",gname);
                databaseHelper.insertGroup(contentValues);
                Toast.makeText(CreateGroupActivity.this,"Group Created Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
