package utils;

import java.text.DecimalFormat;

public class StringsUtils {

    public static String twoDecimalsFormatter(double value) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMinimumIntegerDigits(1);
        return decimalFormat.format(value);
    }
}
