package expensemanger.yashodhandivakaran.com.expensemanager;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by webyog on 06/02/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
