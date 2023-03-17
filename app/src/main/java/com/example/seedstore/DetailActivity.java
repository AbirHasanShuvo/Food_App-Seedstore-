package com.example.seedstore;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.seedstore.databinding.ActivityDetailBinding;
import com.example.seedstore.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        DBHelper helper = new DBHelper(DetailActivity.this);

        if(getIntent().getIntExtra("type", 0) == 1) {


            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));

            final String foodname = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.pricelbl.setText(String.format("%d", price));
            binding.foodname.setText(foodname);
            binding.detailDescription.setText(description);


            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isinserted = helper.insertOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            description,
                            foodname,
                            Integer.parseInt(binding.quantity.getText().toString()));

                    if (isinserted) {
                        Toast.makeText(DetailActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

        else
        {
            int id = getIntent().getIntExtra("id", 0 );
            Cursor cursor = helper.getOrderById(id);
            Toast.makeText(this, cursor.getString(1)+"'s order", Toast.LENGTH_LONG).show();
            int image = cursor.getInt(4);


            binding.detailImage.setImageResource(image);
            binding.pricelbl.setText(String.format("%d", cursor.getInt(3)));
            binding.foodname.setText(cursor.getString(7));
            binding.detailDescription.setText(cursor.getString(5));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));

            binding.insertButton.setText("Update your order");

            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                            boolean isUpdated = helper.updateOrder(
                                    binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.pricelbl.getText().toString()),
                                    image,
                                    binding.detailDescription.getText().toString(),
                                    binding.foodname.getText().toString(),
                                    1,
                                    id
                            );

                            if(isUpdated)
                            {
                                Toast.makeText(DetailActivity.this, "Order Updated", Toast.LENGTH_SHORT).show();
                            }

                            else
                            {
                                Toast.makeText(DetailActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                            }
                }
            });

        }

    }
}