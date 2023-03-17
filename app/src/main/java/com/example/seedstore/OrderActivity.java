package com.example.seedstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.seedstore.Adapters.OrderAdapter;
import com.example.seedstore.Models.OrdersModel;
import com.example.seedstore.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);


        ArrayList<OrdersModel> list = helper.getOrders();


        OrderAdapter adapter = new OrderAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);


    }
}