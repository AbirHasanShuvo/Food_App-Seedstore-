package com.example.seedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.seedstore.Adapters.MainAdapter;
import com.example.seedstore.Models.MainModel;
import com.example.seedstore.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.biriyani, "Biriyani", "420", "Mutton biriyani with extra masala. Full package with some additional item"));
        list.add(new MainModel(R.drawable.kabab, "Kabab", "530", "Marinated in a home-made spice mix. Meat is flavorful"));
        list.add(new MainModel(R.drawable.burger, "Burger", "300", "Make room for our double cheese burger"));
        list.add(new MainModel(R.drawable.chocolates, "Chocolate", "900", "Awesome package included various type"));
        list.add(new MainModel(R.drawable.frenchfries, "Frenchfries", "380", "Premium quality packaged potatoes"));
        list.add(new MainModel(R.drawable.friedrice, "Friedrice", "320", "Fried Rice With Spicy Chicken Combo"));
        list.add(new MainModel(R.drawable.icecream, "Icecream", "370", "1 litre chocolate ice cream included fruits"));
        list.add(new MainModel(R.drawable.pizza, "Pizza", "290", "Topped with spicy chicken, tomatoes, onions, capsicum"));
        list.add(new MainModel(R.drawable.sandwich, "Sandwich", "270", "Special cheese sandwich"));
        list.add(new MainModel(R.drawable.steak, "Steak", "890", "Served with 2 sides and 2 types of sauce"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_LOW");
        MyReceiver myReceiver = new MyReceiver();
        registerReceiver(myReceiver, intentFilter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuorers:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}