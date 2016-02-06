package expensemanger.yashodhandivakaran.com.expensemanager.data.tables;

import android.content.ContentValues;

import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Category;

/**
 * Created by webyog on 06/02/16.
 */
public class CategoryTable {

    public static final String TABLE_NAME = "category_table";

    //columns
    public static final String ID = "_id";
    public static final String SUMMARY = "_summary";
    public static final String DESCRIPTION = "_description";


    public static final String TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SUMMARY+" TEXT, " +
                    DESCRIPTION+" TEXT " +
                    " );";


    public static ContentValues getContentValueObject(Category category) {
        ContentValues cv = new ContentValues();
//        cv.put(ID, category.getId());
        cv.put(SUMMARY,category.getSummary());
        cv.put(DESCRIPTION,category.getDescription());
        return cv;
    }

    public static final String TABLE_DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME;



}
