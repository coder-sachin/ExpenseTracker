package np.com.sachinmaharzan.expensetracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemberListActivity extends AppCompatActivity {

    LinearLayout container;
    DatabaseHelper databaseHelper;
     int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        id=getIntent().getIntExtra("g_id",0);
        Log.i("groupid", "intent got from grphome: "+id);
        container = (LinearLayout) findViewById(R.id.container);
        populateData();

    }

    public void populateData(){
        container.removeAllViews();
        ArrayList<Member> list=new ArrayList<Member>();
        databaseHelper=new DatabaseHelper(this);
        list= databaseHelper.getMemberList(id);
        for(final Member m: list){

            TextView textView=new TextView(this);
            View view= LayoutInflater.from(this).inflate(R.layout.member_list_layout,null);
            TextView name, id;
            Button edit, delete;
            edit= (Button) view.findViewById(R.id.edit);
            delete= (Button) view.findViewById(R.id.delete);
            name= (TextView) view.findViewById(R.id.mname);
            id= (TextView) view.findViewById(R.id.gid);
            name.setText("Member name is:"+m.m_name);
            id.setText("Group id:"+ Integer.toString(m.g_id));
            container.addView(view);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MemberListActivity.this,AddMemberActivity.class);
                    intent.putExtra("m_id",m.m_id);
                    intent.putExtra("g_id",m.g_id);

                    startActivity(intent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlertDialog(m.m_id);

                }
            });
        }

    }

    public void showAlertDialog(final int m_id){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("DELETE RECORD!!");
        dialog.setMessage("Are you sure?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                databaseHelper.deleteMember(m_id);
                Toast.makeText(MemberListActivity.this,"Member Deleted Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }
}
