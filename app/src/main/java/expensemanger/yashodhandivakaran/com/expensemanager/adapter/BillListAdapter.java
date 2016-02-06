package expensemanger.yashodhandivakaran.com.expensemanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.R;
import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.ClaimBillTask;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Bill;

/**
 * Created by webyog on 06/02/16.
 */
public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.ViewHolder> {

    private List<Bill> bills;
    private Context mContext;

    public BillListAdapter(List<Bill> bills, Context mContext) {
        this.bills = bills;
        this.mContext = mContext;
    }

    public void updateBill(Bill newBill){
        bills.add(0, newBill);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView billTitle;
        public TextView billAmount;

        public View claim;

        public ViewHolder(View itemView,View claim) {
            super(itemView);
            this.claim = claim;
            claim.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ClaimBillTask task = new ClaimBillTask(mContext);
            Bill bill = bills.get(getAdapterPosition());
            bill.setClaimed(true);
            task.execute(bill);
            Toast.makeText(mContext, "Expense Claimed", Toast.LENGTH_LONG).show();
            v.setVisibility(View.GONE);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_row,parent,false);

        ViewHolder vh = new ViewHolder(v,v.findViewById(R.id.claim));

        vh.billTitle = (TextView)v.findViewById(R.id.bill_title);
        vh.billAmount = (TextView)v.findViewById(R.id.bill_amount);
        vh.claim = v.findViewById(R.id.claim);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Bill bill = bills.get(position);

        holder.billTitle.setText(bill.getTitle());
        holder.billAmount.setText("Rs "+bill.getAmount());

        if(bill.isClaimed()) {
            holder.claim.setVisibility(View.GONE);
        }else {
            holder.claim.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }
}
