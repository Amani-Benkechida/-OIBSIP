package com.example.unit_conventor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText ed1;
    TextView tv1;
    Button b1,b2,b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ed1=findViewById(R.id.edit1);
        tv1=findViewById(R.id.textView2);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);

        b1.setOnClickListener(view -> {
            int x= Integer.parseInt(ed1.getText().toString());
            tv1.setText("metters" + (x*1000));
        });
        b2.setOnClickListener(view -> {
            int x= Integer.parseInt(ed1.getText().toString());
            tv1.setText("mm" + (x*1000000));
        });


    }
}