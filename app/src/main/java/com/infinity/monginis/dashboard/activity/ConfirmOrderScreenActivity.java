package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiClient;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.api.IApiInterface;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.adapter.ConfirmOrderAccessoriesAdapter;
import com.infinity.monginis.dashboard.adapter.ConfirmOrderAddsonAdapter;
import com.infinity.monginis.dashboard.pojo.ConfrimOrderReponsePojo;
import com.infinity.monginis.dashboard.pojo.SaveSpecialOrderconfirmPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.infinity.monginis.dashboard.fragments.SearchFragment.lastFileToUploadPassport;

public class ConfirmOrderScreenActivity extends AppCompatActivity implements View.OnClickListener {


    private MySharedPreferences mySharedPreferences;
    private RecyclerView rvAddsOn,rvAcessories;
    private  AppCompatEditText tvAdvanceAmount,tvMobileNo;
    private TextViewRegularFont tvConfirm;
    private String amountPayable = "";

    private String totalAddsOnPrice;
    private LinearLayout llMain;
    private TextViewMediumFont tvWeight,tvFlavour,tvShape,tvProductName;
    Calendar myCalendar = Calendar.getInstance();
    private TextViewRegularFont tvPickupDate,tvPickUpTime;
    private AppCompatEditText tvAnniversaryDate,tvDob,tvRemainingAmount,tvAmountPayable,tvOrderBy,tvOrderFor;
    private IApiInterface apiInterface;
    private String cgst_per,sgst_pr,schedule_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order_screen);


        initView();
        getPartialOrderDetails("728");
    }




    private void initView(){
        apiInterface = ApiClient.getClient().create(IApiInterface.class);
        mySharedPreferences = new MySharedPreferences(this);
        tvWeight = findViewById(R.id.tvWeight);
        tvFlavour = findViewById(R.id.tvFlavour);
        sdf_full = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        serverDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        tvConfirm = findViewById(R.id.tvConfirm);
        tvShape = findViewById(R.id.tvShape);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate_form()){


                    String orderBy = tvOrderBy.getText().toString().trim();
                    String mobileNo = tvMobileNo.getText().toString().trim();
                    String advanceAmount = tvAdvanceAmount.getText().toString().trim();
                    String remainingAmount = tvRemainingAmount.getText().toString().trim();


                    String orderFor = tvOrderFor.getText().toString();
                    SaveSpecialOrderConfirm("721",orderFor,orderBy,mobileNo,advanceAmount,remainingAmount,amountPayable,cgst_per,sgst_pr,totalAddsOnPrice,schedule_id,SELECTED_ANNIVERSARY_DATE,SELECTED_DATE_OF_BIRTH,SELECTEDPICKUPDATE,PICKUP_TIME);
                }

            }
        });

        tvProductName = findViewById(R.id.tvProductName);
        tvPickupDate = findViewById(R.id.tvPickupDate);
        tvAmountPayable = findViewById(R.id.tvAmountPayable);
        tvPickUpTime = findViewById(R.id.tvPickUpTime);
        tvRemainingAmount = findViewById(R.id.tvRemainingAmount);
        tvOrderBy = findViewById(R.id.tvOrderBy);
        tvMobileNo = findViewById(R.id.tvMobileNo);
        tvOrderFor = findViewById(R.id.tvOrderFor);
        tvPickupDate.setOnClickListener(this);
        tvPickUpTime.setOnClickListener(this);
        rvAddsOn = findViewById(R.id.rvAddsOn);
        rvAcessories = findViewById(R.id.rvAcessories);
        tvAnniversaryDate = findViewById(R.id.tvAnniversaryDate);
        tvAdvanceAmount = findViewById(R.id.tvAdvanceAmount);
        tvAdvanceAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty()){

                    Double remainingAmount = Double.parseDouble(amountPayable) - Integer.parseInt(s.toString());
                    tvRemainingAmount.setText(remainingAmount+"");




                }

            }
        });
        tvDob = findViewById(R.id.tvDob);
        tvAnniversaryDate.setOnClickListener(this);
        tvDob.setOnClickListener(this);
        rvAddsOn.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvAcessories.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        llMain = findViewById(R.id.llMain);
    }

    private void getPartialOrderDetails(String orderId){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(ConfirmOrderScreenActivity.this, "");
            }
        });

        ApiImplementer.getPartialOrderDetails(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, orderId, new Callback<ConfrimOrderReponsePojo>() {
            @Override
            public void onResponse(Call<ConfrimOrderReponsePojo> call, Response<ConfrimOrderReponsePojo> response) {
                DialogUtil.hideProgressDialog();

                try {
                    if (response.isSuccessful() && response.body() != null){
                        llMain.setVisibility(View.VISIBLE);

                        ConfrimOrderReponsePojo confrimOrderReponsePojo = response.body();

                        if (confrimOrderReponsePojo != null ){
                            if (!CommonUtil.checkIsEmptyOrNullCommon(confrimOrderReponsePojo.getRecords().getMain().get(0).getSroidItmWeight())){
                                tvWeight.setText(confrimOrderReponsePojo.getRecords().getMain().get(0).getSroidItmWeight()+"");





                            }

                            if (!CommonUtil.checkIsEmptyOrNullCommon(confrimOrderReponsePojo.getRecords().getMain().get(0).getItmName())){
                                tvProductName.setText(confrimOrderReponsePojo.getRecords().getMain().get(0).getItmName()+"");
                            }
                            if (!CommonUtil.checkIsEmptyOrNullCommon(confrimOrderReponsePojo.getRecords().getMain().get(0).getFlavour())){
                                tvFlavour.setText(confrimOrderReponsePojo.getRecords().getMain().get(0).getFlavour()+"");
                            }
                            if (!CommonUtil.checkIsEmptyOrNullCommon(confrimOrderReponsePojo.getRecords().getMain().get(0).getShape())){
                                tvShape.setText(confrimOrderReponsePojo.getRecords().getMain().get(0).getShape()+"");
                            }

                            if (!CommonUtil.checkIsEmptyOrNullCommon(confrimOrderReponsePojo.getRecords().getMain().get(0).getNetMrpAmt())){

                                amountPayable =  confrimOrderReponsePojo.getRecords().getMain().get(0).getNetMrpAmt()+"";
                                tvAmountPayable.setText(amountPayable +"");
                            }


                            if (confrimOrderReponsePojo.getRecords().getAddons().size() > 0){

                                ArrayList<String> arrayList = new ArrayList();
                                ConfirmOrderAddsonAdapter confirmOrderAddsonAdapter = new ConfirmOrderAddsonAdapter(ConfirmOrderScreenActivity.this,confrimOrderReponsePojo);
                                rvAddsOn.setAdapter(confirmOrderAddsonAdapter);

                            }

                            if (confrimOrderReponsePojo.getRecords().getAccesories().size() > 0){
                                ConfirmOrderAccessoriesAdapter confirmOrderAddsonAdapter = new ConfirmOrderAccessoriesAdapter(ConfirmOrderScreenActivity.this,confrimOrderReponsePojo);
                                rvAcessories.setAdapter(confirmOrderAddsonAdapter);
                            }


                            String productName = confrimOrderReponsePojo.getRecords().getMain().get(0).getItmName();
                            String productWeight = confrimOrderReponsePojo.getRecords().getMain().get(0).getSroidItmWeight() + "";
                            String productQty = confrimOrderReponsePojo.getRecords().getMain().get(0).getSroidItmQty() + "";
                            String cakeName = confrimOrderReponsePojo.getRecords().getMain().get(0).getItmName();
                           // amountPayable = confrimOrderReponsePojo.getRecords().getMain().get(0).getMrp() + "";
                            cgst_per = confrimOrderReponsePojo.getRecords().getMain().get(0).getSroidCgstPer() + "";
                            sgst_pr = confrimOrderReponsePojo.getRecords().getMain().get(0).getSroidSgstPer() + "";
                            schedule_id = confrimOrderReponsePojo.getRecords().getMain().get(0).getSromScheduleId() + "";
                        }

                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ConfrimOrderReponsePojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                llMain.setVisibility(View.GONE);
            }
        });

    }

    final DatePickerDialog.OnDateSetListener pick_up_date = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            update_pickup_date();


        }

    };

    private void update_date_of_birth() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tvDob.setText(sdf.format(myCalendar.getTime()));
        SELECTED_DATE_OF_BIRTH = sdf.format(myCalendar.getTime()) + "";


    }


    final DatePickerDialog.OnDateSetListener date_of_birth = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            update_date_of_birth();


        }

    };

    private String SELECTEDPICKUPDATE,SELECTED_ANNIVERSARY_DATE,SELECTED_DATE_OF_BIRTH;
    private void update_pickup_date() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvPickupDate.setText(sdf.format(myCalendar.getTime()));


        // FROM_DATE = formattedDate;
        SELECTEDPICKUPDATE = sdf.format(myCalendar.getTime());
        System.out.println("FROM_DATE dat ::: " + SELECTEDPICKUPDATE + "");
    }

    final DatePickerDialog.OnDateSetListener anniversary_date = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            update_anniversary_date();


        }

    };

    private void update_anniversary_date() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tvAnniversaryDate.setText(sdf.format(myCalendar.getTime()));
        SELECTED_ANNIVERSARY_DATE = sdf.format(myCalendar.getTime()) + "";


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.tvPickupDate){

           /* DatePickerDialog datePickerDialog_from_expense = new DatePickerDialog(ConfirmOrderScreenActivity.this, R.style.DateDialogTheme, pick_up_date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog_from_expense.getDatePicker();
            datePickerDialog_from_expense.show();*/
            deliveryDateDialog();

        }else if (v.getId() == R.id.tvPickUpTime){
            time_Picker();

        }else if (v.getId() == R.id.tvAnniversaryDate){
            DatePickerDialog datePickerDialog_from_expense = new DatePickerDialog(ConfirmOrderScreenActivity.this, R.style.DateDialogTheme, anniversary_date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog_from_expense.getDatePicker();
            datePickerDialog_from_expense.show();

            datePickerDialog_from_expense.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            datePickerDialog_from_expense.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
        }else if (v.getId() == R.id.tvDob){
            dateOfBirth();
           /* DatePickerDialog datePickerDialog_from_expense = new DatePickerDialog(ConfirmOrderScreenActivity.this, R.style.DateDialogTheme, date_of_birth, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog_from_expense.getDatePicker();
            datePickerDialog_from_expense.show();*/

        }

    }


    /*time picker*/
    int mHour;
    int mMinute;
    String in_time = "", out_time = "";
    String[] h1;
    String PICKUP_TIME = "";
    /*time picker*/

    /**
     * Time picker dailog
     **/

    private void time_Picker() {
        int h = 0, m = 0;
        if (h1 != null && h1.length > 0) {
            h = Integer.parseInt(h1[0]);
            m = Integer.parseInt(h1[1]);

        }

        String a = "am";
        // Get Current Time
        final Calendar c = Calendar.getInstance();

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                mHour = hourOfDay;
                mMinute = minute;

                String time = getTime(hourOfDay, minute);
                PICKUP_TIME = time;
                tvPickUpTime.setText(PICKUP_TIME);
                //  updateLabel(time);

            }
        };
        // }, h, m,false);
        TimePickerDialog timePickerDialog;
        if (out_time.contains("PM")) {
            System.out.println("containsssssssssssssssssssssssssssss");
            timePickerDialog = new TimePickerDialog(this, R.style.DateDialogTheme, myTimeListener, h + 12, m, false);
        } else {
            timePickerDialog = new TimePickerDialog(this, R.style.DateDialogTheme, myTimeListener, h, m, true);
        }

        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.show();
        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }


    private String getTime(int hr, int min) {
        Time time_new = new Time(hr, min, 0);//seconds by default set to zero
        Format formatter;
//        formatter = new SimpleDateFormat("h:mma");
//        formatter = new SimpleDateFormat("H:mm");
        formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(time_new);
    }


    private void SaveSpecialOrderConfirm(String orderId,String order_for,String order_by,String mobile_no,String adv_amt,String remaining_amt,String mrp,String cgst_per,String sgst_per,String total_addons_price, String schedule_id,String anniversery_date, String date_of_birth, String pick_up_date, String pick_up_time ){




        if (anniversery_date == null) {
            anniversery_date = "";
        }

        if (date_of_birth == null) {
            date_of_birth = "";
        }
        if (pick_up_date == null) {
            pick_up_date = "";
        }
        if (pick_up_time == null) {
            pick_up_time = "";
        }

        RequestBody AppVersionCode = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getVersionCode()));
        RequestBody AppAndroidId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getAndroidID()));

