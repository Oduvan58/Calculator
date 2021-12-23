package by.geekbrains.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class SymbolData implements Parcelable {

    private long number;

    public SymbolData() {
        number = 0;
    }

    public SymbolData(long number) {
        setNumber(number);
    }

    protected SymbolData(Parcel in) {
        number = in.readLong();
    }

    public static final Creator<SymbolData> CREATOR = new Creator<SymbolData>() {
        @Override
        public SymbolData createFromParcel(Parcel in) {
            return new SymbolData(in);
        }

        @Override
        public SymbolData[] newArray(int size) {
            return new SymbolData[size];
        }
    };

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void writeNumber(String symbol, CharSequence view) {
        symbol = symbol + view;
        number = Long.parseLong(symbol);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(number);
    }
}
