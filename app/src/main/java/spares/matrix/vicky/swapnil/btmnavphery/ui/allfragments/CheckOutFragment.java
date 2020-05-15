package spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.activites.HomeActivity;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Vegetable;

import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.cartFoods;
import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.cartPrice;
import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.grandPriviousTotal;
import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.grandTotal;


public class CheckOutFragment extends Fragment {
   TextView subcheck,savemoneych,priceTotalch,check_price,check_saved;
    Toolbar toolbar1;
    TextView text12;
    public static CheckOutFragment newInstance() {

        return new CheckOutFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_check_out, container, false);
        toolbar1 =v.findViewById(R.id.toolbar);
       // text12=v.findViewById(R.id.text12);
        subcheck=v.findViewById(R.id.subtotal_price);
        savemoneych=v.findViewById(R.id.checkSavedMoney);
        priceTotalch=v.findViewById(R.id.priceTotal);
        check_price=v.findViewById(R.id.check_cart_price);
        check_saved=v.findViewById(R.id.cart_savedmoney);


        priceTotalch.setText(Double.toString(grandTotal(cartFoods)));
        subcheck.setText(Double.toString(grandTotal(cartFoods)));
        savemoneych.setText(Double.toString(grandPriviousTotal(cartFoods)));

        check_saved.setText(Double.toString(grandPriviousTotal(cartFoods)));
        check_price.setText(Double.toString(grandTotal(cartFoods)));

        toolbar1.setNavigationIcon(R.drawable.button_back_all);
        toolbar1.setTitle("Checkout");
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.cartconstraints, new BasketFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });



        return v;
    }
}
