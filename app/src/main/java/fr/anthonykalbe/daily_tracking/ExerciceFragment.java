package fr.anthonykalbe.daily_tracking;

import android.app.Dialog;
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
    private TextView title;
    private MainActivity mainActivity;

    Dialog popUpDialog;

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
        this.mainActivity = (MainActivity) getActivity();
        // Ajouter un OnClickListener à tous les boutons dans le fragment

        view.findViewById(R.id.buttonPopUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add a popup
                displayPopUp();
            }
        });
        addClickListenerToButtons(view);
        String getActualExercice = ((MainActivity) getActivity()).getActualExercice();
        dynamicLayout = view.findViewById(R.id.dynamicLayout);
        displayPreviousExercices(view);
        title = (TextView) view.findViewById(R.id.title);
        title.setText(getActualExercice);
        return view;
    }


    public void displayPopUp(){
        popUpDialog = new Dialog(getContext());
        popUpDialog.setContentView(R.layout.dialog_add_exercice);
        popUpDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popUpDialog.show();
        popUpDialog.findViewById(R.id.buttonCancelExercice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog.dismiss();
            }
        });
        popUpDialog.findViewById(R.id.buttonAddExercice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextWeight = popUpDialog.findViewById(R.id.editTextExercicePoids);
                EditText editTextReps = popUpDialog.findViewById(R.id.editTextExerciceReps);
                String exerciceWeight = editTextWeight.getText().toString();
                String exerciceReps = editTextReps.getText().toString();
                editTextWeight.setText("");
                editTextReps.setText("");
                try{
                    ((MainActivity) getActivity()).setSet(exerciceWeight, exerciceReps);
                    displayPreviousExercices(v);
                }
                catch (JSONException e){
                    System.out.println(e);
                }
                popUpDialog.dismiss();

            }
        });
    }

    private void addClickListenerToButtons(View parentView) {
        System.out.print("salut bg");
    }



    public void displayPreviousExercices(View v ){
        JSONObject data = ((MainActivity) getActivity()).getData();
        System.out.println(data);
        //remove all element from dynamicLayout
        dynamicLayout.removeAllViews();

        try{
            JSONArray session = data.getJSONArray(((MainActivity) getActivity()).getActualSession());
            int target = 0;
            for(int i=0; i<session.length(); i++){
                if (session.getJSONObject(i).has(((MainActivity) getActivity()).getActualExercice())){
                    System.out.println("exercice found");
                    target = i;
                }
            }
            JSONObject exercices =session.getJSONObject(target);
            JSONArray sets = exercices.getJSONArray(((MainActivity) getActivity()).getActualExercice());
            Integer length = sets.length();
            if(sets.length() == 0){
                TextView textView = new TextView((MainActivity) getActivity());
                textView.setText("No sets for this exercice");
                dynamicLayout.addView(textView);
            }else{
                for ( int i=0; i<sets.length(); i++ ) {
                    TextView textView = new TextView((MainActivity) getActivity());
                    textView.setText(sets.getJSONObject(i).getString("weight") + "kg x " + sets.getJSONObject(i).getString("reps"));
                    dynamicLayout.addView(textView);
                }
            }


        }
        catch (JSONException e){
            System.out.println(e);
        }
    }
}