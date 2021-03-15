package mk.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button exit, simple, advanced, about;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit = findViewById(R.id.btnExit);
        simple = findViewById(R.id.btnSimple);
        advanced = findViewById(R.id.btnAdvanced);
        about = findViewById(R.id.btnAbout);

        exit.setOnClickListener(v -> finish());
        simple.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CalculatorSimpleActivity.class);
            startActivity(intent);
        });
        advanced.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CalculatorAdvancedActivity.class);
            startActivity(intent);
        });
        about.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
        });
    }
}