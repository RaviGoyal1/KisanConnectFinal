package com.project.agroworldapp.payment.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.agroworldapp.BuildConfig;
import com.project.agroworldapp.R;
import com.project.agroworldapp.databinding.ActivityPaymentDetailsBinding;
import com.project.agroworldapp.payment.model.PaymentModel;
import com.project.agroworldapp.ui.repository.AgroWorldRepositoryImpl;
import com.project.agroworldapp.utils.Constants;
import com.project.agroworldapp.viewmodel.AgroViewModel;
import com.project.agroworldapp.viewmodel.AgroWorldViewModelFactory;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//
public class PaymentDetailsActivity extends AppCompatActivity implements PaymentResultListener {

    ActivityPaymentDetailsBinding binding;
    String address;
    StringBuilder stringBuilder = new StringBuilder();
    int countBackPressed;
    private AlertDialog.Builder alertDialogBuilder;
    private AgroViewModel viewModel;

    // Static user email and phone number
    private static final String USER_EMAIL = "example@example.com";
    private static final String USER_PHONE = "9724833142";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_details);
        Intent intent=getIntent();
        String totalAmount = intent.getStringExtra("totalAmount");
        String[] amountArr=totalAmount.split("");
        double amount=Double.parseDouble(amountArr[1]);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
//        initializeAgroWorldViewModel();
//        Checkout.preload(getApplicationContext());
//        Intent intent = getIntent();
//        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        address = intent.getStringExtra("address");
//
//        String totalAmount = intent.getStringExtra("totalAmount");
//        String[] amountArr = totalAmount.split(" ");
//        double amount = Double.parseDouble(amountArr[1]);
//
//
//        binding.tvProductNameAndPrice.setText("Product list- \n" + stringBuilder);
//        binding.tvAddress.setText("Address- " + address);
//        binding.tvPaymentDate.setText("Date- " + date);
//        binding.tvTotalPayAmt.setText("Total- " + totalAmount);
//
//        alertDialogBuilder = new AlertDialog.Builder(PaymentDetailsActivity.this);
//        alertDialogBuilder.setCancelable(false);
//        alertDialogBuilder.setTitle("Payment Result");
//
       binding.btnProceed.setOnClickListener(v -> {
           if (amount != 0.0) {
               Checkout checkout = new Checkout();
               checkout.setKeyID(BuildConfig.RAZORPAY_KEY_ID);
               JSONObject object = new JSONObject();
               try {
                   // to put name
                   object.put("name", "Geeks for Geeks");

                   // put description
                   object.put("description", "Test payment");

                   // to set theme color
                   object.put("theme.color", "");

                   // put the currency
                   object.put("currency", "INR");

                   // put amount
                   object.put("amount", amount);

                   // put mobile number
                   object.put("prefill.contact", "9724833142");

                   // put email
                   object.put("prefill.email", "chaitanyamunje@gmail.com");

                   // open razorpay to checkout activity
                   checkout.open(PaymentDetailsActivity.this, object);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       });

    }
    @Override
    public void onPaymentSuccess(String s) {
        // this method is called on payment success.
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
//    public void startPayment(int amount) {
//        printLog("Amount - " + amount);
//        final Activity activity = this;
//        final Checkout checkout = new Checkout();
//        checkout.setKeyID(BuildConfig.RAZORPAY_KEY_ID);
//        try {
//            JSONObject options = new JSONObject();
//            options.put("name", "Agro World");
//            options.put("description", "Seeds shopping");
//            options.put("send_sms_hash", true);
//            options.put("allow_rotation", true);
//            //You can omit the image option to fetch the image from dashboard
//            options.put("image", Constants.APP_ICON_LINK);
//            options.put("currency", "INR");
//            options.put("amount", amount);
//            options.put("payment_capture", 1);
//            JSONObject preFill = new JSONObject();
//            // Using static email and phone number
//            preFill.put("email", USER_EMAIL);
//            preFill.put("contact", USER_PHONE);
//            options.put("prefill", preFill);
//            checkout.open(activity, options);
//        } catch (Exception e) {
//            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
//                    .show();
//            e.printStackTrace();
//        }
//    }
//
//    public void initializeAgroWorldViewModel() {
//        AgroWorldRepositoryImpl agroWorldRepository = new AgroWorldRepositoryImpl();
//        viewModel = ViewModelProviders.of(this, new AgroWorldViewModelFactory(agroWorldRepository, this)).get(AgroViewModel.class);
//    }
//
//    public void showAlertMessageWithStatus(PaymentData paymentData) {
//        // Implementation of the showAlertMessageWithStatus method remains the same
//    }
//
//    @Override
//    public void onBackPressed() {
//        // Implementation of the onBackPressed method remains the same
//    }
//
//    private void printLog(String message) {
//        Log.d("paymentActivity", message);
//    }

}
