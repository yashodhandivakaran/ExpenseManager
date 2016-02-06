package expensemanger.yashodhandivakaran.com.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import expensemanger.yashodhandivakaran.com.expensemanager.adapter.CategoryListAdapter;
import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.AddNewBillTask;
import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.GetListOfCategoriesTask;
import expensemanger.yashodhandivakaran.com.expensemanager.asynctasks.InsertNewCategoryTask;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Bill;
import expensemanger.yashodhandivakaran.com.expensemanager.data.entities.Category;

public class BillActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        CategoryListAdapter.CategorySelectionCallback, GetListOfCategoriesTask.GetListOfCategoriesTaskCallback,
        InsertNewCategoryTask.InsertNewCategoryTaskCallBack,AddNewBillTask.AddNewBillTaskCallback {
    public static final int BILL_ADD_REQUEST_CODE = 2;

    public static final String BILL = "bill";
    public static final String MODE = "mode";

    public static final int BILL_IN_VIEW_MODE = 0;
    public static final int BILL_IN_CREATE_MODE = 1;

    private int mode = BILL_IN_CREATE_MODE;

    static final int REQUEST_TAKE_PHOTO = 1;

    private String mCurrentPhotoPath;

    private DatePickerDialog mDatePicker;

    private EditText date;

    private ImageView mThumbnail;
    private Category mCategory;

    private View pickCategory;


    private EditText category;
    private EditText title;
    private EditText amount;

    private long dateValue;

    private View addBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        mode = intent.getIntExtra(MODE, BILL_IN_CREATE_MODE);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Bill");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });


        if (mode == BILL_IN_CREATE_MODE) {
            setUpCreateView();
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.GONE);
        }

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            fab.setVisibility(View.GONE);
        }
    }


    private void setUpCreateView() {
        date = (EditText)findViewById(R.id.date);


        Calendar now = Calendar.getInstance();
        mDatePicker = DatePickerDialog.newInstance(
                BillActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        dateValue = now.getTimeInMillis();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePicker.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        mThumbnail = (ImageView) findViewById(R.id.bill_thumbnail);

        title = (EditText) findViewById(R.id.title);
        category = (EditText) findViewById(R.id.category);
        amount = (EditText) findViewById(R.id.amount);
        pickCategory = findViewById(R.id.pick_a_category);

        pickCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetListOfCategoriesTask categoriesTask = new GetListOfCategoriesTask(BillActivity.this,
                        BillActivity.this);
                categoriesTask.execute();
            }
        });

        addBill = findViewById(R.id.save);
        addBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCategory == null || !mCategory.getSummary().equals(category.getText().toString().trim())) {
                    //Insert a new category
                    Category categoryEntity = new Category();
                    categoryEntity.setSummary(category.getText().toString().trim());
                    categoryEntity.setDescription(category.getText().toString().trim());

                    InsertNewCategoryTask categoryTask = new InsertNewCategoryTask(BillActivity.this,
                            BillActivity.this);
                    categoryTask.execute(categoryEntity);
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {

            Glide.with(this)
                    .load(mCurrentPhotoPath)
                    .into(mThumbnail);
            if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                if (imageBitmap != null) {
                    mThumbnail.setImageBitmap(imageBitmap);
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,monthOfYear,dayOfMonth);
        dateValue = calendar.getTimeInMillis();

        date.setText(dayOfMonth+" - "+(monthOfYear+1)+" - "+year);
    }

    @Override
    public void setSelectedCategory(Category category) {
        this.mCategory = category;
        this.category.setText(category.getSummary());
    }

    @Override
    public void listOfCategories(List<Category> categories) {
        CategoryPickerDialog pickerDialog = new CategoryPickerDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(CategoryPickerDialog.CATEGORY_LIST, new ArrayList<Category>(categories));
        pickerDialog.setArguments(bundle);

        pickerDialog.show(getFragmentManager(), "CATEGORY");
    }

    @Override
    public void categoryInserted(Category category) {
        //TODO: add the bill
        Bill bill = new Bill();
        bill.setAmount(Double.valueOf(amount.getText().toString().trim()));
        bill.setTitle(title.getText().toString());
        bill.setCategoryId(category.getId());
        bill.setClaimed(false);
        bill.setDate(dateValue);
        bill.setPhoto(mCurrentPhotoPath);

        AddNewBillTask addNewBillTask = new AddNewBillTask(BillActivity.this,
                BillActivity.this);
        addNewBillTask.execute(bill);

    }

    @Override
    public void billAdded(Bill bill) {
        //TODO: update list activity
        Intent returnIntent = new Intent();
        returnIntent.putExtra(BILL, bill);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
