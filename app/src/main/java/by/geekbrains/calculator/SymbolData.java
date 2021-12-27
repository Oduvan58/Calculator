package by.geekbrains.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class SymbolData implements Parcelable {

    private String inputNumber;
    private String outputNumber;

    public SymbolData() {
        inputNumber = "";
        outputNumber = "";
    }

    public SymbolData(String inputNumber, String outputNumber) {
        setInputNumber(inputNumber);
        setOutputNumber(outputNumber);
    }

    protected SymbolData(Parcel in) {
        inputNumber = in.readString();
        outputNumber = in.readString();
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

    public String getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(String inputNumber) {
        this.inputNumber = inputNumber;
    }

    public String getOutputNumber() {
        return outputNumber;
    }

    public void setOutputNumber(String outputNumber) {
        this.outputNumber = outputNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(inputNumber);
        parcel.writeString(outputNumber);
    }
}
