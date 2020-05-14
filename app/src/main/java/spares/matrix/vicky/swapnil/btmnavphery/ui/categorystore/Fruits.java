package spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.activites.HomeActivity;
import spares.matrix.vicky.swapnil.btmnavphery.ui.adapters.HomeListAdapter;
import spares.matrix.vicky.swapnil.btmnavphery.ui.adapters.VerticalAdapter;
import spares.matrix.vicky.swapnil.btmnavphery.ui.constants.Constant;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.Food;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.GeneralFood;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.RetrofitClient;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.ServiceApi;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class Fruits extends Fragment {

    Toolbar toolbar1;
    TextView text12;
    TextView fruititemcount;
    FlipperLayout flipperLayout2;
    public static String description1;
    RecyclerView mRecyclerview;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<HashMap<String, String>> arrayListNews;


    public static Fruits newInstance() {
        return new Fruits();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v2=inflater.inflate(R.layout.fruits_fragment, container, false);
        mRecyclerview=v2.findViewById(R.id.mRecyclerViewfru);
        mLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
fruititemcount=v2.findViewById(R.id.fruit_itemcount);

        text12=v2.findViewById(R.id.text12);
        flipperLayout2 = v2.findViewById(R.id.flipperfru);
        toolbar1 =v2.findViewById(R.id.toolbar);
        int imagesDrawable[] = {R.drawable.image4, R.drawable.fru};
        for (int i = 0; i < imagesDrawable.length; i++) {

            FlipperView flipperView = new FlipperView(getContext());
            flipperView.setImageDrawable(imagesDrawable[i]);
            flipperLayout2.addFlipperView(flipperView);

        }


        toolbar1.setNavigationIcon(R.drawable.button_back_all);
        text12.setText("Fruits");
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });
        ServiceApi retrofitService = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);

        Call<Food> call = retrofitService.getFruitFoods();
        call.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, retrofit2.Response<Food> response) {
            /*    List<GeneralFood> popularFoods = response.body().getPopularFood();
                recyclerViewHorizontal.setAdapter(new HorizontalAdapter(popularFoods, R.layout.recyclerview_horizontal, MainActivity.this));
*/
                List<GeneralFood> regularFoods = response.body().getFruits();
                 int itemcout=regularFoods.size();
                 fruititemcount.setText(itemcout+" "+"Items");
                mRecyclerview.setNestedScrollingEnabled(false);
                mRecyclerview.setAdapter(new VerticalAdapter(regularFoods, R.layout.data_content, getContext()));
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {

            }
        });
        return v2;
    }


   /* private void callAPI() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //String url = "https://api.myjson.com/bins/1eits2";

        String url = "https://swapnilz.000webhostapp.com/Pherywala/json/Fruits2.json";

        //String url = "https://swapnilz.000webhostapp.com/Pherywala/json/Grains.json";

        //String url = "https://swapnilz.000webhostapp.com/Pherywala/json/Spices.json";

       // String url = "https://swapnilz.000webhostapp.com/Pherywala/json/Vegetables.json";


        //String url = "https://api.myjson.com/bins/w3hge";
        //String url = "https://chandansatyendraprasad.000webhostapp.com/tests";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("=====", "=========response:" + response);
                        parseAPIResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void parseAPIResponse(String response) {
        try {
            JSONObject objResponse = new JSONObject(response);
            //JSONArray arrayHeadlines = objResponse.getJSONArray("Fruits");
            //JSONArray arrayHeadlines = objResponse.getJSONArray("Grains");
            //JSONArray arrayHeadlines = objResponse.getJSONArray("Spices");
            JSONArray arrayHeadlines = objResponse.getJSONArray("Fruits");
            arrayListNews = new ArrayList<>();

            for (int i = 0; i < arrayHeadlines.length(); i++) {
                JSONObject objItem = arrayHeadlines.getJSONObject(i);
                String productName = objItem.getString("productName");
                String quantity = objItem.getString("quantity");
                String productImage1 = objItem.getString("productImage1");
                String productPriceBeforeDiscount = objItem.getString("productPriceBeforeDiscount");
                String productPrice = objItem.getString("productPrice");

                HashMap<String, String> map = new HashMap<>();
                map.put("title", productName);
                map.put("quantity", quantity);
                map.put("url", productImage1);
                map.put("mrp", productPriceBeforeDiscount);
                map.put("price", productPrice);
                arrayListNews.add(map);
            }

            //set adapter
        *//*    mAdapter = new HomeListAdapter(getContext(), arrayListNews);
            mRecyclerview.setAdapter(mAdapter);*//*

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/

}
