package np.com.sachinmaharzan.expensetracker;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionSettleAdapter extends ArrayAdapter<Member>  {

     Context context;
     DatabaseHelper databasehelper;
     int damt, moneyGiven;

    public TransactionSettleAdapter(@NonNull Context context, ArrayList<Member> list, int dividedamt) {
        super(context, 0,list);
        this.context=context;
        damt=dividedamt;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=LayoutInflater.from(context).inflate(R.layout.settle_transactionlayout,null);
        TextView name, take , give;
        databasehelper=new DatabaseHelper(context);
        name=(TextView) view.findViewById(R.id.memberName);
        take=(TextView) view.findViewById(R.id.takeAmt);
        give=(TextView) view.findViewById(R.id.giveAmt);

        Typeface typeface=Typeface.DEFAULT.createFromAsset(context.getAssets(),"PTN57F.ttf");

        name.setTypeface(typeface);
        take.setTypeface(typeface);
        give.setTypeface(typeface);

        final Member member=getItem(position);
        name.setText("For "+member.m_name);


        moneyGiven=databasehelper.moneyGiven(member.m_id);

        Log.i("b4 if", "moneygiven and damt: "+moneyGiven+" "+damt);
        if(moneyGiven < damt){
            take.setText("Take : Rs. "+(damt-moneyGiven));
            give.setText("Give : Rs.0");
        }else if (moneyGiven> damt){
            take.setText("Take : Rs.0");
            give.setText("Give :Rs."+(moneyGiven-damt));

        }else {
            take.setText("Take :Rs.0");
            give.setText("Give :Rs.0");

        }

        return view;


    }


}
