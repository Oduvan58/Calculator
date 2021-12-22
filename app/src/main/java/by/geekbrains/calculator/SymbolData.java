package by.geekbrains.calculator;

import java.io.Serializable;

public class SymbolData implements Serializable {

    private long number;

    public SymbolData() {
        number = 0;
    }

    public long getNumber() {
        return number;
    }

    public void writeNumber(String symbol, CharSequence view) {
        symbol = symbol + view;
        number = Long.parseLong(symbol);
    }
}
