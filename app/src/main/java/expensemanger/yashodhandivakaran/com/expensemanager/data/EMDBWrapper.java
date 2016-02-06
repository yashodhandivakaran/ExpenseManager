package expensemanger.yashodhandivakaran.com.expensemanager.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Bill;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Category;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.User;
import expensemanger.yashodhandivakaran.com.expensemanager.data.tables.BillTable;
import expensemanger.yashodhandivakaran.com.expensemanager.data.tables.CategoryTable;
import expensemanger.yashodhandivakaran.com.expensemanager.data.tables.UserTable;

/**
 * Created by webyog on 06/02/16.
 */
public class EMDBWrapper {

    private EMOpenHelper dbHelper;
    private Context context;

    public EMDBWrapper(Context context) {
        dbHelper = new EMOpenHelper(context);
        this.context = context;
    }

    //TODO: get list of categories
    public List<Category> getAllCategories() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CategoryTable.TABLE_NAME, null, null, null, null, null, null);
        //cursor.moveToFirst();
        ArrayList<Category> categoriesList = new ArrayList<Category>();
        while (cursor.moveToNext()) {
            categoriesList.add(new Category(cursor));
        }
        db.close();
        return categoriesList;
    }

    //TODO: get all bills in sorted order
    public List<Bill> getAllBills() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(BillTable.TABLE_NAME, null, null, null, null, null, BillTable.DATE + " DESC");
        //cursor.moveToFirst();
        ArrayList<Bill> billList = new ArrayList<Bill>();
        while (cursor.moveToNext()) {
            billList.add(new Bill(cursor));
        }
        db.close();
        return billList;
    }


    //TODO: get bills in specified range
    public List<Bill> getBillsInRange(long startDate, long endDate) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(BillTable.TABLE_NAME, null, null, null, null, null, BillTable.DATE + " DESC");
        //cursor.moveToFirst();
        ArrayList<Bill> billList = new ArrayList<Bill>();
        while (cursor.moveToNext()) {
            billList.add(new Bill(cursor));
        }
        db.close();
        return billList;
    }


    //TODO: add user
    public int insertUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowid = db.insertWithOnConflict(UserTable.TABLE_NAME, null,
                UserTable.getContentValueObject(user),
                SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
        return (int)rowid;
    }

    public int insertCategory(Category category){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowid = db.insertWithOnConflict(CategoryTable.TABLE_NAME, null,
                CategoryTable.getContentValueObject(category),
                SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
        return (int)rowid;
    }

    public int insertBill(Bill bill){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowid = db.insertWithOnConflict(BillTable.TABLE_NAME, null,
                BillTable.getContentValueObject(bill),
                SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
        return (int)rowid;
    }

    public void claimBill(Bill bill){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(BillTable.TABLE_NAME,BillTable.getContentValueObject(bill),
                BillTable.ID+"= ?",new String[]{String.valueOf(bill.getId())});
        db.close();
    }
}
