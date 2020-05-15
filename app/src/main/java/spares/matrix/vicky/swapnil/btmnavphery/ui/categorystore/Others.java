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
import android.widget.Button;
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
import spares.matrix.vicky.swapnil.btmnavphery.ui.activites.FilterActivity;
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

public class Others extends Fragment {

    public static String description1;
    RecyclerView mRecyclerview;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    TextView text12;
    TextView otheritemcount;
    FlipperLayout flipperLayout;
    Toolbar toolbar1;
    ArrayList<HashMap<String, String>> arrayListNews;

    Button buttonFilterOther;

    public static Others newInstance() {
        return new Others();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v3=inflater.inflate(R.layout.others_fragment, container, false);
        mRecyclerview=v3.findViewById(R.id.mRecyclerViewothers);
        mLayoutManager=new LinearLayoutManager(getContext());


        buttonFilterOther=v3.findViewById(R.id.btnFilterOthers);
        buttonFilterOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), FilterActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerview.setLayoutManager(mLayoutManager);
      //  callAPI();
        otheritemcount=v3.findViewById(R.id.other_itemcount);
       /* */
      //  text12=v3.findViewById(R.id.text12);
        toolbar1 =v3.findViewById(R.id.toolbar);
        flipperLayout = v3.findViewById(R.id.flipperoth);
        int imagesDrawable[] = {R.drawable.spices1, R.drawable.spices2};
        for (int i = 0; i < imagesDrawable.length; i++) {

            FlipperView flipperView = new FlipperView(getContext());
            flipperView.setImageDrawable(imagesDrawable[i]);
            flipperLayout.addFlipperView(flipperView);

        }


        toolbar1.setNavigationIcon(R.drawable.button_back_all);
        toolbar1.setTitle("Others / Spices");
       // text12.setText("Others");
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });
        ServiceApi retrofitService = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);

        Call<Food> call = retrofitService.getSpicesFoods();
        call.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, retrofit2.Response<Food> response) {
            /*    List<GeneralFood> popularFoods = response.body().getPopularFood();
                recyclerViewHorizontal.setAdapter(new HorizontalAdapter(popularFoods, R.layout.recyclerview_horizontal, MainActivity.this));
*/
                List<GeneralFood> regularFoods = response.body().getSpices();
                int itemcout=regularFoods.size();
                otheritemcount.setText(itemcout+" "+"Items");
                mRecyclerview.setNestedScrollingEnabled(false);
                mRecyclerview.setAdapter(new VerticalAdapter(regularFoods, R.layout.data_content, getContext()));
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {

            }
        });
        return v3;
    }
   /* private void callAPI() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //String url = "https://api.myjson.com/bins/1eits2";

        //String url = "https://swapnilz.000webhostapp.com/Pherywala/json/Fruits.json";

        //String url = "https://swapnilz.000webhostapp.com/Pherywala/json/Grains.json";

        String url = "https://swapnilz.000webhostapp.com/Pherywala/json/Spices.json";

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
            //JSONArray arrayHeadlines = objResponse.getJSONArray("Grains");//
            //JSONArray arrayHeadlines = objResponse.getJSONArray("Spices");
            JSONArray arrayHeadlines = objResponse.getJSONArray("Spices");
            arrayListNews = new ArrayList<>();

            for (int i = 0; i < arrayHeadlines.length(); i++) {
                JSONObject objItem = arrayHeadlines.getJSONObject(i);
                String title = objItem.getString("title");
                String quantity = objItem.getString("quantity");
                String imgUrl = objItem.getString("imgUrl");
                String mrp = objItem.getString("mrp");
                String price = objItem.getString("price");

                HashMap<String, String> map = new HashMap<>();
                map.put("title", title);
                map.put("quantity", quantity);
                map.put("url", imgUrl);
                map.put("mrp", mrp);
                map.put("price", price);
                arrayListNews.add(map);
            }

            //set adapter
           *//* mAdapter = new HomeListAdapter(getContext(), arrayListNews);
            mRecyclerview.setAdapter(mAdapter);*//*

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/

}
