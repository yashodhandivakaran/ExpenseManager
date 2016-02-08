package expensemanger.yashodhandivakaran.com.expensemanager.data.entities;

import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by webyog on 07/02/16.
 */
public class WeekAnalytics {
    private int weekNo;
    private String weekStart;
    private String weekEnd;
    private double amount;

    public WeekAnalytics(Cursor cursor) {
        this.weekNo = cursor.getInt(0);


        int year = cursor.getInt(1);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, weekNo);
        calendar.set(Calendar.YEAR, year);

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy"); // PST`
        Date startDate = calendar.getTime();
        weekStart = formatter.format(startDate);

        calendar.add(Calendar.DATE, 6);
        Date enddate = calendar.getTime();
        weekEnd = formatter.format(enddate);

        this.amount = cursor.getDouble(2);
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public String getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
