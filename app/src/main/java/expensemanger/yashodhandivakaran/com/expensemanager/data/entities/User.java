package expensemanger.yashodhandivakaran.com.expensemanager.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by webyog on 06/02/16.
 */
public class User implements Parcelable {

    private int id;
    private String email;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(Parcel source) {
        this.id = source.readInt();
        this.email = source.readString();
        this.phone = source.readString();
    }

    public User() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(email);
        dest.writeString(phone);
    }

    public static Creator<User> CREATOR = new Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
