package com.codsoft.todo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    Button updateButton;
    EditText updateTitle, updateDesc, updateTime;
    String key;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton = findViewById(R.id.updateButton);
        updateDesc = findViewById(R.id.updateDesc);
        updateTime = findViewById(R.id.updateTime);
        updateTitle = findViewById(R.id.updateTitle);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            updateTitle.setText(bundle.getString("Title"));
            updateDesc.setText(bundle.getString("Description"));
            updateTime.setText(bundle.getString("Time"));
            key = bundle.getString("Key");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Tasks").child(key);

        updateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year);
                        updateTime.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    public void saveData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        updateData();
        dialog.dismiss();
    }

    public void updateData() {
        String title = updateTitle.getText().toString().trim();
        String desc = updateDesc.getText().toString().trim();
        String time = updateTime.getText().toString();
        DataClass dataClass = new DataClass(title, desc, time);
        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
