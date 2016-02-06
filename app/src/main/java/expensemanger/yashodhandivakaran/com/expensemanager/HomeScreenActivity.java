package expensemanger.yashodhandivakaran.com.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.adapter.BillListAdapter;
import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.GetListOfBillsTask;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Bill;
import expensemanger.yashodhandivakaran.com.expensemanager.helper.UserPreferences;

public class HomeScreenActivity extends AppCompatActivity implements GetListOfBillsTask.GetListOfBillsTaskCallBack{

    private RecyclerView billRecyclerView;
    private List<Bill> billList;
    private BillListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        billList = new ArrayList<Bill>();

        adapter = new BillListAdapter(billList,this);
        billRecyclerView = (RecyclerView)findViewById(R.id.bill_list);
        billRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        billRecyclerView.setAdapter(adapter);


        if(!isUserLoggedIn()){
            //If user is not logged in then ask user to sign up
            Intent start = new Intent(HomeScreenActivity.this,SignUpAcitivity.class);
            startActivityForResult(start, SignUpAcitivity.SIGNUP_REQUEST_CODE);
        }else{
            fetchBills();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add a bill
                Intent addBill = new Intent(HomeScreenActivity.this, BillActivity.class);
                addBill.putExtra(BillActivity.MODE,BillActivity.BILL_IN_CREATE_MODE);
                startActivityForResult(addBill, BillActivity.BILL_ADD_REQUEST_CODE);
            }
        });
    }

    private void fetchBills(){
        GetListOfBillsTask billsTask = new GetListOfBillsTask(this,this);
        billsTask.execute();
    }

    private boolean isUserLoggedIn(){
        UserPreferences userPreferences = UserPreferences.getInstance(this);
        return userPreferences.getBooleanValue(UserPreferences.USER_LOGGEDIN,false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_analytics) {
            Intent analytics = new Intent(this,BillAnalyticsActivity.class);
            startActivity(analytics);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SignUpAcitivity.SIGNUP_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                fetchBills();
            }
        }

        if(requestCode == BillActivity.BILL_ADD_REQUEST_CODE && requestCode == Activity.RESULT_OK){
            Bill bill = data.getParcelableExtra(BillActivity.BILL);
            adapter.updateBill(bill);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Bill: " + bill.getTitle() + " -- Added", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void listOfBills(List<Bill> bills) {
        this.billList.clear();
        this.billList.addAll(bills);
        adapter.notifyDataSetChanged();
    }
}
