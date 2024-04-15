//package com.project.agroworldapp.manufacture.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.project.agroworldapp.databinding.ProductAdminLayoutBinding;
//import com.project.agroworldapp.databinding.ProductItemLayoutBinding;
//import com.project.agroworldapp.manufacture.listener.ManufactureAdminListener;
//import com.project.agroworldapp.manufacture.viewholder.ProductAdminViewHolder;
//import com.project.agroworldapp.manufacture.viewholder.ProductViewHolder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//
//
//
//
//
//    @Override
//    public int getItemViewType(int position) {
//        return layoutType;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == 0) {
//            return new ProductAdminViewHolder(ProductAdminLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
//        } else {
//            return new ProductViewHolder(ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
//        }
//    }
//
//
//
//
//}
