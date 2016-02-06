package expensemanger.yashodhandivakaran.com.expensemanager.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.data.EMDBWrapper;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Category;

/**
 * Created by webyog on 06/02/16.
 */
public class GetListOfCategoriesTask extends AsyncTask<Void,Void,List<Category>> {

    private Context mContext;
    private GetListOfCategoriesTaskCallback mListener;

    public interface GetListOfCategoriesTaskCallback{
        public void listOfCategories(List<Category> categories);
    }

    public GetListOfCategoriesTask(Context mContext, GetListOfCategoriesTaskCallback mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    protected List<Category> doInBackground(Void... params) {
        EMDBWrapper dbWrapper = new EMDBWrapper(mContext);
        return dbWrapper.getAllCategories();
    }

    @Override
    protected void onPostExecute(List<Category> categories) {
        super.onPostExecute(categories);

        if(categories.isEmpty()){
            Toast.makeText(mContext,"Categories not present",Toast.LENGTH_LONG).show();
        }else {
            mListener.listOfCategories(categories);
        }
    }
}
