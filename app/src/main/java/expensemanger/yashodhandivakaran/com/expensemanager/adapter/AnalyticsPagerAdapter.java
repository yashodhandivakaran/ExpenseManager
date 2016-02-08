package expensemanger.yashodhandivakaran.com.expensemanager.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import expensemanger.yashodhandivakaran.com.expensemanager.fragment.AnalyticFragment;

/**
 * Created by webyog on 07/02/16.
 */
public class AnalyticsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public AnalyticsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        int mode = AnalyticFragment.WEEKLY_ANALYTICS;

        switch (position){
            case 0:
                mode = AnalyticFragment.WEEKLY_ANALYTICS;
                break;
            case 1:
                mode = AnalyticFragment.MONTHLY_ANALYTICS;
                break;
        }

        AnalyticFragment analyticFragment = new AnalyticFragment();
        Bundle arg = new Bundle();
        arg.putInt(AnalyticFragment.ANALYTICS_FRAGMENT_MODE,mode);
        analyticFragment.setArguments(arg);
        return analyticFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Weekly";
                break;
            case 1:
                title = "Monthly";
                break;
        }
        return title;
    }
}
