package spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.adapters.CategoryAdapter;
import spares.matrix.vicky.swapnil.btmnavphery.ui.adapters.PriceListAdapter;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Fruits;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Grains;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Others;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Vegetable;
import spares.matrix.vicky.swapnil.btmnavphery.ui.constants.Constant;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.Category;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.Food;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.Offer;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.RetrofitClient;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.ServiceApi;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;


public class HomeFragment extends Fragment {
    private AppBarConfiguration mAppBarConfiguration;
    ImageButton vege, frui, grain, spices;
    final Vegetable vegetable = new Vegetable();
    public static ConstraintLayout drawerLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
String itemid;

    Fruits fruits = new Fruits();
    Grains grains1 = new Grains();
    Others others1 = new Others();
    FlipperLayout flipperLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        drawerLayout = root.findViewById(R.id.homelayout2);
        flipperLayout = root.findViewById(R.id.flipper);
recyclerView=root.findViewById(R.id.mRecyclerViewOfr);
        int imagesDrawable[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
        for (int i = 0; i < imagesDrawable.length; i++) {

            FlipperView flipperView = new FlipperView(getContext());
            flipperView.setImageDrawable(imagesDrawable[i]);
            flipperLayout.addFlipperView(flipperView);

        }
        mLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        //    callAPI();
        ServiceApi retrofitService = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);

        Call<Food> call = retrofitService.getCategoryFood();
        call.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, retrofit2.Response<Food> response) {
            /*    List<GeneralFood> popularFoods = response.body().getPopularFood();
                recyclerViewHorizontal.setAdapter(new HorizontalAdapter(popularFoods, R.layout.recyclerview_horizontal, MainActivity.this));
*/
                List<Category> regularFoods = response.body().getCategory();
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setAdapter(new CategoryAdapter(regularFoods, R.layout.data_category, getContext()));
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {

            }
        });



        //String getArgument = getArguments().getString("edttext");
       /* int integer= Integer.parseInt(getArgument);
        datacome(integer);*/
        //Get pass data with its key value
       /* frui.setOnClickListener(this);
        spices.setOnClickListener(this);
        grain.setOnClickListener(this);
        vege.setOnClickListener(this);*/


        return root;


    }


    private void datacome(Integer integer) {



    }


}




