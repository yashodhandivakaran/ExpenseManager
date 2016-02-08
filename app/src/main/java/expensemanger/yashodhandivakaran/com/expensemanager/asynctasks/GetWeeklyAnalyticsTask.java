package expensemanger.yashodhandivakaran.com.expensemanager.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.data.EMDBWrapper;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.WeekAnalytics;

/**
 * Created by webyog on 07/02/16.
 */
public class GetWeeklyAnalyticsTask extends AsyncTask<Void,Void,List<WeekAnalytics>> {

    public interface GetWeeklyAnalyticsTaskCallback{
        void weeklyAnalytics(List<WeekAnalytics> analyticses);
    }

    private Context context;
    private GetWeeklyAnalyticsTaskCallback mListener;

    public GetWeeklyAnalyticsTask(Context context, GetWeeklyAnalyticsTaskCallback mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    protected List<WeekAnalytics> doInBackground(Void... params) {
        EMDBWrapper emdbWrapper = new EMDBWrapper(context);
        return emdbWrapper.getWeeklyAnalytics();
    }

    @Override
    protected void onPostExecute(List<WeekAnalytics> weekAnalyticses) {
        super.onPostExecute(weekAnalyticses);
        mListener.weeklyAnalytics(weekAnalyticses);
    }
}
