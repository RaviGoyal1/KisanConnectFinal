//package com.project.agroworldapp.ui.fragments;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.project.agroworldapp.Crop;
//import com.project.agroworldapp.R;
//import com.project.agroworldapp.Vehicle;
//import com.project.agroworldapp.databinding.FragmentShoppingBinding;
//import com.project.agroworldapp.ui.adapter.CropAdapter;
//import com.project.agroworldapp.ui.adapter.VehicleAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ShoppingFragment extends Fragment {
//
//    private RecyclerView recyclerView;
//    private CropAdapter adapter;
//    private FragmentShoppingBinding binding; // Declare binding variable
//    @Nullable
//    @Override
//    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup
//            container, @Nullable Bundle savedInstanceState){
//        View rootView = inflater.inflate(R.layout.fragment_shopping, container, false);
//
//        // Initialize RecyclerView
//        recyclerView = rootView.findViewById(R.id.recyclerView);
//
//        // Set layout manager
//        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
//
//        // Create a list of vehicles (replace this with your actual data)
//        List<Crop> cropList = generateCropList();
//
//        // Create and set adapter
//        adapter = new CropAdapter(cropList);
//        recyclerView.setAdapter(adapter);
//
//        return rootView;
//    }
//    private List<Crop> generateCropList() {
//        List<Crop> cropList = new ArrayList<>();
//        cropList.add(new Crop("wheat",1000,"9390",R.drawable.disease));
//        cropList.add(new Crop("maize",1000,"9390",R.drawable.disease));
//        cropList.add(new Crop("bajra",1000,"9390",R.drawable.disease));
//        cropList.add(new Crop("corn",1000,"9390",R.drawable.disease));
//        cropList.add(new Crop("xyz",1000,"9390",R.drawable.disease));
//        return cropList;
//    }
//}
package com.project.agroworldapp.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.project.agroworldapp.Crop;
import com.project.agroworldapp.R;
import com.project.agroworldapp.ui.activity.AddToCartActivity;
import com.project.agroworldapp.ui.adapter.CropAdapter;
import com.project.agroworldapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {

    private RecyclerView recyclerView;
    private CropAdapter adapter;
    private List<Crop> cropList;

    private TextView q;

    private int quantity=0;
    private ImageView ivCart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shopping, container, false);
        q=rootView.findViewById(R.id.cart_textview);
        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Create a list of crops

        cropList = generateCropList();

        // Create and set adapter
        adapter = new CropAdapter(cropList);
        recyclerView.setAdapter(adapter);
        List<Crop> cropList1 = new ArrayList<>();
        // Initialize SearchView
        SearchView searchView = rootView.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
        ivCart = rootView.findViewById(R.id.ivCart);

        // Set OnClickListener for Cart ImageView
        ivCart.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddToCartActivity.class);
            intent.putExtra("selected_crop",new ArrayList<>(cropList1));
            startActivity(intent);
        });

        ImageView ivHistory = rootView.findViewById(R.id.ivHistoy);

        // Set OnClickListener for History ImageView
//        ivHistory.setOnClickListener(v -> {
//            startActivityForResult(new Intent(getContext(), PaymentHistoryActivity.class), Constants.REQUEST_CODE);
//        });
        adapter.setBuyNowClickListener(new CropAdapter.OnBuyNowClickListener() {
            @Override
            public void onBuyNowClick(Crop crop) {
                  quantity++;
                  q.setText(String.valueOf(quantity));
                  int i= crop.getQuantity();
                  i++;
                  crop.setQuantity(i);
                  cropList1.add(crop);
            }
        });

        return rootView;
    }

    // Method to generate a list of crops
    private List<Crop> generateCropList() {
        List<Crop> cropList = new ArrayList<>();
        cropList.add(new Crop("Asian Rice", 100, "9390", R.drawable.asianrice,0));
        cropList.add(new Crop("Cumin Seeds", 200, "9390", R.drawable.cumin,0));
        cropList.add(new Crop("Sunflower Seeds", 100, "9390", R.drawable.sunf,0));
        cropList.add(new Crop("Latin Seeds", 300, "9390", R.drawable.latin,0));
        cropList.add(new Crop("Sesame seeds", 100, "9390", R.drawable.sesame,0));
        return cropList;
    }

    // Method to filter the crop list based on search query
    private void filter(String query) {
        List<Crop> filteredList = new ArrayList<>();
        for (Crop crop : cropList) {
            if (crop.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(crop);
            }
        }
        adapter.setData(filteredList); // Update adapter with filtered list
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // Handle the result here if needed
                // For example, update the UI or perform any other operation
            }
        }
    }

}
