package fr.anthonykalbe.daily_tracking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TraingingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TraingingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TraingingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TraingingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TraingingFragment newInstance(String param1, String param2) {
        TraingingFragment fragment = new TraingingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_trainging, container, false);

        Button push_button = view.findViewById(R.id.push_button);
        //Button pull_button = view.findViewById(R.id.pull_button);
        //Button leg_button = view.findViewById(R.id.leg_button);

        push_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the button's tag
                ((MainActivity) getActivity()).setActualSession(v.getTag().toString());
                   /// ((MainActivity) getActivity()).setData(v.getTag().toString(), new JSONObject().toString());
                    //setData(v.getTag().toString(), new JSONObject().toString());

                displayNewFragmentTraining("push");
            }
        });
//        pull_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    //setData("session", v.getTag().toString());
//                ((MainActivity) getActivity()).setActualSession(v.getTag().toString());
//                displayNewFragmentTraining("pull");
//            }
//        });
//        leg_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainActivity) getActivity()).setActualSession(v.getTag().toString());
//                displayNewFragmentTraining("leg");
//            }
//        });
        return view;
    }


    private void displayNewFragmentTraining(String arg) {
        Fragment NextFragment = new SessionFragment();
        if (NextFragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment, NextFragment).addToBackStack(null).commit();
        }
    }


}