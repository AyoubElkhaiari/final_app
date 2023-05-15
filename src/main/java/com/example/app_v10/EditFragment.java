package com.example.app_v10;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    private ListView commandList;
    private Commands commands;
    private EditDialogFragment dialog;
    private String oldCommande;
    private Dialog dialogReset ;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public EditFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
        commands = Commands.getInstance(getActivity());
        dialogReset = new Dialog(requireContext(), R.style.dialogstyle) ;
        dialogReset.setContentView(R.layout.reset_dialog);


    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_edit, container, false);

        view.findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogReset.show();
            }
        });
        dialogReset.findViewById(R.id.resetAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commands cmds = Commands.getInstance(requireContext());
                cmds.resetCommands();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finishAffinity();

            }
        });
        getCommandList(view);
        setCommandList();
        setCommandListListner();
        return view;
    }
    private void getCommandList(View view){
        commandList=view.findViewById(R.id.commandList);
    }
    private void setCommandList(){
        Set<String> commandNames= commands.getAllCommands().keySet();
        ArrayList<String> namesList= new ArrayList<>();
        if (commandNames.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (String command : commandNames) {
                namesList.add(command);
                
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.text_list_item , namesList);
        commandList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        commandList.invalidateViews();
    }
    private void setCommandListListner(){
        commandList.setOnItemClickListener((adapterView, view1, i, l) -> {
            oldCommande = commandList.getItemAtPosition(i).toString();
            dialog = new EditDialogFragment();
            dialog.setOldCommand(oldCommande);
            dialog.show(getFragmentManager(),"edit");
            setCommandList();
        });
    }






}