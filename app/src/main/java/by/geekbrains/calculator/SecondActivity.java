package by.geekbrains.calculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    public static final String SHOW_SYMBOLS_KEY = "show_symbol_data";

    private TextView showInputNumberTextView;
    private TextView showOutputNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();
        SymbolData data = getIntent().getExtras().getParcelable(SHOW_SYMBOLS_KEY);
        getIONumber(data);
    }

    private void initView() {
        showInputNumberTextView = findViewById(R.id.show_input_number_text_view);
        showOutputNumberTextView = findViewById(R.id.show_output_number_text_view);
    }

    private void getIONumber(SymbolData data) {
        showInputNumberTextView.setText(data.getInputNumber());
        showOutputNumberTextView.setText(data.getOutputNumber());
    }
}