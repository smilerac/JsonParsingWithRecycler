package com.kthdvs.day2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class cardclick extends AppCompatActivity {

    TextView name,desc,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardclick);

        String item_name = getIntent().getExtras().getString("name");
        String item_desc = getIntent().getExtras().getString("desc");
        String item_price = getIntent().getExtras().getString("price");

        //Toast.makeText(this, "Price of "+item_name+""+item_desc+"is"+item_price, Toast.LENGTH_SHORT).show();

        name=findViewById(R.id.txt_name);
        desc=findViewById(R.id.txt_desc);
        price=findViewById(R.id.txt_price);

        name.setText(item_name);
        desc.setText(item_desc);
        price.setText(item_price);
    }
}
