package com.example.stickyheaders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {


    private List<Model> itemList;
    private Context context;
    public static TextView grandTotal;
    public static int grandTotalplus;
    int cart_count = 0;

    public ParentItemAdapter(List<Model> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parent_item, viewGroup, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder parentViewHolder, final int position) {
        parentViewHolder.categoryid.setText(itemList.get(position).getCategoryid());
        parentViewHolder.categoryname.setText(itemList.get(position).getHeader());
        parentViewHolder.layout.setText(itemList.get(position).getLayout());
        parentViewHolder.type.setText(itemList.get(position).getDescription());

    }


    @Override
    public int getItemCount() {

        return itemList.size();
    }


    class ParentViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryid, categoryname, layout, type;
        private RecyclerView ChildRecyclerView;
        ImageView productCartImage, cartIncrement, cartDecrement, deleteItem;
        TextView productCartCode, productCartPrice, productCartQuantity;


        ParentViewHolder(final View itemView) {
            super(itemView);

            categoryid = itemView.findViewById(R.id.categoryid);
            categoryname = itemView.findViewById(R.id.categoryname);
            layout = itemView.findViewById(R.id.layout);
            type = itemView.findViewById(R.id.type);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
            productCartQuantity = itemView.findViewById(R.id.cart_product_quantity_tv);
            cartDecrement = itemView.findViewById(R.id.cart_decrement);
            cartIncrement = itemView.findViewById(R.id.cart_increment);

        }
    }
}
