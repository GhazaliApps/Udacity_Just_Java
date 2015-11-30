package com.example.lenovo.justjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // This Method called when + Button  Clicked
    public void increment(View view) {
        quantity += 1;
        display(quantity);
    }

    // This Method called when - Button  Clicked
    public void decrement(View view) {
        quantity -= 1;
        display(quantity);
    }

    // This Method called when order Button  Clicked
    public void submitOrder(View view) {
        CheckBox WhippedCreamCheckBox= (CheckBox)findViewById(R.id.Whipped_cream_CheckBox);
        boolean hasWhippedCream= WhippedCreamCheckBox.isChecked();

        CheckBox ChocolateCheckBox= (CheckBox)findViewById(R.id.Chocolate_CheckBox);
        boolean hasChocolate= ChocolateCheckBox.isChecked();

        EditText nameField=(EditText) findViewById(R.id.name_field);
        String name=nameField.getText().toString();

        int Price = CalculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage = createOrderSummary(name,Price,hasWhippedCream,hasChocolate);
        dispalyMessage(priceMessage);
    }

    // Method Return Total Price
    private int CalculatePrice(boolean addWhippedCream, boolean addChocolate) {

        int baseprice=5;
        if (addWhippedCream)
        {
            baseprice+=1;
        }
        if (addChocolate)
        {
            baseprice+=2;
        }

        return  quantity * baseprice;
    }

    // This Method return Total Order Summary
    private String createOrderSummary( String name ,int price,boolean addWhippedCream,boolean addChocolate)
    {
        String priceMessage="Name : "+name;
        priceMessage+="\nAdd Whipped Cream ? "+ addWhippedCream;
        priceMessage+="\nAdd Chocolate ? "+ addChocolate;
        priceMessage+="\nQuantity = "+ quantity;
        priceMessage+="\nPrice is : "+price +" $";
        priceMessage+="\nThank You !";
                return priceMessage;
    }
    // This Method set The Quantity on the screen
    private void display(int num) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textView);
        quantityTextView.setText(" " + num);
    }

    // This Method set Price on the screen
    private void displayPrice(int num) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textView);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(num));
    }
    //This Method display a meesage beside a Price
    private void dispalyMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textView);
        priceTextView.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
