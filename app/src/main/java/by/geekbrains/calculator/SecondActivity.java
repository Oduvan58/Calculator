package by.geekbrains.calculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    public static final String SHOW_SYMBOLS_KEY = "show_symbol_data";

    private TextView showNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        showNumberTextView = findViewById(R.id.show_input_number_text_view);

        getInputNumber();
    }

    private void getInputNumber() {
        SymbolData data = getIntent().getExtras().getParcelable(SHOW_SYMBOLS_KEY);
        putView(data);
    }

    private void putView(SymbolData data) {
        showNumberTextView.setText(String.valueOf(data.getNumber()));
    }
}