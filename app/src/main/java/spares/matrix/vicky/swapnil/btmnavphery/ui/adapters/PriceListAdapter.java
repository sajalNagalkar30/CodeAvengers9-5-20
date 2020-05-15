
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
        TextView credit_debittxt,instatdis,disco,shop_fordi,codedi,valid,termc;
        ImageView regularImage1;
        Button regularPlus;

        public VerticalViewHolder(View itemView) {
            super(itemView);



            regularImage1 = itemView.findViewById(R.id.img);
            disco = itemView.findViewById(R.id.discount_b);
            credit_debittxt=itemView.findViewById(R.id.credit_debit_sbi);
            instatdis=itemView.findViewById(R.id.instant);
            shop_fordi=itemView.findViewById(R.id.shop_for);
            codedi=itemView.findViewById(R.id.codefor);
            valid=itemView.findViewById(R.id.validityof);
            termc=itemView.findViewById(R.id.tc);
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
        holder.instatdis.setText(regularFoods.get(position).getInstant());
        holder.disco.setText((((regularFoods.get(position).getDiscount()))));
        holder.credit_debittxt.setText(regularFoods.get(position).getCard());
        holder.shop_fordi.setText(regularFoods.get(position).getShop());
        holder.codedi.setText(regularFoods.get(position).getCode());
        holder.valid.setText(regularFoods.get(position).getVaild());
        holder.termc.setText(regularFoods.get(position).getTc());
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
