
package spares.matrix.vicky.swapnil.btmnavphery.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.Offer;

public class PriceListAdapter extends RecyclerView.Adapter<PriceListAdapter.VerticalViewHolder> {

    private List<Offer> regularFoods;
    private Context context;


    public static class VerticalViewHolder extends RecyclerView.ViewHolder{

        CardView verticalLayout;
        TextView regularTitle;
        TextView regularPrice;
        ImageView regularImage1;
        Button regularPlus;

        public VerticalViewHolder(View itemView) {
            super(itemView);


            regularTitle = itemView.findViewById(R.id.id_title_sbi);
            regularImage1 = itemView.findViewById(R.id.img);
            regularPrice = itemView.findViewById(R.id.sbi_dis);
            //   regularPlus = itemView.findViewById(R.id.cd_sbi);

        }
    }

    public PriceListAdapter(List<Offer> regularFoods, int vertical_recyclerview, Context context){
        this.context = context;
        this.regularFoods = regularFoods;
    }

    @NonNull
    @Override
    public PriceListAdapter.VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_offer, parent, false);
        return new PriceListAdapter.VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PriceListAdapter.VerticalViewHolder holder, final int position) {
        holder.regularTitle.setText(regularFoods.get(position).getInstant());
        holder.regularPrice.setText((((regularFoods.get(position).getDiscount()))) + " Taka");
        Glide.with(context)
                .load(regularFoods.get(position).getFilepath())
                .fitCenter()
                .into(holder.regularImage1);



    }

/*    @Override
    public int getItemCount() {
        return 0;
    }*/

    @Override
    public int getItemCount() {
        return regularFoods.size();
    }
}
