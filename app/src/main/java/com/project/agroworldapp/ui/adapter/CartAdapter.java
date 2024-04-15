package com.project.agroworldapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.agroworldapp.R;
import com.project.agroworldapp.viewmodel.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartModel> cartList;
    private Context context;
    private TextView tvTotalAmount;
    public CartAdapter(List<CartModel> cartList, Context context,TextView tvTotalAmount) {
        this.cartList = cartList;
        this.context = context;
        this.tvTotalAmount = tvTotalAmount;
        updateTotalAmount();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartModel currentItem = cartList.get(position);
        holder.ivProductImage.setImageResource(currentItem.getProductImageResource());
        holder.tvProductName.setText(currentItem.getProductName());
        holder.tvProductPrice.setText(String.valueOf(currentItem.getProductPrice() * currentItem.getItemCount()));
        holder.tvItemCount.setText(String.valueOf(currentItem.getItemCount()));

        holder.ivIncreaseCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemCount = currentItem.getItemCount();
                itemCount++;
                currentItem.setItemCount(itemCount);
                holder.tvItemCount.setText(String.valueOf(itemCount));
                holder.tvProductPrice.setText(String.valueOf(currentItem.getProductPrice() * itemCount));
                updateTotalAmount();
            }
        });

        holder.ivDecreaseCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemCount = currentItem.getItemCount();
                if (itemCount >= 1) {
                    itemCount--;
                    currentItem.setItemCount(itemCount);
                    holder.tvItemCount.setText(String.valueOf(itemCount));
                    holder.tvProductPrice.setText(String.valueOf(currentItem.getProductPrice() * itemCount));
                    if (itemCount == 0) {
                        delete(holder.getAdapterPosition());
                    }
                    updateTotalAmount();
                }
            }
        });

        holder.ivRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(holder.getAdapterPosition());
                updateTotalAmount();
            }
        });
    }
    private void updateTotalAmount() {
        double totalAmount = 0;
        for (CartModel cartItem : cartList) {
            totalAmount += cartItem.getProductPrice() * cartItem.getItemCount();
        }
        tvTotalAmount.setText(String.format("Total: $%.2f", totalAmount)); // Update total amount TextView
    }
    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void delete(int position) {
        cartList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cartList.size());
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView tvProductName;
        TextView tvProductPrice;
        TextView tvItemCount;
        ImageView ivIncreaseCount;
        ImageView ivDecreaseCount;
        ImageView ivRemoveItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvItemCount = itemView.findViewById(R.id.tvItemCount);
            ivIncreaseCount = itemView.findViewById(R.id.ivIncreaseCount);
            ivDecreaseCount = itemView.findViewById(R.id.ivDecreaseCount);
            ivRemoveItem = itemView.findViewById(R.id.ivRemoveItem);
        }
    }
    public interface CartInterface {
        void changeQuantity(CartModel cartModel, int quantity);
    }

}
