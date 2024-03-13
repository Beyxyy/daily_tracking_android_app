package fr.anthonykalbe.daily_tracking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciceFragment extends Fragment {

    private LinearLayout dynamicLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExerciceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExerciceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciceFragment newInstance(String param1, String param2) {
        ExerciceFragment fragment = new ExerciceFragment();
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercice, container, false);
        // Ajouter un OnClickListener à tous les boutons dans le fragment
        addClickListenerToButtons(view);

        this.dynamicLayout = view.findViewById(R.id.dynamicLayout);
        view.findViewById(R.id.buttonsystemOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPreviousExercices(v);
            }
        });
        //setSet
        return view;
    }

    private void addClickListenerToButtons(View parentView) {
        Button button = parentView.findViewById(R.id.button);
        button.setOnClickListener(v -> handleButtonClick(parentView));
    }

    private void handleButtonClick(View parentView){
        String nbWeight = ((EditText) parentView.findViewById(R.id.editTextExerciceWeight)).getText().toString();
        String nbRep = ((EditText) parentView.findViewById(R.id.editTextExerciceReps)).getText().toString();
        //empty the editText
        ((EditText) parentView.findViewById(R.id.editTextExerciceWeight)).setText("");
        ((EditText) parentView.findViewById(R.id.editTextExerciceReps)).setText("");
        //try {
            //((MainActivity) getActivity()).setSet(nbRep, nbWeight);
            JSONObject data = ((MainActivity) getActivity()).getData();
            System.out.print(data);
            JSONObject data2 = ((MainActivity) getActivity()).getData();
            System.out.println(data2);
        //}
        //catch (JSONException e) {
          //  System.out.print(e);
        //}
    }

    public void displayPreviousExercices(View v ){
        JSONObject data = ((MainActivity) getActivity()).getData();
        try{
            JSONArray session = data.getJSONArray(((MainActivity) getActivity()).getActualSession());
            JSONObject exercices =session.getJSONObject(0);
            JSONArray sets = exercices.getJSONArray(((MainActivity) getActivity()).getActualExercice());
            System.out.println(sets);

            for ( int i=0; i<sets.length(); i++ ) {
                //System.out.println( sets[ i ] );
                TextView textView = new TextView((MainActivity) getActivity());
                textView.setText(sets.getJSONObject(i).getString("weight") + "kg x " + sets.getJSONObject(i).getString("reps"));
                this.dynamicLayout.addView(textView);
            }

        }
        catch (JSONException e){
            System.out.println(e);
        }
    }
}