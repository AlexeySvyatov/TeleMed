package com.example.telemed;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.InputEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DBpath;
    private static String DBName = "telemed.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "users";
    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_PASSWORD = "password";
    private Context myContext;

    DatabaseHelper(Context context){
        super(context, DBName, null, SCHEMA);
        this.myContext = context;
        DBpath = context.getFilesDir().getPath() + DBName;
    }

    @Override public void onCreate(SQLiteDatabase db){}
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    void createDB(){
        File file = new File(DBpath);
        if(!file.exists()){
            try(InputStream myInput = myContext.getAssets().open(DBName);
                OutputStream myOutput = new FileOutputStream(DBpath)){
                byte[] buffer = new byte[1024];
                int length;
                while((length = myInput.read(buffer)) > 0){
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex) {
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    public SQLiteDatabase open() throws SQLException{
        return SQLiteDatabase.openDatabase(DBpath, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
