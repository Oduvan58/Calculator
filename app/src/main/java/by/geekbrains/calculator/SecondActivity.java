package by.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String SHOW_NUMBER_KEY = "show_number";

    private TextView showNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        showNumberTextView = findViewById(R.id.show_input_number_text_view);

        getInputNumber();
    }

    private void getInputNumber() {
        String number = getIntent().getExtras().getString(SHOW_NUMBER_KEY);
        showNumberTextView.setText(number);
    }
}