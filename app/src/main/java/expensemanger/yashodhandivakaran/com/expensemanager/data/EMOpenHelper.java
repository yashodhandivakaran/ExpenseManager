package expensemanger.yashodhandivakaran.com.expensemanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import expensemanger.yashodhandivakaran.com.expensemanager.data.tables.BillTable;
import expensemanger.yashodhandivakaran.com.expensemanager.data.tables.CategoryTable;
import expensemanger.yashodhandivakaran.com.expensemanager.data.tables.UserTable;

/**
 * Created by webyog on 06/02/16.
 */
public class EMOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "expense_manager.db";
    public static final int DATABASE_VERSION = 1;

    public EMOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.TABLE_CREATE);
        db.execSQL(CategoryTable.TABLE_CREATE);
        db.execSQL(BillTable.TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
