package expensemanger.yashodhandivakaran.com.expensemanager.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.data.EMDBWrapper;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Bill;

/**
 * Created by webyog on 06/02/16.
 */
public class GetListOfBillsTask extends AsyncTask<Void,Void,List<Bill>> {

    private Context context;
    private GetListOfBillsTaskCallBack mListener;

    public GetListOfBillsTask(Context context, GetListOfBillsTaskCallBack mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    public interface GetListOfBillsTaskCallBack{
        public void listOfBills(List<Bill> bills);
    }

    @Override
    protected List<Bill> doInBackground(Void... params) {
        EMDBWrapper emdbWrapper = new EMDBWrapper(context);

        return emdbWrapper.getAllBills();
    }

    @Override
    protected void onPostExecute(List<Bill> bills) {
        super.onPostExecute(bills);
        mListener.listOfBills(bills);
    }
}
