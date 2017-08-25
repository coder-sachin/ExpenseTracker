package np.com.sachinmaharzan.expensetracker.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import np.com.sachinmaharzan.expensetracker.BalanceActivity;
import np.com.sachinmaharzan.expensetracker.ExpenseActivity;
import np.com.sachinmaharzan.expensetracker.IncomeActivity;
import np.com.sachinmaharzan.expensetracker.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IndividualHomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IndividualHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndividualHomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PAGE = "ARG_PAGE";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView expense, income, balance, creditamt, debtamt, overview,target;

    private OnFragmentInteractionListener mListener;
    private int mPage;

    public IndividualHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndividualHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndividualHomeFragment newInstance(int page) {
        IndividualHomeFragment fragment = new IndividualHomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,page);
        IndividualHomeFragment fragment1=new IndividualHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getInt(ARG_PAGE);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.activity_individual_home,null);

        expense= (TextView) view.findViewById(R.id.expense);
        income= (TextView) view.findViewById(R.id.incomes);
        balance= (TextView) view.findViewById(R.id.balance);
        creditamt= (TextView) view.findViewById(R.id.cramt);
        debtamt= (TextView) view.findViewById(R.id.debtamt);
        overview= (TextView) view.findViewById(R.id.overview);
        target= (TextView) view.findViewById(R.id.targetsaving);

        Typeface typeface= Typeface.createFromAsset(getActivity().getAssets(),"PTN57F.ttf");
        expense.setTypeface(typeface);
        income.setTypeface(typeface);
        balance.setTypeface(typeface);
        creditamt.setTypeface(typeface);
        debtamt.setTypeface(typeface);
        overview.setTypeface(typeface);
        target.setTypeface(typeface);



        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ExpenseActivity.class);
                startActivity(intent);
            }
        });

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),IncomeActivity.class);
                startActivity(intent);
            }
        });
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),BalanceActivity.class));
            }
        });/*
        creditamt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CreditActivity.class));
            }
        });
        debtamt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),DebtActivity.class));
            }
        });

        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PieChartActivity.class));
            }
        });*/



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
