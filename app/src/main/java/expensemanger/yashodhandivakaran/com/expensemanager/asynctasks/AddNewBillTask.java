package expensemanger.yashodhandivakaran.com.expensemanager.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import expensemanger.yashodhandivakaran.com.expensemanager.data.EMDBWrapper;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Bill;

/**
 * Created by webyog on 06/02/16.
 */
public class AddNewBillTask extends AsyncTask<Bill,Void,Bill>{

    private Context context;
    private AddNewBillTaskCallback mListener;

    public interface AddNewBillTaskCallback{
        void billAdded(Bill bill);
    }

    public AddNewBillTask(Context context, AddNewBillTaskCallback mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    protected Bill doInBackground(Bill... params) {
        EMDBWrapper emdbWrapper = new EMDBWrapper(context);
        int id = emdbWrapper.insertBill(params[0]);
        params[0].setId(id);
        return params[0];
    }

    @Override
    protected void onPostExecute(Bill bill) {
        super.onPostExecute(bill);
        mListener.billAdded(bill);
    }
}
