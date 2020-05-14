package spares.matrix.vicky.swapnil.btmnavphery.ui.adapters;

        import android.content.Context;
        import android.graphics.Paint;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TableLayout;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;

        import java.util.List;
        import java.util.Map;

        import spares.matrix.vicky.swapnil.btmnavphery.R;
        import spares.matrix.vicky.swapnil.btmnavphery.ui.activites.HomeActivity;
        import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment;
        import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Vegetable;
        import spares.matrix.vicky.swapnil.btmnavphery.ui.model.GeneralFood;



        import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.cartFoods;
        import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.grandPriviousTotal;
        import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.grandTotal;
        import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.priceAdjust;
        import static spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment.pricePreviousAdjust;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private CartAdapter mCartAdapter;
    int TotalPrice1=0;

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {

        holder.txtTitle.setText(cartFoods.get(position).getProductName());
        holder.txtprice.setText((((cartFoods.get(position).getProductPrice()))));
        holder.txtmrp.setText("Rs "+(((cartFoods.get(position).getProductPriceBeforeDiscount()))) );
        holder.txtquantity.setText((((cartFoods.get(position).getQuantity()))) );

        holder.disp.setText(String.valueOf(cartFoods.get(position).getCount()));
        //  Picasso.get().load(regularFoods.get(position).getFilepath()).fit().into(holder.imgBanner);
        Glide.with(context)
                .load((cartFoods.get(position).getFilepath()))
                .fitCenter()
                .into(holder.imgBanner);
        int cat= Integer.parseInt(cartFoods.get(position).getCategory());
            /*if(cat==2) {

            }*/
        switch (cat)
        {
            case 1:
                holder.tcat1.setText("Fruits");
                break;

            case 2:
                holder.tcat1.setText("Vegetable");
                break;

            case 3:
                holder.tcat1.setText("Grains");
                break;

            case 4:
                holder.tcat1.setText("Other Spices");
                break;
        }
        holder.setListener(new IncreseadapterClicklisner() {
            @Override
            public void onCalculatePrice(View view, int position, boolean isDecrese, boolean isDelete) {
                if(!isDelete)
                {
                    if(isDecrese)
                    {
                        if(cartFoods.get(position).getCount() > 1){
                            cartFoods.get(position).setCount(cartFoods.get(position).getCount()-1);
                            holder.disp.setText(String.valueOf(cartFoods.get(position).getCount()));
                             grandPriviousTotal(cartFoods);
                            grandTotal(cartFoods);
                            pricePreviousAdjust();
                            priceAdjust(); }


                    }
                    else {
                        if (cartFoods.get(position).getCount() < 99) {
                            cartFoods.get(position).setCount(cartFoods.get(position).getCount() + 1);
                            holder.disp.setText(String.valueOf(cartFoods.get(position).getCount()));
                            grandPriviousTotal(cartFoods);
                            grandTotal(cartFoods);
                            pricePreviousAdjust();
                            priceAdjust();
                        }
                    }

                }
                else
                {
                    GeneralFood item = cartFoods.get(position);
                    cartFoods.remove(item);
                    item.setCount(1);
                    notifyItemRemoved(position);

                    notifyItemRangeChanged(position, cartFoods.size());
                    grandPriviousTotal(cartFoods);
                    grandTotal(cartFoods);
                    pricePreviousAdjust();
                    priceAdjust();


                }
            }
        });

     /*   int totalc=(Integer.parseInt(cartFoods.get(position).getProductPrice()))*cartFoods.get(position).getCount();
        TotalPrice1=TotalPrice1+totalc;*/
      /*  holder.sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numtest ++;
                String s= String.valueOf(numtest);
                holder.disp.setText("" + s);
                int n= Integer.parseInt(holder.disp.getText().toString());
               // grandTotal(cartFoods,n);
                int n1=grandTotal(cartFoods,n);
                String n2= String.valueOf(n1);
                cartPrice.setText(n2);
               // GeneralFood item = cartFoods.get(position);
               *//* updateTotal(cartFoods);
                uppriceAdjust();*//*



            }
        });*/

     /*   holder.add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String n=holder.disp.getText().toString().trim();

                int n1= Integer.parseInt(n);
                if (n1 == 1){

                    GeneralFood item = cartFoods.get(position);
                    cartFoods.remove(item);

                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartFoods.size());

                    grandTotal(cartFoods,n1);

                }
                if (numtest > 1) {
                    numtest--;
                    String s= String.valueOf(numtest);
                    holder.disp.setText(s+ "");
                    int n5=grandTotal(cartFoods,numtest);
                    String n9= String.valueOf(n5);
                    cartPrice.setText(n9);
                }
            }
        });*/
       /* holder.cartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralFood item = cartFoods.get(position);
                cartFoods.remove(item);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartFoods.size());

               *//* grandTotal(cartFoods);
                priceAdjust();*//*

         *//* cartUpdate();*//*


            }});
*/

    }


    @Override
    public int getItemCount() {
        if(cartFoods!=null){
            return cartFoods.size();
        }
        return 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgBanner;
        TextView txtTitle, txtquantity, txtmrp, txtprice;
        Button buttond;
        CardView cartParentLayout;
        ImageButton cartDelete;
        TableLayout tableLayout;
        TextView disp;
        Button add1,sub1,deleteca,cnt;
        TextView tcat1,tcont;

        IncreseadapterClicklisner listener;

        public void setListener(IncreseadapterClicklisner listener) {
            this.listener = listener;
        }
        //static int numtest = 1;
        LinearLayout linearLayout, linearLayout1;


        public CartViewHolder(View itemView) {
            super(itemView);

            imgBanner = itemView.findViewById(R.id.img1);
            txtTitle = itemView.findViewById(R.id.textTitle);
            txtquantity = itemView.findViewById(R.id.textquantity);
            txtmrp = itemView.findViewById(R.id.textmrp);
            txtprice = itemView.findViewById(R.id.textprice);
            buttond = itemView.findViewById(R.id.buttondta);
            //  linearLayout1=itemView.findViewById(R.id.layoutL12);
            tcat1=itemView.findViewById(R.id.cart_category);
//tcont=itemView.findViewById(R.id.cart_category_count);
            disp=itemView.findViewById(R.id.tex1);
            add1 = itemView.findViewById(R.id.bt2);
            sub1 = itemView.findViewById(R.id.bt1);
            deleteca=itemView.findViewById(R.id.deletecart);
            txtmrp.setPaintFlags(txtmrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            // linearLayout = itemView.findViewById(R.id.vertical_parent_layout);
            // cartParentLayout = itemView.findViewById(R.id.cart_parent_layout);
            //  cartDelete = itemView.findViewById(R.id.cart_food_delete);

            add1.setOnClickListener(this);
            sub1.setOnClickListener(this);
            deleteca.setOnClickListener(this);
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
            else if(v==deleteca)
            {
                listener.onCalculatePrice(v,getAdapterPosition(),true,true);
            }
        }
    }

    public CartAdapter(Map<String, List<GeneralFood>> cartFoods, int recyclerview_cart, Context context){
        this.context = context;
        BasketFragment.map1 = cartFoods;

    }


}
