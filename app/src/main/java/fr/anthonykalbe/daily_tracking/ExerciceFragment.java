package fr.anthonykalbe.daily_tracking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciceFragment extends Fragment {

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

        return view;
    }

    private void addClickListenerToButtons(View parentView) {
        if (parentView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parentView;
            int childCount = viewGroup.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View childView = viewGroup.getChildAt(i);

                // Si le childView est un bouton, ajouter un OnClickListener
                if (childView instanceof Button) {
                    Button button = (Button) childView;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Action à effectuer lors du clic sur un bouton
                            handleButtonClick(button);
                        }
                    });
                }

                // Si le childView est une autre ViewGroup, récursion
                if (childView instanceof ViewGroup) {
                    addClickListenerToButtons(childView);
                }
            }
        }
    }

    private void handleButtonClick(Button button){
        Fragment NextFragment = new detailFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment, NextFragment).addToBackStack(null).commit();
    }
}