package tech.jameswharton.sqlitetest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabAdd;
    RecyclerView recyclerView;
    ArrayList<String> constellation_id;
    ArrayList<String> constellation_name;
    ArrayList<String> constellation_brightest;
    ArrayList<String> constellation_magnitude;
    ArrayList<String> constellation_domain;

    MyDBHelper helper;
    MyAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDelete();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAdd = findViewById(R.id.fabAdd);
        recyclerView = findViewById(R.id.mRecyclerView);

        constellation_id = new ArrayList<>();
        constellation_name = new ArrayList<>();
        constellation_brightest = new ArrayList<>();
        constellation_magnitude = new ArrayList<>();
        constellation_domain = new ArrayList<>();

        helper = new MyDBHelper(MainActivity.this);

        storeData();

        adapter = new MyAdapter(MainActivity.this,

                this,

                constellation_id, constellation_name,

                constellation_brightest, constellation_magnitude,
                constellation_domain);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    public void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all records?");
        builder.setMessage("Are you sure you want to delete all records?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                MyDBHelper myDB = new MyDBHelper(MainActivity.this);

                // Delete All Records
                myDB.deleteAllRecords();

                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }

        });

        builder.create().show();

    }

    public void storeData()
    {
        Cursor cursor = helper.readData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data To Read",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                constellation_id.add(cursor.getString(0));
                constellation_name.add(cursor.getString(1));
                constellation_brightest.add(cursor.getString(2));
                constellation_magnitude.add(cursor.getString(3));
                constellation_domain.add(cursor.getString(4));
            }
        }
    }
}