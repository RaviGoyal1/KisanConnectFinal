package com.project.agroworldapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.project.agroworldapp.Crop;
import com.project.agroworldapp.R;
import com.project.agroworldapp.payment.activities.PaymentDetailActivity;
import com.project.agroworldapp.payment.activities.PaymentDetailsActivity;
import com.project.agroworldapp.ui.adapter.CartAdapter;
import com.project.agroworldapp.viewmodel.CartModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddToCartActivity extends AppCompatActivity implements CartAdapter.CartInterface {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartModel> cartItemList;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private TextView tvAddAddress;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView tvTotalAmount;

    private Button btnProceedToPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view_crt_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvTotalAmount=findViewById(R.id.tvTotalAmount);
        btnProceedToPayment=findViewById(R.id.btnPayment);
        // Initialize cart item list
        cartItemList = new ArrayList<>();
        tvAddAddress = findViewById(R.id.tvAddAddress);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Set click listener for the drawableEndCompat
        tvAddAddress.setOnClickListener(v -> fetchAndDisplayLiveLocation());
        // Receive the selected crop from the intent extras
        Intent intent = getIntent();
        ArrayList<Crop> cropList = (ArrayList<Crop>) intent.getSerializableExtra("selected_crop");
        List<CartModel> cartItemList = new ArrayList<>();
        for (Crop crop : cropList) {
            CartModel cartItem = new CartModel(crop.getImageResource(), crop.getName(), crop.getPrice(), crop.getQuantity());
            cartItemList.add(cartItem);
        }
        double totalAmount = calculateTotalAmount(cartItemList);
        tvTotalAmount.setText(String.format("Total: Rs%.2f", totalAmount));
        cartAdapter = new CartAdapter(cartItemList, this,tvTotalAmount);
        recyclerView.setAdapter(cartAdapter);


    }
    public void onProceedToPaymentClicked(View view) {
        // Show a toast message
        Log.d("ButtonClicked", "Proceed to payment button clicked");
        Intent i=new Intent(getApplicationContext(), PaymentDetailActivity.class);
        i.putExtra("totalAmount",tvTotalAmount.getText());
        i.putExtra("address",tvAddAddress.getText());
        startActivity(i);
    }
    // Inside fetchAndDisplayLiveLocation() method

    private void fetchAndDisplayLiveLocation() {
        // Check if the app has location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permissions if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        // Fetch the last known location
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        // Reverse geocode the location to get the address
                        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (!addresses.isEmpty()) {
                                String address = addresses.get(0).getAddressLine(0);
                                // Display the address in the TextView
                                tvAddAddress.setText(address);
                            } else {
                                // Handle case where address is not available
                                tvAddAddress.setText("Address not available");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            // Handle IO Exception
                            tvAddAddress.setText("Error fetching address");
                        }
                    } else {
                        // Handle case where location is null
                        tvAddAddress.setText("Location not available");
                    }
                });
    }

    // onRequestPermissionsResult() method
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, fetch and display location
                fetchAndDisplayLiveLocation();
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
                // Optionally, disable the button or provide alternative functionality
            }
        }
    }

    private double calculateTotalAmount(List<CartModel> cartItemList) {
        double total = 0;
        for (CartModel cartItem : cartItemList) {
            total += cartItem.getProductPrice() * cartItem.getItemCount();
        }
        return total;
    }
    @Override
    public void changeQuantity(CartModel cartModel, int quantity) {
        // Handle quantity change if needed
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        // Add any data you want to pass back to the fragment
        setResult(Activity.RESULT_OK, resultIntent);
        super.onBackPressed();
    }

}
