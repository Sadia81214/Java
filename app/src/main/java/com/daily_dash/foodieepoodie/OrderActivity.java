package com.daily_dash.foodieepoodie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private EditText itemNameEditText;
    private RadioGroup storeRadioGroup;
    private CheckBox checkItem1, checkItem2, checkItem3;
    private TextView quantityTextView, priceTextView;
    private int quantity = 1;
    private final double pricePerItem = 50.0;
    private double totalPrice = pricePerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize UI components
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

        // Update price based on quantity
        updatePrice();

        increaseQuantityButton.setOnClickListener(v -> {
            quantity++;
            quantityTextView.setText(String.valueOf(quantity));
            updatePrice();
        });

        decreaseQuantityButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityTextView.setText(String.valueOf(quantity));
                updatePrice();
            } else {
                Toast.makeText(OrderActivity.this, "Minimum quantity is 1", Toast.LENGTH_SHORT).show();
            }
        });

        placeOrderButton.setOnClickListener(v -> {
            String itemName = itemNameEditText.getText().toString().trim();
            if (itemName.isEmpty()) {
                Toast.makeText(this, "Please enter an item name", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedStoreId = storeRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedStoreRadioButton = findViewById(selectedStoreId);
            String selectedStore = selectedStoreRadioButton != null
                    ? selectedStoreRadioButton.getText().toString()
                    : "No store selected";

            // Prepare additional items string
            StringBuilder additionalItems = new StringBuilder();
            if (checkItem1.isChecked()) additionalItems.append(checkItem1.getText()).append(", ");
            if (checkItem2.isChecked()) additionalItems.append(checkItem2.getText()).append(", ");
            if (checkItem3.isChecked()) additionalItems.append(checkItem3.getText()).append(", ");

            if (additionalItems.length() > 0) {
                additionalItems.setLength(additionalItems.length() - 2);
            } else {
                additionalItems.append("No additional items");
            }

            // Pass order details to FormActivity
            Intent intent = new Intent(OrderActivity.this, FormActivity.class);
            intent.putExtra("itemName", itemName);
            intent.putExtra("quantity", String.valueOf(quantity));
            intent.putExtra("price", String.valueOf(totalPrice));
            intent.putExtra("store", selectedStore);
            intent.putExtra("additionalItems", additionalItems.toString());
            startActivity(intent);
        });
    }

    private void updatePrice() {
        totalPrice = quantity * pricePerItem;
        priceTextView.setText(String.format("BDT %.2f", totalPrice));
    }
}
