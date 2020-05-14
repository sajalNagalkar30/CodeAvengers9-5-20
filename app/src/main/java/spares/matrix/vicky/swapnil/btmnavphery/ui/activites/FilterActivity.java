package spares.matrix.vicky.swapnil.btmnavphery.ui.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.FilterRefine;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.FilterSort;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.NotifiAlert;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.NotifiOffers;

public class FilterActivity extends AppCompatActivity {

    FilterRefine filterRefine_frag;
    FilterSort filterSort_frag;



    public FilterActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        final RadioButton refineby=findViewById(R.id.refineby);

      RadioGroup radioGroup = findViewById(R.id.filter_rgrp);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                Fragment fragment1 = new FilterRefine();
                Fragment fragment2 = new FilterSort();

                if(refineby.isChecked())
                {

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.output, fragment1);
                    transaction.commit();

                } else
                {

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.output, fragment2);
                    transaction.commit();
                }

            }
        });


    }
}