package expensemanger.yashodhandivakaran.com.expensemanager.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.data.EMDBWrapper;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.MonthlyAnalytics;

/**
 * Created by webyog on 07/02/16.
 */
public class GetMonthlyAnalyticsTask extends AsyncTask<Void,Void,List<MonthlyAnalytics>> {

    public interface GetMonthlyAnalyticsTaskCallback{
        void monthlylyAnalytics(List<MonthlyAnalytics> analyticses);
    }

    private Context context;
    private GetMonthlyAnalyticsTaskCallback mListener;

    public GetMonthlyAnalyticsTask(Context context, GetMonthlyAnalyticsTaskCallback mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    protected List<MonthlyAnalytics> doInBackground(Void... params) {
        EMDBWrapper emdbWrapper = new EMDBWrapper(context);
        return emdbWrapper.getMonthlyAnalytics();
    }

    @Override
    protected void onPostExecute(List<MonthlyAnalytics> monthlyAnalyticses) {
        super.onPostExecute(monthlyAnalyticses);
        mListener.monthlylyAnalytics(monthlyAnalyticses);
    }
}
