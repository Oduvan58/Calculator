package by.geekbrains.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String NUMBER_SAVE_KEY = "number_key";

    private TextView workingTextView;
    private TextView resultTextView;

    private Button zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton
            , sevenButton, eightButton, nineButton, clearButton;

    private long number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_SAVE_KEY)) {
            number = savedInstanceState.getLong(NUMBER_SAVE_KEY);
        }

        showInput();
    }

    private void initViews() {
        workingTextView = findViewById(R.id.work_text_view);
        resultTextView = findViewById(R.id.result_text_view);
        zeroButton = findViewById(R.id.number_zero_button);
        oneButton = findViewById(R.id.number_one_button);
        twoButton = findViewById(R.id.number_two_button);
        threeButton = findViewById(R.id.number_three_button);
        fourButton = findViewById(R.id.number_four_button);
        fiveButton = findViewById(R.id.number_five_button);
        sixButton = findViewById(R.id.number_six_button);
        sevenButton = findViewById(R.id.number_seven_button);
        eightButton = findViewById(R.id.number_eight_button);
        nineButton = findViewById(R.id.number_nine_button);
        clearButton = findViewById(R.id.clear_button);
        symbolOnClick();
    }

    private void symbolOnClick() {
        zeroButton.setOnClickListener(this);
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String working = workingTextView.getText().toString();
        switch (view.getId()) {
            case R.id.number_zero_button:
            case R.id.number_one_button:
            case R.id.number_two_button:
            case R.id.number_three_button:
            case R.id.number_four_button:
            case R.id.number_five_button:
            case R.id.number_six_button:
            case R.id.number_seven_button:
            case R.id.number_eight_button:
            case R.id.number_nine_button:
                working = working + ((Button) view).getText();
                number = Long.parseLong(working);
                showInput();
                break;
            case R.id.clear_button:
                workingTextView.setText("");
                break;
        }
    }

    private void showInput() {
        workingTextView.setText(String.valueOf(number));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(NUMBER_SAVE_KEY, number);
    }
}