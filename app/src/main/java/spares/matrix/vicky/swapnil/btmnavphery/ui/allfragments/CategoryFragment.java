package spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.activites.HomeActivity;
import spares.matrix.vicky.swapnil.btmnavphery.ui.adapters.CategoryAdapter;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Fruits;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Grains;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Others;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Vegetable;
import spares.matrix.vicky.swapnil.btmnavphery.ui.constants.Constant;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.Category;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.Food;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.RetrofitClient;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.ServiceApi;

public class CategoryFragment extends Fragment  {
    ImageButton vege, frui, grain, spices;
    Vegetable vegetable = new Vegetable();
    public static ConstraintLayout drawerLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    Fruits fruits = new Fruits();
    Grains grains1 = new Grains();
    Others others1 = new Others();
    Toolbar toolbar1;
    TextView text12;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View V1 = inflater.inflate(R.layout.category_fragment, container, false);
      //  text12=V1.findViewById(R.id.text12);
        toolbar1 =V1.findViewById(R.id.toolbar);
        drawerLayout = V1.findViewById(R.id.homelayout2);

        recyclerView=V1.findViewById(R.id.mRecyclerViewOfr);
        toolbar1.setNavigationIcon(R.drawable.button_back_all);
        toolbar1.setTitle("Category");
      //  text12.setText("");
toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
    }
});
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


        return V1;


    }

}
