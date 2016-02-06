package expensemanger.yashodhandivakaran.com.expensemanager.data.entities;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import expensemanger.yashodhandivakaran.com.expensemanager.data.tables.CategoryTable;

/**
 * Created by webyog on 06/02/16.
 */
public class Category implements Parcelable{

    private int id;
    private String summary;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category(Parcel source) {
        this.id = source.readInt();
        this.summary = source.readString();
        this.description = source.readString();
    }

    public Category(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndexOrThrow(CategoryTable.ID));
        this.summary = cursor.getString(cursor.getColumnIndexOrThrow(CategoryTable.SUMMARY));
        this.description = cursor.getString(cursor.getColumnIndexOrThrow(CategoryTable.DESCRIPTION));
    }

    public Category() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(summary);
        dest.writeString(description);
    }

    public static Creator<Category> CREATOR = new Creator<Category>() {

        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
