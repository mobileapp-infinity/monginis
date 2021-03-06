package com.infinity.monginis.dashboard.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.adapter.TopCategoriesAdapter;
import com.infinity.monginis.dashboard.pojo.GetCategoryForDashboardPojo;
import com.infinity.monginis.utils.ConnectionDetector;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.infinity.monginis.utils.IntentConstants.SPECIAL_ORDER_UPLOAD_PHOTO_GALLERY_REQUEST;

public class BottomSheetDialogForSpecialOrder extends BottomSheetDialogFragment implements View.OnClickListener {


    SearchableSpinner spMenu;
    SearchableSpinner spSpecialCakeShop;
    SearchableSpinner spWeight;
    SearchableSpinner spFlavour;
    SearchableSpinner spQty;
    Button btnProceed;
    private ItemDetailsActivity activity;
    TextViewRegularFont tvDeliveryDate, tvPhotoUpload;
    ConnectionDetector connectionDetector;
    public MultipartBody.Part specialOrderPhotoUpload = null;

    public BottomSheetDialogForSpecialOrder(ItemDetailsActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_for_special_order_bottom_sheet_dialog,
                container, false);
        initView(view);

        setMenu();
        setCakeShape();
        setFlavours();
        setWeight();
        setQty();
        return view;
    }


    private void initView(View view) {

        spFlavour = view.findViewById(R.id.spFlavour);
        connectionDetector = new ConnectionDetector(activity);
        spMenu = view.findViewById(R.id.spMenu);
        spSpecialCakeShop = view.findViewById(R.id.spSpecialCakeShop);
        spWeight = view.findViewById(R.id.spWeight);
        spQty = view.findViewById(R.id.spQty);
        tvDeliveryDate = view.findViewById(R.id.tvDeliveryDate);
        tvPhotoUpload = view.findViewById(R.id.tvPhotoUpload);
        btnProceed = view.findViewById(R.id.btnProceed);

        sdf_full = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        serverDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        tvDeliveryDate.setOnClickListener(this);
        btnProceed.setOnClickListener(this);
        tvPhotoUpload.setOnClickListener(this);

    }


    ArrayList<String> menuList;
    ArrayList<String> flavourList;
    ArrayList<String> weightList;
    ArrayList<String> cakeShapeList;
    ArrayList<String> qtyList;

    private void setMenu() {

        menuList = new ArrayList<>();
        menuList.add("Menu");
        menuList.add("Test 1");
        menuList.add("Test 2");
        menuList.add("Test 3");
        menuList.add("Test 4");

        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_common_layout, menuList);
        menuAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
        spMenu.setTitle("Select Menu");

        spMenu.setAdapter(menuAdapter);


    }

    private void setFlavours() {

        flavourList = new ArrayList<>();
        flavourList.add("Flavour");
        flavourList.add("Test 1");
        flavourList.add("Test 2");
        flavourList.add("Test 3");
        flavourList.add("Test 4");

        ArrayAdapter<String> flavoursAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_common_layout, flavourList);
        flavoursAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
        spFlavour.setTitle("Select Flavours");
        spFlavour.setAdapter(flavoursAdapter);


    }

   /* private void getFlavours() {
        if (connectionDetector.isConnectingToInternet()) {


            ApiImplementer.getFlavoursImplementer("1", "1", "1", "0", ApiUrls.TESTING_KEY, "1", new Callback<GetCategoryForDashboardPojo>() {
                @Override
                public void onResponse(Call<GetCategoryForDashboardPojo> call, Response<GetCategoryForDashboardPojo> response) {



                    try {
                        if (response.isSuccessful() && response.body() != null
                        ) {







                        } else {


                            llCategoryProgressbar.setVisibility(View.GONE);
                            rvTopCategories.setVisibility(View.VISIBLE);
                        }

                    } catch (Exception e) {
                        llCategoryProgressbar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<GetCategoryForDashboardPojo> call, Throwable t) {

                    Toast.makeText(activity, "Error in response" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    llCategoryProgressbar.setVisibility(View.GONE);
                    rvTopCategories.setVisibility(View.VISIBLE);
                }
            });
        } else {
            Toast.makeText(activity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }*/

    private void setCakeShape() {
        cakeShapeList = new ArrayList<>();
        cakeShapeList.add("Select Cake Shape");
        cakeShapeList.add("Cake Shape 1");
        cakeShapeList.add("Cake Shape 2");
        cakeShapeList.add("Cake Shape 3");
        cakeShapeList.add("Cake Shape 4");

        ArrayAdapter<String> cakeShapeAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_common_layout, cakeShapeList);
        cakeShapeAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
        spSpecialCakeShop.setTitle("Select Cake Shape");
        //  spSpecialCakeShop.setBackgroundDrawable(R.drawable.ic_baseline_expand_more_24);
        spSpecialCakeShop.setAdapter(cakeShapeAdapter);


    }

    private void setWeight() {
        weightList = new ArrayList<>();
        weightList.add("Weight");
        weightList.add("Weight 1");
        weightList.add("Weight 2");
        weightList.add("Weight 3");
        weightList.add("Weight 4");

        ArrayAdapter<String> cakeShapeAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_common_layout, weightList);
        cakeShapeAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
        spWeight.setTitle("Select Weight");
        spWeight.setAdapter(cakeShapeAdapter);


    }

    private void setQty() {
        qtyList = new ArrayList<>();
        qtyList.add("Quantity");
        qtyList.add("1");
        qtyList.add("2");
        qtyList.add("3");
        qtyList.add("4");
        qtyList.add("5");
        qtyList.add("6");

        ArrayAdapter<String> cakeShapeAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_common_layout, qtyList);
        cakeShapeAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
        spQty.setTitle("Select Qty");
        spQty.setAdapter(cakeShapeAdapter);


    }


    private boolean isValidated() {
        boolean flag = true;

        if (spMenu.getSelectedItemPosition() == 0) {

            Toast.makeText(activity, "Please Select Menu", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (spSpecialCakeShop.getSelectedItemPosition() == 0) {
            Toast.makeText(activity, "Please Select Cake Shape", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (spWeight.getSelectedItemPosition() == 0) {
            Toast.makeText(activity, "Please Select Weight", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (spFlavour.getSelectedItemPosition() == 0) {
            Toast.makeText(activity, "Please Select Flavour", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (tvPhotoUpload.getText().toString().contentEquals("Upload Photo")) {
            Toast.makeText(activity, "Please Select Photo", Toast.LENGTH_LONG).show();
            flag = false;

        } else if (spQty.getSelectedItemPosition() == 0) {
            Toast.makeText(activity, "Please Select Quantity", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (tvDeliveryDate.getText().toString().contentEquals("Delivery Date")) {
            Toast.makeText(activity, "Please Enter Delivery Date", Toast.LENGTH_LONG).show();
            flag = false;
        }

        return flag;


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.tvDeliveryDate) {
            deliveryDateDialog();
        } else if (view.getId() == R.id.btnProceed) {
           // if (isValidated()) {
                System.out.println("Done=============");

                Intent customizeScreenIntent = new Intent(activity, CustomizeScreenActivity.class);
                startActivity(customizeScreenIntent);
          //  }
        } else if (view.getId() == R.id.tvPhotoUpload) {
            Intent intent = new Intent(activity, com.jaiselrahman.filepicker.activity.FilePickerActivity.class);


            intent.putExtra(com.jaiselrahman.filepicker.activity.FilePickerActivity.CONFIGS, new Configurations.Builder()
                    .setCheckPermission(true)
                    .setShowImages(true)
                    .setShowAudios(false)
                    .setShowVideos(false)

                    .enableImageCapture(false)
                    .setMaxSelection(1)

                    .setSkipZeroSizeFiles(true)
                    .build());
            startActivityForResult(intent, SPECIAL_ORDER_UPLOAD_PHOTO_GALLERY_REQUEST);

        }

    }

    private Date deliveryDate;
    private SimpleDateFormat sdf_full, serverDateFormat;
    private String selectedDeliveryDateString;


    File specialOrderFileUpload = null;

    public void deliveryDateDialog() {
        int mYear = 0, mMonth = 0, mDay = 0;
        final Calendar c = Calendar.getInstance();
        if (deliveryDate == null) {
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        } else {
            c.setTimeInMillis(deliveryDate.getTime());
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        }
        DatePickerDialog dialog = new DatePickerDialog(activity, R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year,
                                  int monthOfYear, int dayOfMonth) {
                try {
                    StringBuilder theDate = new StringBuilder()
                            .append(dayOfMonth).append("-")
                            .append(monthOfYear + 1).append("-")
                            .append(year);

                    try {
                        deliveryDate = sdf_full.parse(theDate.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    selectedDeliveryDateString = serverDateFormat.format(deliveryDate);
                    tvDeliveryDate.setText(sdf_full.format(deliveryDate));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }


            }
        }, mYear, mMonth, mDay);
        dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", dialog);

        dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", dialog);

        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SPECIAL_ORDER_UPLOAD_PHOTO_GALLERY_REQUEST && resultCode == RESULT_OK) {
            try {


                ArrayList<MediaFile> files = data.getParcelableArrayListExtra(com.jaiselrahman.filepicker.activity.FilePickerActivity.MEDIA_FILES);

                specialOrderFileUpload = new File(files.get(0).getPath());
                RequestBody mFile = RequestBody.create(MediaType.parse("application*//*"), specialOrderFileUpload);

                tvPhotoUpload.setText(specialOrderFileUpload.getName());
                specialOrderPhotoUpload = MultipartBody.Part.createFormData("file", specialOrderFileUpload.getName());


            } catch (Exception e) {
                Log.d("TAG_DOC_UPLOAD", e.getMessage());
                System.out.println(getString(R.string.something_went_wrong));
            }

        }
    }
}
