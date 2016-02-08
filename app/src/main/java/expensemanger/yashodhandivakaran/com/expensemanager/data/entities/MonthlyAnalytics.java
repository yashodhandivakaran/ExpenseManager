package expensemanger.yashodhandivakaran.com.expensemanager.data.entities;

import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by webyog on 07/02/16.
 */
public class MonthlyAnalytics {
    private String monthWithYear;
    private double amount;

    public MonthlyAnalytics(Cursor cursor) {

        int month = cursor.getInt(0);
        int year = cursor.getInt(1);
        this.amount = cursor.getDouble(2);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);

        SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy"); // PST`
        Date startDate = calendar.getTime();
        monthWithYear = formatter.format(startDate);
    }

    public String getMonthWithYear() {
        return monthWithYear;
    }

    public void setMonthWithYear(String monthWithYear) {
        this.monthWithYear = monthWithYear;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
