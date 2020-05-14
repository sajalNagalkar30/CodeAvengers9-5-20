package spares.matrix.vicky.swapnil.btmnavphery.ui.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Vegetable;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.GeneralFood;


import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.cartFoods;
import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.checkList;
import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.checkMap;
import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.map1;


public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder> {

    public static List<GeneralFood> regularFoods;
    private Context context;


    public static class VerticalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgBanner;
        TextView txtTitle, txtquantity, txtmrp, txtprice;
        Button buttond;
        IncreseadapterClicklisner listener;

        public void setListener(IncreseadapterClicklisner listener) {
            this.listener = listener;
        }
        Button add1,sub1;
        ConstraintLayout c1add,c2elg;
        TextView disp;

        LinearLayout linearLayout,linearLayout1;
        public VerticalViewHolder(View v) {
            super(v);
            imgBanner = v.findViewById(R.id.img1);
            txtTitle = v.findViewById(R.id.textTitle);
            txtquantity = v.findViewById(R.id.textquantity);
            txtmrp = v.findViewById(R.id.textmrp);
            add1 = v.findViewById(R.id.bt2);
            sub1 = v.findViewById(R.id.bt1);
            txtprice = v.findViewById(R.id.textprice);
            buttond = v.findViewById(R.id.buttondta);
           /* linearLayout = v.findViewById(R.id.vertical_parent_layout);
            linearLayout1=v.findViewById(R.id.layoutL12);*/

            disp=v.findViewById(R.id.tex1);
            c1add=v.findViewById(R.id.contraint1);
            c2elg=v.findViewById(R.id.constraint2);
            txtmrp.setPaintFlags(txtmrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            add1.setOnClickListener(this);
            sub1.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if(v == sub1)
            {
                listener.onCalculatePrice(v,getAdapterPosition(),true,false);
            }
            else if(v == add1)
            {
                listener.onCalculatePrice(v,getAdapterPosition(),false,false);
            }
            else
            {
                listener.onCalculatePrice(v,getAdapterPosition(),true,true);
            }
        }
    }

    public VerticalAdapter(List<GeneralFood> regularFoods, int vertical_recyclerview, Context context){
        this.context = context;
        this.regularFoods = regularFoods;
    }

    @NonNull
    @Override
    public VerticalAdapter.VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_content, parent, false);
        return new VerticalAdapter.VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VerticalAdapter.VerticalViewHolder holder, final int position) {
        holder.txtTitle.setText(regularFoods.get(position).getProductName());
        holder.txtprice.setText((((regularFoods.get(position).getProductPrice()))));
        holder.txtmrp.setText("Rs "+(((regularFoods.get(position).getProductPriceBeforeDiscount()))) );
        holder.txtquantity.setText((((regularFoods.get(position).getQuantity()))) );
        holder.disp.setText(String.valueOf(regularFoods.get(position).getCount()));

        //  Picasso.get().load(regularFoods.get(position).getFilepath()).fit().into(holder.imgBanner);
        Glide.with(context)
                .load((regularFoods.get(position).getFilepath()))
                .fitCenter()
                .into(holder.imgBanner);



        holder.buttond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  boolean s = false;
                addItem(cartFoods,s);
*/
                GeneralFood item = regularFoods.get(position);

                if(!checkList(cartFoods,item)){

                    if(!checkMap(map1,regularFoods.get(position).getId()))
                    {
                        Toast.makeText(context, "Item "+regularFoods.get(position).getProductName()+"Added In cart", Toast.LENGTH_SHORT).show();
                        cartFoods.add(regularFoods.get(position));
                        map1.put(regularFoods.get(position).getId(),cartFoods);
                    }
                    else
                    {
                        Toast.makeText(context, "Item Is already exist In Cart", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(context, "Item Is already exist In Cart", Toast.LENGTH_SHORT).show();
                }

            }});
       /*  if(!cartFoods.contains(regularFoods.get(position)))
        {
            holder.c2elg.setVisibility(View.GONE);
            holder.c1add.setVisibility(View.VISIBLE);
        }*/

   /*     holder.setListener(new IncreseadapterClicklisner() {
            @Override
            public void onCalculatePrice(View view, int position, boolean isDecrese, boolean isDelete) {
                if (!isDelete) {
                    if (isDecrese) {
                        if (cartFoods.get(position).getCount() > 1) {
                            cartFoods.get(position).setCount(cartFoods.get(position).getCount() - 1);
                            holder.disp.setText(String.valueOf(regularFoods.get(position).getCount()));


                        }
                        else if(cartFoods.get(position).getCount() == 1){
                            holder.c2elg.setVisibility(View.GONE);
                            holder.c1add.setVisibility(View.VISIBLE);
                        }


                    } else {
                        if (cartFoods.get(position).getCount() < 99) {
                            cartFoods.get(position).setCount(cartFoods.get(position).getCount() + 1);
                            holder.disp.setText(String.valueOf(regularFoods.get(position).getCount()));

                        }
                    }

                } else {




                }
            }
        });*/


    }




  /*  @Override
    public int getItemCount() {
        return 0;
    }*/

    @Override
    public  int getItemCount() {

        return regularFoods.size();
    }
}
