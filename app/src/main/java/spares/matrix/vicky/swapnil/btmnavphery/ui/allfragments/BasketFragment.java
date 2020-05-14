package spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.activites.HomeActivity;
import spares.matrix.vicky.swapnil.btmnavphery.ui.adapters.CartAdapter;
import spares.matrix.vicky.swapnil.btmnavphery.ui.fragment.RegistrationFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.fragment.Starting;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.GeneralFood;

import static android.view.View.GONE;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;


public class BasketFragment extends Fragment {
    RecyclerView recyclerviewCart;
    Context context;
    public static TextView cartPrice;
    public static TextView cartPreviousPrice;
    public static Map<String, List<GeneralFood>> map1 = new HashMap<>();//This is one instance of the  map you want to store in the above list.
    Toolbar toolbar1;
    TextView text12;
    Button checkout;
    ConstraintLayout constraintLayout;
    public static List<GeneralFood> cartFoods = new ArrayList<>();

    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.basket_fragment, container, false);
        toolbar1 =v.findViewById(R.id.toolbar);
        context=getActivity();
        constraintLayout=v.findViewById(R.id.cartconstraints);
        text12=v.findViewById(R.id.text12);
        toolbar1.setNavigationIcon(R.drawable.button_back_all);
        text12.setText("Your Basket");
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                } else {
                    getFragmentManager().beginTransaction().addToBackStack(null);
                }
                //getFragmentManager().popBackStackImmediate();
               // getFragmentManager().popBackStack();
              //  getContext().onBackPressed();
               /// startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });
        cartPrice = v.findViewById(R.id.cart_price);
        cartPreviousPrice=v.findViewById(R.id.savedmoney);
        checkout=v.findViewById(R.id.checkoutbtn);
        cartPreviousPrice.setText(Double.toString(grandPriviousTotal(cartFoods)));
        cartPrice.setText(Double.toString(grandTotal(cartFoods)));
        /*cartPrice.setText("0");*/
        recyclerviewCart = v.findViewById(R.id.cart_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerviewCart.setLayoutManager(linearLayoutManager);
        recyclerviewCart.setNestedScrollingEnabled(false);
        recyclerviewCart.setAdapter(new CartAdapter(map1, R.layout.data_cart, getContext()));

checkout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.cartconstraints, new CheckOutFragment())
                .addToBackStack(null)
                .commit();
    }
});

        return v;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

      /*  if (id == R.id.order_button){
            Intent intent = new Intent(CartActivity.this, OrderActivity.class);
            startActivity(intent);
        }*/

        return super.onOptionsItemSelected(item);
    }

    public static int grandTotal(List<GeneralFood> cartFoods) {


        int totalPrice = 0;
        for (int i = 0; i < cartFoods.size(); i++) {
            int p = Integer.parseInt(cartFoods.get(i).getProductPrice());
            int c = cartFoods.get(i).getCount();
            //      Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            totalPrice += p * c;
        }


        return totalPrice;

    }
    public static int grandPriviousTotal(List<GeneralFood> cartFoods)
    {
        int totalPrice = 0;
        for (int i = 0; i < cartFoods.size(); i++) {
            int p = Integer.parseInt(cartFoods.get(i).getProductPriceBeforeDiscount());
            int c = cartFoods.get(i).getCount();
            //      Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            totalPrice += p * c;
        }
        totalPrice=totalPrice - grandTotal(cartFoods);


        return totalPrice;
    }

/*    public static int updateValue(List< GeneralFood> cartFoods,int s)
    {
        for(int i = 0 ; i < cartFoods.size(); i++) {
            int s= Integer.parseInt(cartFoods.get(i).getProductPrice());
            totalPrice += s;
        }
    return totalPrice;
    }*/
    public static void pricePreviousAdjust(){
        cartPreviousPrice.setText(String.valueOf(grandPriviousTotal(cartFoods)));

    }
    public static void priceAdjust() {
        cartPrice.setText(String.valueOf(grandTotal(cartFoods)));
    }

    public static boolean checkList(List<GeneralFood> cartFoods, GeneralFood item) {
        boolean b = false;
        b = cartFoods.contains(item);
        return b;
    }

    public static boolean checkMap(Map<String, List<GeneralFood>> map1, String itemId) {

        boolean c = false;
        c = map1.containsKey(itemId);
        return c;
    }
}
