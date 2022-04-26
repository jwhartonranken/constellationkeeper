package tech.jameswharton.sqlitetest;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText etNameAdd;
    EditText etDomainAdd;
    EditText etBrightestAdd;
    EditText etMagnitudeAdd;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Get references to widgets
        etNameAdd = findViewById(R.id.etNameAdd);
        etDomainAdd = findViewById(R.id.etDomainAdd);
        etBrightestAdd = findViewById(R.id.etBrightestAdd);
        btnAdd = findViewById(R.id.btnSave);
        etMagnitudeAdd = findViewById(R.id.etMagnitudeAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper helper = new MyDBHelper(AddActivity.this);

                helper.addConstellation(etNameAdd.getText().toString().trim(),
                        etDomainAdd.getText().toString().trim(),
                        etBrightestAdd.getText().toString().trim(),
                        etMagnitudeAdd.getText().toString().trim());

                btnAdd.setEnabled(false);
            }
        });

        etNameAdd.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnAdd.setEnabled(true);
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}