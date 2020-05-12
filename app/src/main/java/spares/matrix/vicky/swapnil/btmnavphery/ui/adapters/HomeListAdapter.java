package spares.matrix.vicky.swapnil.btmnavphery.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Vegetable;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.GeneralFood;

import static android.graphics.Color.RED;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {


    ArrayList<HashMap<String, String>> mArray;
    private LayoutInflater inflater;
    private List<GeneralFood> regularFoods;
    private Context context;
    public TextView textView;


    public HomeListAdapter(List<GeneralFood> regularFoods, int vertical_recyclerview, Context context) {
        this.context = context;
        this.regularFoods = regularFoods;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;
        TextView txtTitle, txtquantity, txtmrp, txtprice;
        Button buttond;

        Button add1, sub1;
        TableLayout tableLayout;
        TextView disp;
        //  static int numtest = 1;
        LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);
            imgBanner = v.findViewById(R.id.img1);
            txtTitle = v.findViewById(R.id.textTitle);
            txtquantity = v.findViewById(R.id.textquantity);
            txtmrp = v.findViewById(R.id.textmrp);
            txtprice = v.findViewById(R.id.textprice);
            buttond = v.findViewById(R.id.buttondta);
            add1 = v.findViewById(R.id.bt2);
            sub1 = v.findViewById(R.id.bt1);
            disp = v.findViewById(R.id.tex1);


            buttond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttond.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    tableLayout.setVisibility(View.VISIBLE);
                    linearLayout.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
                }
            });
/*
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numtest ++;
                String s= String.valueOf(numtest);
                disp.setText("" + s);
            }
        });

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=disp.getText().toString().trim();

                int n1= Integer.parseInt(n);
                if (n1 == 1){


                    buttond.setVisibility(View.VISIBLE);
                    tableLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);


                  *//*  disp.setText(s + "");*//*
                }
                if (numtest > 1) {
                    numtest--;
                    String s= String.valueOf(numtest);
                    disp.setText(s+ "");
                }
            }
        });
        }*/


        }


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTitle.setText(regularFoods.get(position).getProductName());
        holder.txtprice.setText((((regularFoods.get(position).getProductPrice()))) + " Taka");
        holder.txtmrp.setText((((regularFoods.get(position).getProductPriceBeforeDiscount()))) + " Taka");
        //  Picasso.get().load(regularFoods.get(position).getFilepath()).fit().into(holder.imgBanner);
      /*  Picasso.get().load(dataModelArrayList.get(position).getProductImage1()).into(holder.imgBanner);
        holder.txtTitle.setText(dataModelArrayList.get(position).getProductName());
        holder.txtmrp.setText(dataModelArrayList.get(position).getProductPriceBeforeDiscount());
        holder.txtprice.setText(dataModelArrayList.get(position).getProductPrice());*/
        //   Picasso.get().load(regularFoods.get(position).getProductImage1()).fit().into(holder.imgBanner);
    }

    @Override
    public int getItemCount() {
        return regularFoods.size();
    }

   /* @Override
    public int getItemCount()
    {
        return mArray.size();
    }*/

    @Override
    public HomeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_content, parent, false);
        return new HomeListAdapter.ViewHolder(view);


    }


}
