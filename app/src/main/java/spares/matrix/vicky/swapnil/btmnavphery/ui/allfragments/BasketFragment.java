package spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.adapters.CartAdapter;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.GeneralFood;

import static android.view.View.GONE;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;


public class BasketFragment extends Fragment {
    RecyclerView recyclerviewCart;
    public static TextView cartPrice;
    public static Map<String, List<GeneralFood>> map1 = new HashMap<>();//This is one instance of the  map you want to store in the above list.

    public static List<GeneralFood> cartFoods = new ArrayList<>();

    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.basket_fragment, container, false);

        cartPrice = v.findViewById(R.id.cart_price);
        cartPrice.setText(Double.toString(grandTotal(cartFoods)));
        /*cartPrice.setText("0");*/
        recyclerviewCart = v.findViewById(R.id.cart_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerviewCart.setLayoutManager(linearLayoutManager);
        recyclerviewCart.setNestedScrollingEnabled(false);
        recyclerviewCart.setAdapter(new CartAdapter(map1, R.layout.data_cart, getContext()));

        if (cartPrice.getText().length()==0)
        {
            Toast.makeText(getActivity(), "Changed", Toast.LENGTH_SHORT).show();
        }


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

/*    public static int updateValue(List< GeneralFood> cartFoods,int s)
    {
        for(int i = 0 ; i < cartFoods.size(); i++) {
            int s= Integer.parseInt(cartFoods.get(i).getProductPrice());
            totalPrice += s;
        }
    return totalPrice;
    }*/

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
