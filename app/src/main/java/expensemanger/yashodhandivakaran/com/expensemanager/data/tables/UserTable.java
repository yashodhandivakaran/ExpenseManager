package expensemanger.yashodhandivakaran.com.expensemanager.data.tables;

import android.content.ContentValues;

import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.User;

/**
 * Created by webyog on 06/02/16.
 */
public class UserTable {

    public static final String TABLE_NAME = "user_table";

    //columns
    public static final String ID = "_id";
    public static final String EMAIL = "_email";
    public static final String PHONE = "_phone";

    public static final String TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EMAIL+" TEXT, " +
                    PHONE+" TEXT " +
                    " );";


    public static ContentValues getContentValueObject(User user) {
        ContentValues cv = new ContentValues();
        cv.put(EMAIL,user.getEmail());
        cv.put(PHONE,user.getPhone());
        return cv;
    }

    public static final String TABLE_DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
