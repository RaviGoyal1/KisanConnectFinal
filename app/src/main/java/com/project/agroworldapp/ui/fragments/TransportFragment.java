package com.project.agroworldapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.project.agroworldapp.R;
import com.project.agroworldapp.Vehicle;
import com.project.agroworldapp.ui.adapter.VehicleAdapter;
import java.util.ArrayList;
import java.util.List;

public class TransportFragment extends Fragment {

    private RecyclerView recyclerView;
    private VehicleAdapter adapter;

    private Button btnVehicleOwnerCall;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transport, container, false);
        //btnVehicleOwnerCall=rootView.findViewById(R.id.btnVehicleOwnerCall);
        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView);

        // Set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Create a list of vehicles (replace this with your actual data)
        List<Vehicle> vehicleList = generateVehicleList();

        // Create and set adapter
        adapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    // Method to generate a list of vehicles (replace this with your actual data)
    private List<Vehicle> generateVehicleList() {
        List<Vehicle> vehicleList = new ArrayList<>();
        // Add vehicles to the list
        vehicleList.add(new Vehicle("Combines", "Location 1", 500.0, "9173177901", R.drawable.combines));
        vehicleList.add(new Vehicle("Tractor", "Location 1", 200.0, "1234567890", R.drawable.trac));
        vehicleList.add(new Vehicle("Sprayers", "Location 1", 400.0, "9173177901", R.drawable.sprayers));
        vehicleList.add(new Vehicle("Balers", "Location 1", 500.0, "1234567890", R.drawable.balers));
        vehicleList.add(new Vehicle("Seeders", "Location 1",  100.0, "1234567890", R.drawable.seeders));
        // Add more vehicles as needed
        return vehicleList;
    }
}
