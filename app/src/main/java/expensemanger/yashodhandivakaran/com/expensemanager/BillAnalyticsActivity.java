package expensemanger.yashodhandivakaran.com.expensemanager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.adapter.AnalyticsPagerAdapter;
import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.GetMonthlyAnalyticsTask;
import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.GetWeeklyAnalyticsTask;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.MonthlyAnalytics;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.WeekAnalytics;

public class BillAnalyticsActivity extends AppCompatActivity  implements GetWeeklyAnalyticsTask.GetWeeklyAnalyticsTaskCallback,
        GetMonthlyAnalyticsTask.GetMonthlyAnalyticsTaskCallback{

    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_analytics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewpager = (ViewPager) findViewById(R.id.pager);
        AnalyticsPagerAdapter analyticsPagerAdapter = new AnalyticsPagerAdapter(getSupportFragmentManager(),this);
        viewpager.setAdapter(analyticsPagerAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Analytics");

        GetMonthlyAnalyticsTask task = new GetMonthlyAnalyticsTask(this,this);
        task.execute();

        GetWeeklyAnalyticsTask task1 = new GetWeeklyAnalyticsTask(this,this);
        task1.execute();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void monthlylyAnalytics(List<MonthlyAnalytics> analyticses) {

    }

    @Override
    public void weeklyAnalytics(List<WeekAnalytics> analyticses) {

    }
}
