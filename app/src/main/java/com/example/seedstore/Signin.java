package com.example.seedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.seedstore.databinding.ActivityMainBinding;
import com.example.seedstore.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {

    ActivitySigninBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signinTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signin.this, Signup.class);
                startActivity(intent);
            }
        });

        binding.signinButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

    }

    private void userLogin() {

        String email = binding.signinEmailId.getText().toString().trim();

        String password = binding.signinpasswordId.getText().toString().trim();



        if(email.isEmpty())

        {

            binding.signinEmailId.setError("Enter an email address");

            binding.signinEmailId.requestFocus();

            return;

        }



        if(password.isEmpty())

        {

            binding.signinpasswordId.setError("Enter a password");

            binding.signinpasswordId.requestFocus();

            return;

        }



        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())

        {

            binding.signinEmailId.setError("Enter a valid email address");

            binding.signinEmailId.requestFocus();

            return;

        }// is not it a valid email



        if(password.length()<6)

        {

            binding.signinpasswordId.setError("Passwoord length should be 6 character");
            binding.signinpasswordId.requestFocus();
            return;
        }

        binding.signinprogressBarId.setVisibility(View.VISIBLE);



        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            //this will automatically check our email password correct or not

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                binding.signinprogressBarId.setVisibility(View.GONE);

                if(task.isSuccessful())
                {
                    Intent intent = new Intent(Signin.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                else

                {

                    Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_SHORT).show();

                }

            }

        });

    }
}