//        RequestBody Type_api = RequestBody.create(MediaType.parse("text/plain"), Type);
        RequestBody reg_id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getDeviceID()));
        RequestBody reg_user_id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(CommonUtil.USER_ID));
        RequestBody req_company_id = RequestBody.create(MediaType.parse("text/plain"), CommonUtil.COMP_ID);
        RequestBody req_key = RequestBody.create(MediaType.parse("text/plain"), ApiUrls.TESTING_KEY);
        RequestBody req_cust_id = RequestBody.create(MediaType.parse("text/plain"), "177");
        RequestBody req_order_id = RequestBody.create(MediaType.parse("text/plain"), orderId);
        RequestBody req_order_for = RequestBody.create(MediaType.parse("text/plain"), order_for);
        RequestBody req_order_by = RequestBody.create(MediaType.parse("text/plain"), order_by);
        RequestBody req_mobile_no = RequestBody.create(MediaType.parse("text/plain"), mobile_no);
        RequestBody req_adv_amt = RequestBody.create(MediaType.parse("text/plain"), adv_amt);
        RequestBody req_remaining_amt = RequestBody.create(MediaType.parse("text/plain"), remaining_amt);
        RequestBody req_mrp = RequestBody.create(MediaType.parse("text/plain"), mrp);
        RequestBody req_cgst_per = RequestBody.create(MediaType.parse("text/plain"), cgst_per);
        RequestBody req_sgst_per = RequestBody.create(MediaType.parse("text/plain"), sgst_per);
        RequestBody req_total_addons_price = RequestBody.create(MediaType.parse("text/plain"), "120");
        RequestBody req_schedule_id = RequestBody.create(MediaType.parse("text/plain"), schedule_id);

        RequestBody req_anniversery_date = RequestBody.create(MediaType.parse("text/plain"), anniversery_date);

        RequestBody req_date_of_birth = RequestBody.create(MediaType.parse("text/plain"), date_of_birth);
        RequestBody req_pick_up_date = RequestBody.create(MediaType.parse("text/plain"), pick_up_date);
        RequestBody req_pick_up_time = RequestBody.create(MediaType.parse("text/plain"), pick_up_time);

        Call<SaveSpecialOrderconfirmPojo> call = apiInterface.Save_special_order_confirm(
                AppVersionCode,
                AppAndroidId,
                reg_id,
                reg_user_id,
                req_key,
                req_company_id,
                req_cust_id,
                req_order_id,
                req_order_for,
                req_order_by,
                req_mobile_no,
                req_adv_amt,
                req_remaining_amt,
                req_mrp,
                req_cgst_per,
                req_sgst_per,
                req_total_addons_price,
                req_schedule_id,
                lastFileToUploadPassport,

                req_order_id,
                req_anniversery_date,
                req_date_of_birth,
                req_pick_up_date,
                req_pick_up_time


        );

        call.enqueue(new Callback<SaveSpecialOrderconfirmPojo>() {
            @Override
            public void onResponse(Call<SaveSpecialOrderconfirmPojo> call, Response<SaveSpecialOrderconfirmPojo> response) {
                try {
                    if (response.isSuccessful() && response.body() != null){
                        SaveSpecialOrderconfirmPojo saveSpecialOrderconfirmPojo = response.body();


                        if (saveSpecialOrderconfirmPojo != null && saveSpecialOrderconfirmPojo.getFLAG() == 1){
                            finish();
                        }

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<SaveSpecialOrderconfirmPojo> call, Throwable t) {

            }
        });

    }

    private boolean validate_form() {
        boolean flag = true;
        if (tvPickupDate.getText().toString().contentEquals("")) {

            Toast.makeText(this,"Please Select PickUp Date",Toast.LENGTH_LONG).show();
            flag = false;

        } else
        if (tvOrderBy.getText().toString().contentEquals("")) {
            Toast.makeText(this,"Please Enter  Order By.",Toast.LENGTH_LONG).show();

            flag = false;
        } else if (tvMobileNo.getText().toString().contentEquals("")) {
            Toast.makeText(this,"Please Select MobileNo.",Toast.LENGTH_LONG).show();

            flag = false;
        }


        return flag;


    }
    private Date deliveryDate,dob;
    private SimpleDateFormat sdf_full, serverDateFormat;
    private String selectedDeliveryDateString;
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
        DatePickerDialog dialog = new DatePickerDialog(ConfirmOrderScreenActivity.this, R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
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
                    tvPickupDate.setText(sdf_full.format(deliveryDate));


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

    public void dateOfBirth() {
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
        DatePickerDialog dialog = new DatePickerDialog(ConfirmOrderScreenActivity.this, R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year,
                                  int monthOfYear, int dayOfMonth) {
                try {
                    StringBuilder theDate = new StringBuilder()
                            .append(dayOfMonth).append("-")
                            .append(monthOfYear + 1).append("-")
                            .append(year);

                    try {
                        dob = sdf_full.parse(theDate.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    selectedDeliveryDateString = serverDateFormat.format(dob);
                    tvDob.setText(sdf_full.format(dob));


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
}