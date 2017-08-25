package np.com.sachinmaharzan.expensetracker.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import np.com.sachinmaharzan.expensetracker.CreateGroupActivity;
import np.com.sachinmaharzan.expensetracker.DatabaseHelper;
import np.com.sachinmaharzan.expensetracker.GroupListAdapter;
import np.com.sachinmaharzan.expensetracker.R;

/**
 * Created by lazyboy on 6/20/17.
 */

public class GroupHomeFragment extends Fragment {

    Button create;
    ListView listView;
    DatabaseHelper databaseHelper;

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static GroupHomeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        GroupHomeFragment fragment = new GroupHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_group,null);
        create= (Button) view.findViewById(R.id.create);
        listView= (ListView) view.findViewById(R.id.listview);
        databaseHelper=new DatabaseHelper(getActivity());

        listView.setAdapter(new GroupListAdapter(getActivity(),databaseHelper.getGroupList()));

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CreateGroupActivity.class));
            }
        });


        return view;
    }

    @Override
    public void onResume() {

        super.onResume();
        listView.setAdapter(new GroupListAdapter(getActivity(),databaseHelper.getGroupList()));

    }
}
