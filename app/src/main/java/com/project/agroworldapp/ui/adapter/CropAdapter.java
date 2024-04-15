package com.project.agroworldapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.project.agroworldapp.Crop;
import com.project.agroworldapp.R;
import java.util.ArrayList;
import java.util.List;

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.ViewHolder> {

    private List<Crop> crops;
    private List<Crop> filteredCrops; // List to hold filtered crops
    private OnBuyNowClickListener buyNowClickListener; // Listener for "Buy Now" button

    // Constructor to initialize the list of crops
    public CropAdapter(List<Crop> crops) {
        this.crops = crops;
        this.filteredCrops = new ArrayList<>(crops); // Initialize filtered list with original data
    }

    // ViewHolder class for the item layout
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView nameTextView;
        public TextView priceTextView;
        public ImageView imageView; // ImageView for the crop image
        public Button btnViewMore; // Button for "View More"

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.crdVehicle);
            nameTextView = itemView.findViewById(R.id.tvVehicleName);
            priceTextView = itemView.findViewById(R.id.tvRentPerDay);
            imageView = itemView.findViewById(R.id.ivVehicleImage);
            btnViewMore = itemView.findViewById(R.id.btnViewMore); // Initialize button
        }
    }

    // Inflate the item layout and create the ViewHolder object
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_crops, parent, false);
        return new ViewHolder(view);
    }

    // Populate data into the item through ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Crop crop = filteredCrops.get(position); // Use filtered dataset
        holder.nameTextView.setText(crop.getName());
        holder.priceTextView.setText("Price: Rs" + crop.getPrice());
        holder.imageView.setImageResource(crop.getImageResource());

        // Set OnClickListener for the "View More" button
        holder.btnViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyNowClickListener != null) {
                    buyNowClickListener.onBuyNowClick(crop);
                }
            }
        });
    }

    // Return the size of the filtered dataset
    @Override
    public int getItemCount() {
        return filteredCrops.size();
    }

    // Method to set filtered data
    public void setData(List<Crop> filteredList) {
        filteredCrops.clear(); // Clear previous filtered data
        filteredCrops.addAll(filteredList); // Add new filtered data
        notifyDataSetChanged(); // Notify adapter of data change
    }

    // Method to set the BuyNowClickListener
    public void setBuyNowClickListener(OnBuyNowClickListener listener) {
        this.buyNowClickListener = listener;
    }

    // Interface to handle "Buy Now" button click events
    public interface OnBuyNowClickListener {
        void onBuyNowClick(Crop crop);
    }
}
