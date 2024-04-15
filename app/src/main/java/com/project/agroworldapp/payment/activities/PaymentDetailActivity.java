package com.project.agroworldapp.payment.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.agroworldapp.BuildConfig;
import com.project.agroworldapp.R;
import com.project.agroworldapp.utils.Constants;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PaymentDetailActivity extends AppCompatActivity implements PaymentResultListener {

    private ImageView ivOnlinePay;
    private TextView tvProductNameAndPrice, tvPaymentDate, tvAddress, tvTotalPayAmt, tvPaymentStatus;
    private Button btnProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        Intent intent=getIntent();
        String total= intent.getStringExtra("totalAmount");
        String address= intent.getStringExtra("address");
        String integerPortion = total.replaceAll("[^\\d.]", "");
        double integerAmount =  Double.parseDouble(integerPortion);

        // Initialize views
        ivOnlinePay = findViewById(R.id.ivOnlinePay);
        tvProductNameAndPrice = findViewById(R.id.tvProductNameAndPrice);
        tvPaymentDate = findViewById(R.id.tvPaymentDate);
        tvAddress = findViewById(R.id.tvAddress);
        tvTotalPayAmt = findViewById(R.id.tvTotalPayAmt);
        tvPaymentStatus = findViewById(R.id.tvPaymentStatus);
        btnProceed = findViewById(R.id.btnProceed);

        // Set onClickListener for the button
        if(integerAmount>0) {
            btnProceed.setOnClickListener(v -> {
                Checkout checkout = new Checkout();
                checkout.setKeyID(BuildConfig.RAZORPAY_KEY_ID);
                JSONObject object = new JSONObject();
                        final Activity activity = this;
                        try {
                            JSONObject options = new JSONObject();
                            options.put("name", "Agro World");
                            options.put("description", "Seeds shopping");
                            options.put("send_sms_hash", true);
                            options.put("allow_rotation", true);
                            //You can omit the image option to fetch the image from dashboard
                            options.put("image", Constants.APP_ICON_LINK);
                            options.put("currency", "INR");
                            options.put("amount", integerAmount*100.0);
                            options.put("payment_capture",1);
                            JSONObject preFill = new JSONObject();
                            preFill.put("email", "ravindragoya2706@gmail.com");
                            preFill.put("contact", "9724833142");
                            options.put("prefill", preFill);
                            checkout.open(activity, options);
                        } catch (Exception e) {
                            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                                    .show();
                            e.printStackTrace();
                        }
            });
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(calendar.getTime());
        // You can set text or image resources to the views as needed
        ivOnlinePay.setImageResource(R.drawable.payment);
//        tvProductNameAndPrice.setText("Class Name");
        tvPaymentDate.setText("Date and Time : "+dateTime);
        tvAddress.setText("Address : "+address);
        tvTotalPayAmt.setText("Total Amount : Rs"+String.valueOf(integerAmount));
        tvPaymentStatus.setText("Payment pending");
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
}
