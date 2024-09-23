package com.daily_dash.foodieepoodie;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private EditText itemNameEditText;
    private RadioGroup storeRadioGroup;
    private CheckBox checkItem1, checkItem2, checkItem3;
    private TextView quantityTextView, priceTextView, ratingTextView;

    private int quantity = 1;
    private final double pricePerItem = 50.0;  // Assuming each item costs 50 BDT
    private double totalPrice = pricePerItem;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize UI elements
        itemNameEditText = findViewById(R.id.itemNameEditText);
        storeRadioGroup = findViewById(R.id.storeRadioGroup);
        checkItem1 = findViewById(R.id.checkItem1);
        checkItem2 = findViewById(R.id.checkItem2);
        checkItem3 = findViewById(R.id.checkItem3);
        Button decreaseQuantityButton = findViewById(R.id.decreaseQuantityButton);
        Button increaseQuantityButton = findViewById(R.id.increaseQuantityButton);
        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        quantityTextView = findViewById(R.id.quantityTextView);
        priceTextView = findViewById(R.id.priceTextView);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingTextView = findViewById(R.id.ratingTextView);

        // Set initial price
        updatePrice();

        // Handle quantity increase
        increaseQuantityButton.setOnClickListener(v -> {
            quantity++;
            quantityTextView.setText(String.valueOf(quantity));
            updatePrice();
        });

        // Handle quantity decrease
        decreaseQuantityButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityTextView.setText(String.valueOf(quantity));
                updatePrice();
            } else {
                Toast.makeText(OrderActivity.this, "Minimum quantity is 1", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle rating change
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> ratingTextView.setText(String.format("Rating: %.1f", rating)));

        // Handle place order button click
        placeOrderButton.setOnClickListener(v -> placeOrder());
    }

    // Method to update price
    @SuppressLint("DefaultLocale")
    private void updatePrice() {
        // Calculate total price based on quantity
        totalPrice = quantity * pricePerItem;
        priceTextView.setText(String.format("BDT %.2f", totalPrice));
    }

    // Method to handle placing the order
    private void placeOrder() {
        String itemName = itemNameEditText.getText().toString().trim();
        if (itemName.isEmpty()) {
            Toast.makeText(this, "Please enter an item name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected store
        int selectedStoreId = storeRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedStoreRadioButton = findViewById(selectedStoreId);
        String selectedStore = (selectedStoreRadioButton != null) ? selectedStoreRadioButton.getText().toString() : "No store selected";

        // Get selected additional items
        StringBuilder additionalItems = new StringBuilder();
        if (checkItem1.isChecked()) additionalItems.append(checkItem1.getText()).append(", ");
        if (checkItem2.isChecked()) additionalItems.append(checkItem2.getText()).append(", ");
        if (checkItem3.isChecked()) additionalItems.append(checkItem3.getText()).append(", ");

        if (additionalItems.length() > 0) {
            additionalItems.setLength(additionalItems.length() - 2);  // Remove last comma and space
        } else {
            additionalItems.append("No additional items");
        }

        // Display a summary of the order
        @SuppressLint("DefaultLocale") String orderSummary = String.format(
                "Item: %s\nStore: %s\nQuantity: %d\nAdditional Items: %s\nTotal Price: BDT %.2f",
                itemName, selectedStore, quantity, additionalItems, totalPrice);

        Toast.makeText(this, orderSummary, Toast.LENGTH_LONG).show();
    }
}
