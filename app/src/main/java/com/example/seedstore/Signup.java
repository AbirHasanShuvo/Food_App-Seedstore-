package com.example.seedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.seedstore.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Signup extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegister();
            }
        });


    }

    public void userRegister()
    {
        String email = binding.signupEmailId.getText().toString().trim();
        String password = binding.signuppasswordid.getText().toString().trim();

        //System.out.println(email+"\n");
        //System.out.println(password);

        if(email.isEmpty())

        {

            binding.signupEmailId.setError("Enter an email address");

            binding.signupEmailId.requestFocus();

            return;

        }



        if(password.isEmpty())

        {

            binding.signuppasswordid.setError("Enter a password");

            binding.signuppasswordid.requestFocus();

            return;

        }



        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())

        {

            binding.signupEmailId.setError("Enter a valid email address");

            binding.signupEmailId.requestFocus();

            return;

        }// is not it a valid email



        if(password.length()<6)

        {

            binding.signuppasswordid.setError("Passwoord length should be 6 character");

            binding.signuppasswordid.requestFocus();

            return;

        }

        binding.signupprogressBarId.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
                binding.signupprogressBarId.setVisibility(View.GONE);

                if (task.isSuccessful())

                {

                    Toast.makeText(getApplicationContext(), "Register is successfull",Toast.LENGTH_SHORT).show();

                }



                else

                {

                    if(task.getException() instanceof FirebaseAuthUserCollisionException)// if account alreary registered

                    {
                        Toast.makeText(getApplicationContext(), "Account already registered",Toast.LENGTH_SHORT).show();
                    }

                    else // for any error

                    {
                        Toast.makeText(getApplicationContext(), "Error :"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });

    }
}