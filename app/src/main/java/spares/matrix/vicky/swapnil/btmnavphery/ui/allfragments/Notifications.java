package spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import spares.matrix.vicky.swapnil.btmnavphery.R;

public class Notifications extends Fragment
{

    public Notifications() {
        // Required empty public constructor
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       View root = inflater.inflate(R.layout.notifications_fragment, container, false);

       final RadioButton alert=root.findViewById(R.id.notification_alerts);

        RadioGroup radioGroup = root.findViewById(R.id.notification_rgrp);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                Fragment fragment1 = new NotifiAlert();
                Fragment fragment2 = new NotifiOffers();

                if(alert.isChecked())
                {
                    FragmentManager manager = getParentFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.output, fragment1);
                    transaction.commit();
                    transaction.hide(fragment1);

                } else
                    {

                        FragmentManager manager = getParentFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.output, fragment2);
                        transaction.commit();
                }

            }
        });

        return root;
    }

}
