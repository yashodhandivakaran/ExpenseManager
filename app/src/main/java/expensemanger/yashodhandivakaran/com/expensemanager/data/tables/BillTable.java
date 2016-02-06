package expensemanger.yashodhandivakaran.com.expensemanager.data.tables;

import android.content.ContentValues;

import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Bill;

/**
 * Created by webyog on 06/02/16.
 */
public class BillTable {

    public static final String TABLE_NAME = "bill_table";

    //columns
    public static final String ID = "_id";
    public static final String DATE = "_date";
    public static final String CATEGORY_ID = "_category_id";
    public static final String AMOUNT = "_amount";
    public static final String PHOTO  = "_photo";
    public static final String TITLE  = "_title";
    public static final String CLAIMED = "_claimed";

    public static final String TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATE+" INTEGER, " +
                    CATEGORY_ID+" INTEGER, " +
                    AMOUNT+" REAL, "+
                    PHOTO+" TEXT, "+
                    TITLE+" TEXT, "+
                    CLAIMED+" INTEGER "+
                    " );";


    public static ContentValues getContentValueObject(Bill bill) {
        ContentValues cv = new ContentValues();
//        cv.put(ID, bill.getId());
        cv.put(DATE,bill.getDate());
        cv.put(CATEGORY_ID,bill.getCategoryId());
        cv.put(AMOUNT,bill.getAmount());
        cv.put(PHOTO,bill.getPhoto());
        cv.put(TITLE,bill.getTitle());
        cv.put(CLAIMED,bill.isClaimed() ? 1:0);
        return cv;
    }

    public static final String TABLE_DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
