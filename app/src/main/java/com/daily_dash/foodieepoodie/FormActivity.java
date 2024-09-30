package com.daily_dash.foodieepoodie;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class FormActivity extends AppCompatActivity {

    private EditText itemName, itemQuantity, itemPrice;
    private Spinner categorySpinner;
    private TextView outputText;
    private View outputLayout;

    // Regex patterns for validation
    private static final Pattern ITEM_NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]+$");
    private static final Pattern QUANTITY_PATTERN = Pattern.compile("^[1-9]\\d*$"); // Positive integers
    private static final Pattern PRICE_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$"); // Decimal with up to two places

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Initialize the views
        itemName = findViewById(R.id.itemName);
        itemQuantity = findViewById(R.id.itemQuantity);
        itemPrice = findViewById(R.id.itemPrice);
        categorySpinner = findViewById(R.id.categorySpinner);
        Button submitBtn = findViewById(R.id.submitBtn);
        Button clearBtn = findViewById(R.id.clearBtn);
        outputText = findViewById(R.id.outputText);
        outputLayout = findViewById(R.id.outputLayout);

        // Set up the Spinner (dropdown)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);


        submitBtn.setOnClickListener(v -> {
            // Get input values
            String name = itemName.getText().toString().trim();
            String quantity = itemQuantity.getText().toString().trim();
            String price = itemPrice.getText().toString().trim();
            String category = categorySpinner.getSelectedItem().toString();

            // Validate input fields with regex
            if (!ITEM_NAME_PATTERN.matcher(name).matches()) {
                Toast.makeText(FormActivity.this, "Invalid item name. Only letters and spaces allowed.", Toast.LENGTH_SHORT).show();
            } else if (!QUANTITY_PATTERN.matcher(quantity).matches()) {
                Toast.makeText(FormActivity.this, "Invalid quantity. Please enter a positive integer.", Toast.LENGTH_SHORT).show();
            } else if (!PRICE_PATTERN.matcher(price).matches()) {
                Toast.makeText(FormActivity.this, "Invalid price. Please enter a valid number (e.g., 100 or 100.50).", Toast.LENGTH_SHORT).show();
            } else {
                // Display the output if all inputs are valid
                outputLayout.setVisibility(View.VISIBLE);
                String output = "Item: " + name + "\nQuantity: " + quantity + "\nPrice: $" + price + "\nCategory: " + category;
                outputText.setText(output);
            }
        });

        // Clear button click listener
        clearBtn.setOnClickListener(v -> {
            // Clear input fields
            itemName.setText("");
            itemQuantity.setText("");
            itemPrice.setText("");
            categorySpinner.setSelection(0); // Reset the spinner to the first item

            // Hide output layout
            outputLayout.setVisibility(View.GONE);
        });
    }
}
