package com.example.app_v10;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements EditDialogFragment.EditDialogListener{


    private Commands commands;
    private State state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        commands=Commands.getInstance(this);
        state=State.getInstance(this);
    }

    @Override
    public void onDialogPositiveClick(EditDialogFragment dialog) {
        // User touched the dialog's positive button
        String newCommand = dialog.getNewCommand();
        String oldCommand = dialog.getOldCommand();
        if(newCommand.equals("")){
            Toast.makeText(this,"Nouvelle commande invalide",Toast.LENGTH_LONG);
        }
        else{
            commands.setCommand(oldCommand,newCommand);
        }
    }

    @Override
    public void onDialogNegativeClick(EditDialogFragment dialog) {

    }




}