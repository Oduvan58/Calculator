package by.geekbrains.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String NUMBER_SAVE_KEY = "number_key";

    private SymbolData dataIO;

    private TextView workingTextView;
    private TextView resultTextView;

    private Button zeroButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;

    private Button clearButton;
    private Button parenthesesButton;
    private Button percentButton;
    private Button divideButton;
    private Button multiplyButton;
    private Button subtractButton;
    private Button sumButton;
    private Button commaButton;
    private Button equalsButton;

    private String working = "";
    private int count = 0;
    private boolean countSymbol = false;
    private Double result = 0.0;
    private boolean countEquals = false;
    boolean checkParentheses = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataIO = new SymbolData();

        initViews();
        initShowSecondButton();
    }


    private void initShowSecondButton() {
        findViewById(R.id.show_second_button).setOnClickListener(v -> {
            Intent intentIO = new Intent(this, SecondActivity.class);
            putSymbolData();
            intentIO.putExtra(SecondActivity.SHOW_SYMBOLS_KEY, dataIO);
            startActivity(intentIO);
        });
    }

    private void putSymbolData() {
        dataIO.setInputNumber(workingTextView.getText().toString());
        dataIO.setOutputNumber(resultTextView.getText().toString());
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
        parenthesesButton = findViewById(R.id.parentheses_button);
        percentButton = findViewById(R.id.percent_button);
        divideButton = findViewById(R.id.divide_button);
        multiplyButton = findViewById(R.id.multiply_button);
        subtractButton = findViewById(R.id.subtract_button);
        sumButton = findViewById(R.id.sum_button);
        commaButton = findViewById(R.id.comma_button);
        equalsButton = findViewById(R.id.equals_button);
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
        parenthesesButton.setOnClickListener(this);
        percentButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        subtractButton.setOnClickListener(this);
        sumButton.setOnClickListener(this);
        commaButton.setOnClickListener(this);
        equalsButton.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        working = workingTextView.getText().toString();
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
                workingTextView.setText(working);
                break;
            case R.id.clear_button:
                workingTextView.setText("");
                resultTextView.setText("");
                break;
            case R.id.parentheses_button:
                if (checkParentheses) {
                    working = working + ")";
                    workingTextView.setText(working);
                    checkParentheses = false;
                } else {
                    working = working + "(";
                    workingTextView.setText(working);
                    checkParentheses = true;
                }
                break;
            case R.id.percent_button:
                if (working.length() != 0) {
                    working = working.replaceAll("%", "/100");
                    working = working + "%";
                    workingTextView.setText(working);
                }
                break;
            case R.id.divide_button:
                if (operationClick(working)) {
                    working = working + "/";
                    workingTextView.setText(working);
                }
                break;
            case R.id.multiply_button:
                if (operationClick(working)) {
                    working = working.replaceAll("x", "*");
                    working = working + "*";
                    workingTextView.setText(working);
                }
                break;
            case R.id.subtract_button:
                if (operationClick(working)) {
                    working = working + "-";
                    workingTextView.setText(working);
                }
                break;
            case R.id.sum_button:
                if (operationClick(working)) {
                    working = working + "+";
                    workingTextView.setText(working);
                }
                break;
            case R.id.comma_button:
                if (setComma(working)) {
                    working = working.replaceAll(",", ".");
                    working = working + ".";
                    workingTextView.setText(working);
                }
                break;
            case R.id.equals_button:
                Double result = null;
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                try {
                    result = (double) engine.eval(working);
                } catch (ScriptException e) {
                    e.printStackTrace();
                }

                if (result != null) {
                    resultTextView.setText(String.valueOf(result.doubleValue()));
                }
                break;
        }
    }

    private boolean operationClick(String work) {
        String[] operations = {"+", "/", "-", "*", "%"};
        for (int i = 0; i < operations.length; i++) {
            if (work.endsWith(operations[i]) || work.length() == 0) {
                return false;
            }
        }
        if (countEquals) {
            countSymbol = true;
            countEquals = false;
        }
        count = 0;
        return true;
    }

    private boolean setComma(String work) {
        String[] operations = {"+", "/", "-", "*", "%"};
        for (int i = 0; i < operations.length; i++) {
            if (work.endsWith(operations[i]) || work.length() == 0 || count == 1) {
                return false;
            }
        }
        count = 1;
        return true;
    }

    private void setTextNumber(TextView textNumber, String numbers) {
        textNumber.setText(numbers);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(NUMBER_SAVE_KEY, dataIO);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dataIO = savedInstanceState.getParcelable(NUMBER_SAVE_KEY);
        setTextNumbers();
    }

    private void setTextNumbers() {
        setTextNumber(workingTextView, dataIO.getInputNumber());
        setTextNumber(resultTextView, dataIO.getOutputNumber());
    }
}