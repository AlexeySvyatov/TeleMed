package com.example.telemed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AuthActivity extends AppCompatActivity {

    EditText loginField;
    EditText passField;
    Button authBtn;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        loginField = findViewById(R.id.loginField);
        passField = findViewById(R.id.passField);
        authBtn = findViewById(R.id.authBtn);

        sqlHelper = new DatabaseHelper(this);
        sqlHelper.createDB();
        db = sqlHelper.open();
    }

    public void mainWindow(View view) {
        System.out.println(loginField.toString());
        System.out.println(passField.toString());
        String[] columns = {DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PASSWORD};
        userCursor = db.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
        while(userCursor.moveToNext()){
            String name = userCursor.getString(0);
            String password = userCursor.getString(1);
            if(name.equals(loginField.getText().toString()) & password.equals(passField.getText().toString())){
                System.out.println("success");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }else{
                System.out.println("failed");
            }
        }
    }

    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}