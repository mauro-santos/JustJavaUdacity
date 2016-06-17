package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;
    int priceCoffe = 5;
    int priceWhippedCream = 1;
    int priceChocolate = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkboxWhippedCream = (CheckBox) findViewById(R.id.checkboxWhippedCream);
        checkboxWhippedCream.setText(checkboxWhippedCream.getText().toString() + " $" + priceWhippedCream);

        CheckBox checkboxChocolate = (CheckBox) findViewById(R.id.checkboxChocolate);
        checkboxChocolate.setText(checkboxChocolate.getText().toString() + " $" + priceChocolate);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(MainActivity.this, getString(R.string.warning_increment), Toast.LENGTH_SHORT).show();
            return;
        }

        quantity += 1;
        display(quantity);
        //displayPrice(quantity * priceCoffe);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(MainActivity.this, getString(R.string.warning_decrement), Toast.LENGTH_SHORT).show();
            return;
        }

        quantity -= 1;
        display(quantity);
        //displayPrice(quantity * priceCoffe);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int price = priceCoffe;

        if (addWhippedCream) {
            price += priceWhippedCream;
        }
        if (addChocolate) {
            price += priceChocolate;
        }

        return (price * quantity);
    }

    /**
     * Create summary of the order.
     *
     * @param price of the order.
     * @return text summary.
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String message = getString(R.string.name) + ": " + name
                + "\n" + getString(R.string.whipped_cream_added) + " " + hasWhippedCream
                + "\n" + getString(R.string.chocolate_added) + " " + hasChocolate
                + "\n" + getString(R.string.quantity) + ": " + quantity
                + "\n" + getString(R.string.total) + price
                + "\n" + getString(R.string.thank_you);
        return message;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        String name = editTextName.getText().toString();

        CheckBox checkboxWhippedCream = (CheckBox) findViewById(R.id.checkboxWhippedCream);
        boolean hasWhippedCream = checkboxWhippedCream.isChecked();

        CheckBox checkboxChocolate = (CheckBox) findViewById(R.id.checkboxChocolate);
        boolean hasChocolate = checkboxChocolate.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        //Log.v("MainActivity","O valor de Whipped Cream é " + checkedBoxWhippedCreamIsChecked);

        /*
        String priceMessage = "Total $" + price;
        priceMessage += "\nThank you!";
        displayMessage(priceMessage);
        */

        displayMessage(createOrderSummary(name, price, hasWhippedCream, hasChocolate));

        /*
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        //intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(name, price, hasWhippedCream, hasChocolate));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MainActivity.this, "There is not email app installed", Toast.LENGTH_SHORT).show();
        }
        */

        /*
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MainActivity.this, "There is not viewer app installed", Toast.LENGTH_SHORT).show();
        }
        */
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

        //View textView = findViewById(R.id.order_summary_text_view);
        //textView.setVisibility(View.GONE);
    }
}