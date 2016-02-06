package expensemanger.yashodhandivakaran.com.expensemanager.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import expensemanger.yashodhandivakaran.com.expensemanager.data.EMDBWrapper;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Category;

/**
 * Created by webyog on 06/02/16.
 */
public class InsertNewCategoryTask extends AsyncTask<Category,Void,Category> {

    private Context context;
    private InsertNewCategoryTaskCallBack mListener;

    public interface InsertNewCategoryTaskCallBack{
        void categoryInserted(Category category);
    }

    public InsertNewCategoryTask(Context context, InsertNewCategoryTaskCallBack mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    protected Category doInBackground(Category... params) {
        EMDBWrapper dbWrapper = new EMDBWrapper(context);
        int id = dbWrapper.insertCategory(params[0]);
        params[0].setId(id);
        return params[0];
    }

    @Override
    protected void onPostExecute(Category category) {
        super.onPostExecute(category);
        mListener.categoryInserted(category);
    }
}
