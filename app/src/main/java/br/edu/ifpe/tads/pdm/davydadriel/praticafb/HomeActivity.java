package br.edu.ifpe.tads.pdm.davydadriel.praticafb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import model.Message;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseAuthListener authListener;

    ViewGroup vgChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.mAuth = FirebaseAuth.getInstance();
        this.authListener = new FirebaseAuthListener(this);

        this.vgChat = findViewById(R.id.chat_area);
    }

    public void buttonSignOutClick(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            mAuth.signOut();
//            this.finish();
        } else {
            Toast.makeText(HomeActivity.this, "Erro!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authListener);
    }

    private void showMessage(Message message) {
        TextView tvMsg = new TextView(this);
        tvMsg.setText(message.getName() + ": " + message.getText());
        vgChat.addView(tvMsg);
    }
}