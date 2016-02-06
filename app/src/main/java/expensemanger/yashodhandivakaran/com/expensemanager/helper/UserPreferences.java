package expensemanger.yashodhandivakaran.com.expensemanager.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by webyog on 06/02/16.
 */
public class UserPreferences {

    private static SharedPreferences preference;
    private static UserPreferences preferenceStore;
    private Context mContext;

    private final String CREDENTIAL_STORE = "credential_store";

    public static final String USER_LOGGEDIN = "user_logged_in";

    private UserPreferences(Context context) {
        mContext = context;
        preference = context.getSharedPreferences(CREDENTIAL_STORE,0);
    }


    public static UserPreferences getInstance(Context context) {
        if (preferenceStore == null)
            preferenceStore = new UserPreferences(context);

        return preferenceStore;
    }

    public void setString(String key,String value){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringValue(String key,String defaultValue){
        return preference.getString(key,defaultValue);
    }

    public void setInt(String key,int value){
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getIntValue(String key,int defaultValue){
        return preference.getInt(key,defaultValue);
    }

    public void setBoolean(String key,boolean value){
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBooleanValue(String key,boolean defaultValue){
        return preference.getBoolean(key,defaultValue);
    }
}

