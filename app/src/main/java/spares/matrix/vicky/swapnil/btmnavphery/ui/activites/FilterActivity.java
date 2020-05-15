package spares.matrix.vicky.swapnil.btmnavphery.ui.activites;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.CheckOutFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.FilterRefine;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.FilterSort;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.NotifiAlert;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.NotifiOffers;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Vegetable;

public class FilterActivity extends AppCompatActivity {

    FilterRefine filterRefine_frag;
    FilterSort filterSort_frag;

    Toolbar toolbar1;
    TextView text12;
    public FilterActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        toolbar1 =findViewById(R.id.toolbar);
      //  text12=findViewById(R.id.text12);
        final RadioButton refineby=findViewById(R.id.refineby);
      toolbar1.setNavigationIcon(R.drawable.button_back_all);
        toolbar1.setTitle("Filter");

       /* ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
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