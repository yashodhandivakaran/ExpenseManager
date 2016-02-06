package expensemanger.yashodhandivakaran.com.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.SignUpUserTask;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.User;
import expensemanger.yashodhandivakaran.com.expensemanager.helper.UserPreferences;

public class SignUpAcitivity extends AppCompatActivity implements SignUpUserTask.SignUpUserCallback {

    public static final int SIGNUP_REQUEST_CODE = 2;
    public static final String USER_DATA = "user_data";


    private EditText email;
    private EditText phone;
    private View signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_acitivity);

        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);

        signUpBtn = findViewById(R.id.sign_up_button);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(email.getText().toString().trim());
                user.setPhone(phone.getText().toString().trim());

                SignUpUserTask task = new SignUpUserTask(SignUpAcitivity.this,SignUpAcitivity.this);
                task.execute(user);
            }
        });


    }

    @Override
    public void userInserted(User user) {

        UserPreferences userPreferences = UserPreferences.getInstance(this);
        userPreferences.setBoolean(UserPreferences.USER_LOGGEDIN,true);


        Intent returnIntent = new Intent();
        returnIntent.putExtra(USER_DATA,user);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
