package com.example.amine_omayma_exam_m1_iibdcc_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameText , passwordText ;
    Button login, load, delete ;

    SharedPreferences sharedPreferences;
    public static final String myPreference = "loginpref";
    public static final String username = "usernameKey";
    public static final String password = "passwordKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);

        login = findViewById(R.id.loginButton);
        load = findViewById(R.id.saveButton);
        delete = findViewById(R.id.deleteButton);

        sharedPreferences = getApplicationContext().getSharedPreferences(myPreference, MODE_PRIVATE);
        if (sharedPreferences.contains(username)) {
            usernameText.setText(sharedPreferences.getString(username, ""));
        }
        if (sharedPreferences.contains(password)) {
            passwordText.setText(sharedPreferences.getString(password, ""));
        }

    }

    public void login(View view){
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        if(!usernameText.getText().toString().isEmpty() && !passwordText.getText().toString().isEmpty()){
            Intent loginIntent = new Intent(this, IPFinderActivity.class);
            startActivity(loginIntent);
        }
        else {
            Toast.makeText(this,"Données invalides",Toast.LENGTH_LONG).show();

        }
    }
    public void save(View view){
        String name = usernameText.getText().toString();
        String pass = passwordText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("usernameKey",true);
        editor.putBoolean("passwordKey",true);
        editor.putString("usernameKey",name);
        editor.putString("passwordKey",pass);
        editor.commit();
    }

    public void clear(View view){
        usernameText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("usernameKey");
        editor.remove("passwordKey");
        editor.commit();

        usernameText.setText("");
        passwordText.setText("");

    }
}