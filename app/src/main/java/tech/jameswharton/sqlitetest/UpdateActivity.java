package tech.jameswharton.sqlitetest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText nameUpdate;
    EditText domainUpdate;
    EditText brightestUpdate;
    EditText magnitudeUpdate;
    Button btnUpdate;
    Button btnDelete;


    String id;
    String name;
    String domain;
    String brightestStar;
    String starMagnitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameUpdate = findViewById(R.id.etNameAdd);
        domainUpdate = findViewById(R.id.etDomainAdd);
        brightestUpdate = findViewById(R.id.etBrightestAdd);
        magnitudeUpdate = findViewById(R.id.etMagnitudeAdd);
        btnUpdate = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        getAndSetIntentData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper myDB = new MyDBHelper(UpdateActivity.this);
                name = nameUpdate.getText().toString().trim();
                domain = domainUpdate.getText().toString().trim();
                brightestStar = brightestUpdate.getText().toString().trim();
                starMagnitude = magnitudeUpdate.getText().toString().trim();
                myDB.updateData(id, name, domain, brightestStar, starMagnitude);


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    private void getAndSetIntentData() {
        if( getIntent().hasExtra("id") &&
            getIntent().hasExtra("name") &&
            getIntent().hasExtra("domain") &&
            getIntent().hasExtra("brightest") &&
            getIntent().hasExtra("magnitude")) {
            // Get data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            domain = getIntent().getStringExtra("domain");
            brightestStar = getIntent().getStringExtra("brightest");
            starMagnitude = getIntent().getStringExtra("magnitude");
            // Set Data
            nameUpdate.setText(name);
            domainUpdate.setText(domain);
            brightestUpdate.setText(brightestStar);
            magnitudeUpdate.setText(starMagnitude);
        }
        else {
            Toast.makeText(this, "No Data for Update", Toast.LENGTH_LONG).show();
        }
    }

    public void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete " + name + " ?");

        builder.setMessage("Are you sure you want to delete " + name + " ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {

                MyDBHelper myDB = new MyDBHelper(UpdateActivity.this);

                myDB.deleteOneRecord(id);

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

    @Override
    public void onBackPressed() {

    }
}