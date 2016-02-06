package expensemanger.yashodhandivakaran.com.expensemanager.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import expensemanger.yashodhandivakaran.com.expensemanager.data.EMDBWrapper;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.User;

/**
 * Created by webyog on 06/02/16.
 */
public class SignUpUserTask extends AsyncTask<User,Void,User> {

    private Context context;
    private SignUpUserCallback listener;

    public interface SignUpUserCallback{
        void userInserted(User user);
    }

    public SignUpUserTask(Context context, SignUpUserCallback listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected User doInBackground(User... params) {
        EMDBWrapper dbWrapper = new EMDBWrapper(context);

        int id = dbWrapper.insertUser(params[0]);
        params[0].setId(id);

        return params[0];
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        listener.userInserted(user);
    }
}
