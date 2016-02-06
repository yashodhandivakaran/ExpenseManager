package expensemanger.yashodhandivakaran.com.expensemanager.data.entities;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import expensemanger.yashodhandivakaran.com.expensemanager.data.tables.BillTable;

/**
 * Created by webyog on 06/02/16.
 */
public class Bill implements Parcelable{

    private int id;
    private long date;
    private int categoryId;
    private double amount;
    private String photo;
    private String title;
    private boolean claimed;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    public Bill(Parcel source) {
        this.id = source.readInt();
        this.date = source.readLong();
        this.categoryId = source.readInt();
        this.amount = source.readDouble();
        this.photo = source.readString();
        this.title = source.readString();
        this.claimed = source.readInt() == 1 ? true:false;
    }

    public Bill() {
    }

    public Bill(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndexOrThrow(BillTable.ID));
        this.date = cursor.getLong(cursor.getColumnIndexOrThrow(BillTable.DATE));
        this.categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(BillTable.CATEGORY_ID));
        this.amount = cursor.getDouble(cursor.getColumnIndexOrThrow(BillTable.AMOUNT));
        this.photo = cursor.getString(cursor.getColumnIndexOrThrow(BillTable.PHOTO));
        this.title = cursor.getString(cursor.getColumnIndexOrThrow(BillTable.TITLE));
        this.claimed = cursor.getInt(cursor.getColumnIndexOrThrow(BillTable.CLAIMED)) == 1? true:false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(date);
        dest.writeInt(categoryId);
        dest.writeDouble(amount);
        dest.writeString(photo);
        dest.writeString(title);
        dest.writeInt(claimed?1:0);
    }

    public static Creator<Bill> CREATOR = new Creator<Bill>() {

        @Override
        public Bill createFromParcel(Parcel source) {
            return new Bill(source);
        }

        @Override
        public Bill[] newArray(int size) {
            return new Bill[size];
        }
    };
}
