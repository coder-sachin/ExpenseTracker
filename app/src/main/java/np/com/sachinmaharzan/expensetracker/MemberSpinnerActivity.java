package np.com.sachinmaharzan.expensetracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lazyboy on 6/28/17.
 */

public class MemberSpinnerActivity extends ArrayAdapter<Member> {
    Context context;
    public static int ID;

    public MemberSpinnerActivity(@NonNull Context context, ArrayList<Member> list) {
        super(context, 0, list);
        this.context=context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.member_spinner_layout,null);
        TextView textView= (TextView) view.findViewById(R.id.member);

        Member member=getItem(position);
        textView.setText(member.m_name);

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.member_spinner_layout,null);
        TextView textView= (TextView) view.findViewById(R.id.member);
        final Member member=getItem(position);
        textView.setText(member.m_name);


        return view;
    }
}
