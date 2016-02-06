package expensemanger.yashodhandivakaran.com.expensemanager.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import expensemanger.yashodhandivakaran.com.expensemanager.data.EMDBWrapper;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Bill;

/**
 * Created by webyog on 06/02/16.
 */
public class ClaimBillTask extends AsyncTask<Bill,Void,Void> {

    private Context context;

    public ClaimBillTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Bill... params) {
        EMDBWrapper emdbWrapper = new EMDBWrapper(context);
        emdbWrapper.claimBill(params[0]);
        return null;
    }
}
