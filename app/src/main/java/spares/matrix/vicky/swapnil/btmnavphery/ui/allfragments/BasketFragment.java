package spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.activites.MainActivity;
import spares.matrix.vicky.swapnil.btmnavphery.ui.adapters.CartAdapter;
import spares.matrix.vicky.swapnil.btmnavphery.ui.constants.Constant;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.CartProduct;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.GeneralFood;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.RetrofitClient;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.ServiceApi;


public class BasketFragment extends Fragment {
    RecyclerView recyclerviewCart;
    Context context;
    public static TextView cartPrice;
    public static TextView cartPreviousPrice;
    public static Map<String, List<GeneralFood>> map1 = new HashMap<>();//This is one instance of the  map you want to store in the above list.
    Toolbar toolbar1;
    TextView text12;
    Button getid12;
    Button checkout;
    static ConstraintLayout cartfull;
    static ConstraintLayout cartempty;
    ConstraintLayout constraintLayout;
    public static List<GeneralFood> cartFoods = new ArrayList<>();
    public static Map<String,String> cartMaps= new HashMap<>();


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
        cartfull=v.findViewById(R.id.fullbasket);
       // getid12=v.findViewById(R.id.getuserid1);
        cartempty=v.findViewById(R.id.empty_basket);
        constraintLayout=v.findViewById(R.id.cartconstraints);
       // text12=v.findViewById(R.id.text12);
        toolbar1.setNavigationIcon(R.drawable.button_back_all);
        toolbar1.setTitle("Your Basket");
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                } else {
                    getFragmentManager().beginTransaction().addToBackStack(null);
                }

            }
        });
        final Map<Set<String>, Collection<String>> map12=new HashMap<>();
        map12.put(cartMaps.keySet(),cartMaps.values());


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
       doPostofData();
    }
});
if(!cartFoods.isEmpty()){
    cartempty.setVisibility(View.GONE);
    cartfull.setVisibility(View.VISIBLE);
}

        return v;
    }

    private void doPostofData() {
        Date currentTime = Calendar.getInstance().getTime();
//layoutchanage(cartFoods);
        String user_id=MainActivity.appPreference.getCRId();
        String payment_method=("COD");
        String order_date=String.valueOf((currentTime));
        String product_id= String.valueOf(cartMaps.entrySet());
     String quantity= (String.valueOf(cartMaps.values()));


        ServiceApi retrofitService = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);

        Call<CartProduct> userpro = retrofitService.dopost1(user_id,payment_method,product_id,quantity);

        userpro.enqueue(new Callback<CartProduct>() {
            @Override
            public void onResponse(Call<CartProduct> call, Response<CartProduct> response) {
                //Toast.makeText(context, ""+response.body().getResponse(), Toast.LENGTH_SHORT).show();
                if (response.body().getResponse().equals("Inserted")) {
                    Log.e("response", response.body().getResponse());

                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }
                else if(response.body().getResponse().equals("Try Again")){
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(context, "hiii", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CartProduct> call, Throwable t) {
                Toast.makeText(context, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
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


    public static void layoutchanage(Map<String, List<GeneralFood>> map1){
        if(map1.isEmpty()){
            cartempty.setVisibility(View.VISIBLE);
            cartfull.setVisibility(View.GONE);
        }

    }
    public static String senddata(String id, Integer count) {
        String s=(id+" "+count);
        return s;
    }



}
