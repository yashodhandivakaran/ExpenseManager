package expensemanger.yashodhandivakaran.com.expensemanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.R;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.WeekAnalytics;

/**
 * Created by webyog on 07/02/16.
 */
public class WeeklyAnalyticsAdapter extends RecyclerView.Adapter<WeeklyAnalyticsAdapter.ViewHolder> {

    private Context context;
    private List<WeekAnalytics> weekAnalyticses;

    public WeeklyAnalyticsAdapter(Context context, List<WeekAnalytics> weekAnalyticses) {
        this.context = context;
        this.weekAnalyticses = weekAnalyticses;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView timeRange;
        public TextView amount;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weekly_analytics_row,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.timeRange = (TextView)v.findViewById(R.id.time_range);
        viewHolder.amount = (TextView)v.findViewById(R.id.amount);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        WeekAnalytics weekAnalytics = weekAnalyticses.get(position);

        holder.timeRange.setText(weekAnalytics.getWeekStart()+" - "+weekAnalytics.getWeekEnd());
        holder.amount.setText(String.valueOf(weekAnalytics.getAmount()));

    }

    @Override
    public int getItemCount() {
        return weekAnalyticses.size();
    }
}
