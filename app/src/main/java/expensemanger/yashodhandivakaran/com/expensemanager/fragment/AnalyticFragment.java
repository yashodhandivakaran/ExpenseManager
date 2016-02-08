package expensemanger.yashodhandivakaran.com.expensemanager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.R;
import expensemanger.yashodhandivakaran.com.expensemanager.adapter.MonthlyAnalyticsAdapter;
import expensemanger.yashodhandivakaran.com.expensemanager.adapter.WeeklyAnalyticsAdapter;
import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.GetMonthlyAnalyticsTask;
import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.GetWeeklyAnalyticsTask;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.MonthlyAnalytics;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.WeekAnalytics;

public class AnalyticFragment extends Fragment implements GetMonthlyAnalyticsTask.GetMonthlyAnalyticsTaskCallback,
        GetWeeklyAnalyticsTask.GetWeeklyAnalyticsTaskCallback {
    private RecyclerView billRecyclerView;

    public static final int WEEKLY_ANALYTICS = 0;
    public static final int MONTHLY_ANALYTICS = 1;
    public static final String ANALYTICS_FRAGMENT_MODE = "fragment_mode";

    private List<WeekAnalytics> weekAnalyticses;
    private List<MonthlyAnalytics> monthlyAnalyticses;

    private RecyclerView.Adapter adapter ;


    private int FRAGMENT_MODE = WEEKLY_ANALYTICS;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_analytic, container, false);

        billRecyclerView = (RecyclerView) view.findViewById(R.id.bill_list);
        billRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        Bundle bundle = getArguments();
        FRAGMENT_MODE = bundle.getInt(ANALYTICS_FRAGMENT_MODE);

        if(FRAGMENT_MODE == WEEKLY_ANALYTICS){
            //set up weekly analytics adapter

            weekAnalyticses = new ArrayList<WeekAnalytics>();
            adapter = new WeeklyAnalyticsAdapter(getActivity(),weekAnalyticses);

            billRecyclerView.setAdapter(adapter);

            GetWeeklyAnalyticsTask weeklyAnalyticsTask = new GetWeeklyAnalyticsTask(getActivity(),this);
            weeklyAnalyticsTask.execute();

        }else if(FRAGMENT_MODE == MONTHLY_ANALYTICS){
            //set up monthly analytics adapter

            monthlyAnalyticses = new ArrayList<MonthlyAnalytics>();
            adapter = new MonthlyAnalyticsAdapter(getActivity(),monthlyAnalyticses);

            billRecyclerView.setAdapter(adapter);

            GetMonthlyAnalyticsTask monthlyAnalyticsTask = new GetMonthlyAnalyticsTask(getActivity(),this);
            monthlyAnalyticsTask.execute();
        }

        return view;
    }

    @Override
    public void monthlylyAnalytics(List<MonthlyAnalytics> analyticses) {
        monthlyAnalyticses.clear();
        monthlyAnalyticses.addAll(analyticses);

    }

    @Override
    public void weeklyAnalytics(List<WeekAnalytics> analyticses) {
        weekAnalyticses.clear();
        weekAnalyticses.addAll(analyticses);
        adapter.notifyDataSetChanged();
    }
}
