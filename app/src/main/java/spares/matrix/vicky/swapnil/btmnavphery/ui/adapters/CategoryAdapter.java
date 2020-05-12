package spares.matrix.vicky.swapnil.btmnavphery.ui.adapters;


import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.HomeFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Fruits;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Grains;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Others;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Vegetable;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.Category;


import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.HomeFragment.drawerLayout;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VerticalViewHolder> {

    private List<Category> regularFoods;
    private Context context;
    Vegetable vegetable = new Vegetable();
    Fruits fruits = new Fruits();
    Grains grains1 = new Grains();
    Others others1 = new Others();

    Bundle bundle = new Bundle();

    public static class VerticalViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout verticalLayout;
        TextView regularTitle;
        TextView regularPrice;
        ImageView regularImage;
        Button regularPlus;

        public VerticalViewHolder(View itemView) {
            super(itemView);

            verticalLayout = itemView.findViewById(R.id.categoryjk);
            regularTitle = itemView.findViewById(R.id.textcategory);
            regularImage = itemView.findViewById(R.id.imgcate);

            //   regularPlus = itemView.findViewById(R.id.cd_sbi);

        }


    }

    public CategoryAdapter(List<Category> regularFoods, int vertical_recyclerview, Context context) {
        this.context = context;
        this.regularFoods = regularFoods;
    }

    @NonNull
    @Override
    public CategoryAdapter.VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_category, parent, false);

        return new CategoryAdapter.VerticalViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.VerticalViewHolder holder, final int position) {


        holder.regularTitle.setText(regularFoods.get(position).getTitle());
        // Picasso.get().load(regularFoods.get(position).getImgUrl()).fit().into(holder.regularImage);
        Glide.with(context)
                .load(regularFoods.get(position).getImgUrl())
                .centerCrop()
                .into(holder.regularImage);


        holder.verticalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 2:

                        change_Fragment(vegetable);
                        Animation developmentv = AnimationUtils.loadAnimation(context, R.anim.custom_animaion);
                        drawerLayout.startAnimation(developmentv);
                        break;

                    case 0:
                        change_Fragment(fruits);
                        Animation developmentv1 = AnimationUtils.loadAnimation(context, R.anim.custom_animaion);
                        drawerLayout.startAnimation(developmentv1);
                        break;

                    case 1:
                        change_Fragment(grains1);
                        Animation developmentv2 = AnimationUtils.loadAnimation(context, R.anim.custom_animaion);
                        drawerLayout.startAnimation(developmentv2);
                        break;

                    case 3:
                        change_Fragment(others1);
                        Animation developmentv3 = AnimationUtils.loadAnimation(context, R.anim.custom_animaion);
                        drawerLayout.startAnimation(developmentv3);
                        break;
                    default:
                        break;

                }

            }
        });
        //   holder.verticalLayout.setOnClickListener(this);
        // holder.verticalLayout.setOnClickListener(this);
          /*  @Override
            public void onClick(View v) {

            }
        });*/

    }
  /*  @Override
    public int getItemCount() {
        return 0;
    }*/

    @Override
    public int getItemCount() {
        return regularFoods.size();
    }

    void change_Fragment(Fragment fragment) {
        FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
       /* FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction ft =fragmentManager.beginTransaction();*/
        ft.addToBackStack(null);
        ft.add(R.id.homelayout2, fragment);
        ft.commit();
    }


}

