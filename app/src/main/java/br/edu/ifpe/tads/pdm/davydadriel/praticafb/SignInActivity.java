package br.edu.ifpe.tads.pdm.davydadriel.praticafb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    EditText edEmail;
    EditText edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        this.edEmail = findViewById(R.id.edit_email);
        this.edPassword = findViewById(R.id.edit_password);
    }

    public void buttonSignUpClick(View object) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void buttonSignInClick(View view) {
        String login = edEmail.getText().toString();
        String passwd = edPassword.getText().toString();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(login, passwd)
                .addOnCompleteListener(this, task -> {
                    String msg = task.isSuccessful() ? "SIGN IN OK!": "SIGN IN ERROR!";
                    Toast.makeText(SignInActivity.this, msg,
                            Toast.LENGTH_SHORT).show();
                });
    }
}