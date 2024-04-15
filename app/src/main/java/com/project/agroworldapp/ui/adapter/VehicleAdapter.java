package com.project.agroworldapp.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.project.agroworldapp.R;
import com.project.agroworldapp.Vehicle;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    private List<Vehicle> vehicles;


    // Constructor to initialize the list of vehicles
    public VehicleAdapter(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    // ViewHolder class for the item layout
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView nameTextView;
        public TextView priceTextView;
        public Button contactOwnerButton;
        public ImageView imageView;
        public Button btnVehicleOwnerCall;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.crdVehicle);
            nameTextView = itemView.findViewById(R.id.tvVehicleName);
            priceTextView = itemView.findViewById(R.id.tvRentPerDay);
            contactOwnerButton = itemView.findViewById(R.id.btnVehicleOwnerCall);
            imageView=itemView.findViewById(R.id.ivVehicleImage);
            btnVehicleOwnerCall=itemView.findViewById(R.id.btnVehicleOwnerCall);


        }
    }

    // Inflate the item layout and create the ViewHolder object
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vehicle, parent, false);
        return new ViewHolder(view);
    }

    // Populate data into the item through ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Vehicle vehicle = vehicles.get(position);
        holder.nameTextView.setText(vehicle.getName());
        holder.priceTextView.setText("Rent per Day: Rs " + vehicle.getPrice());
        holder.imageView.setImageResource(vehicle.getImageResource());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle onClick event here, you can initiate a phone call to the owner
                // using the vehicle's owner phone number
                // For example: callOwner(vehicle.getOwnerPhoneNumber());
            }
        });
        holder.contactOwnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callOwner(vehicle.getOwnerPhoneNumber(), view);
            }
        });
    }
    private void callOwner(String phoneNumber, View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        view.getContext().startActivity(intent);
    }
    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return vehicles.size();
    }
}
