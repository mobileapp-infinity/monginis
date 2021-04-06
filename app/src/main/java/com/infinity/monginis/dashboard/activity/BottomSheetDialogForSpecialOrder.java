package com.infinity.monginis.dashboard.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.adapter.AddsOnAdapter;
import com.infinity.monginis.dashboard.model.AddsOnItemModel;
import com.infinity.monginis.dashboard.model.CartItemModel;
import com.infinity.monginis.dashboard.pojo.GetFlavoursPojo;
import com.infinity.monginis.dashboard.pojo.GetItemWeightPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetOccasionPojo;
import com.infinity.monginis.dashboard.pojo.GetSchedulePojo;
import com.infinity.monginis.dashboard.pojo.Get_Addons_Items_List_Pojo;
import com.infinity.monginis.login.BottomSheetDialogForLoginUser;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.FileUtils;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
    SearchableSpinner spShape;
    SearchableSpinner spWeight;
    SearchableSpinner spFlavour;
    SearchableSpinner spQty;
    Button btnProceed;
    private EditText edtSpecialCakeMessage;
    TextViewRegularFont tvQty;
    private LinearLayout llSchedule;
    //  private ItemDetailsActivity activity;
    private DashboardActivity dashboardActivity;
    private Activity activity;
    TextViewRegularFont tvDeliveryDate, tvPhotoUpload;
    ConnectionDetector connectionDetector;
    public MultipartBody.Part specialOrderPhotoUpload = null;
    private MySharedPreferences mySharedPreferences;
    private TextViewRegularFont tvSpecialItemName, tvSpecialItemPrice;
    private int position;
    private SearchableSpinner spSchedule;
    private LinearLayout llAlmostThere;
    String[] flavours_array, shape_array;
    private ExpandableListView exAddsOnn;
    private EditText edtInstructions;
    private AppCompatImageView imgCloseSpecialOrder;
    private GetItemsForDashboardPojo getItemsForDashboardPojo;

    public BottomSheetDialogForSpecialOrder(ItemDetailsActivity activity) {
        this.activity = activity;
    }

    public BottomSheetDialogForSpecialOrder(DashboardActivity activity, GetItemsForDashboardPojo getItemsForDashboardPojo, int position) {
        this.dashboardActivity = activity;
        this.getItemsForDashboardPojo = getItemsForDashboardPojo;
        this.position = position;
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

        getOccassion();
        setCakeShape(getItemsForDashboardPojo);

        setFlavours();
        //  setWeight();

        return view;
    }


    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(dashboardActivity);
        spFlavour = view.findViewById(R.id.spFlavour);
        connectionDetector = new ConnectionDetector(dashboardActivity);
        spMenu = view.findViewById(R.id.spMenu);
        spShape = view.findViewById(R.id.spShape);
        spWeight = view.findViewById(R.id.spWeight);
        //  spQty = view.findViewById(R.id.spQty);
        tvQty = view.findViewById(R.id.tvQty);
        tvQty.setEnabled(false);
        tvDeliveryDate = view.findViewById(R.id.tvDeliveryDate);
        tvPhotoUpload = view.findViewById(R.id.tvPhotoUpload);
        btnProceed = view.findViewById(R.id.btnProceed);
        tvSpecialItemName = view.findViewById(R.id.tvSpecialItemName);
        tvSpecialItemPrice = view.findViewById(R.id.tvSpecialItemPrice);
        tvSpecialItemName.setText(getItemsForDashboardPojo.getRECORDS().get(position).getItmName());
        tvSpecialItemPrice.setText(getItemsForDashboardPojo.getRECORDS().get(position).getPrice() + "(" + "per kg" + ")");
        sdf_full = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        serverDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        llAlmostThere = view.findViewById(R.id.llAlmostThere);
        llSchedule = view.findViewById(R.id.llSchedule);
        spSchedule = view.findViewById(R.id.spSchedule);
        imgCloseSpecialOrder = view.findViewById(R.id.imgCloseSpecialOrder);
        tvDeliveryDate.setOnClickListener(this);
        imgCloseSpecialOrder.setOnClickListener(this);
        btnProceed.setOnClickListener(this);
        tvPhotoUpload.setOnClickListener(this);
        exAddsOnn = view.findViewById(R.id.exAddsOnn);
        exAddsOnn.setGroupIndicator(null);
        if (mySharedPreferences.getUserMobileNo().equals("")) {
            llAlmostThere.setVisibility(View.VISIBLE);
        } else {
            llAlmostThere.setVisibility(View.GONE);
        }
        edtSpecialCakeMessage = view.findViewById(R.id.edtSpecialCakeMessage);
        edtInstructions = view.findViewById(R.id.edtInstructions);
        exAddsOnn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }


    ArrayList<String> menuList;
    ArrayList<String> flavourList;
    ArrayList<String> weightList;
    ArrayList<String> cakeShapeList;
    ArrayList<String> qtyList;

    private void setMenu() {


        menuList.add("Test 1");
        menuList.add("Test 2");
        menuList.add("Test 3");
        menuList.add("Test 4");


    }

    private void setFlavours() {


        if (!CommonUtil.checkIsEmptyOrNullCommon(getItemsForDashboardPojo.getRECORDS().get(position).getItmFlv())) {


            System.out.println("Flavour Available===");
            flavours_array = new String[1000];

            flavours_array = getItemsForDashboardPojo.getRECORDS().get(position).getItmFlv().split(",");
            flavourList = new ArrayList<>(Arrays.asList(flavours_array));
            flavourList.add(0, "Flavours");
            System.out.println("FlavourList" + flavourList);


            ArrayAdapter<String> cakeFlavourAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, flavourList);
            cakeFlavourAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
            spFlavour.setTitle("Select Flavour");
            //  spSpecialCakeShop.setBackgroundDrawable(R.drawable.ic_baseline_expand_more_24);
            spFlavour.setAdapter(cakeFlavourAdapter);
            GetItemWeight(getItemsForDashboardPojo.getRECORDS().get(position).getId() + "");
        }


    }


    private void getFlavours() {
        if (connectionDetector.isConnectingToInternet()) {


            ApiImplementer.getFlavoursImplementer(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, new Callback<GetFlavoursPojo>() {
                @Override
                public void onResponse(Call<GetFlavoursPojo> call, Response<GetFlavoursPojo> response) {


                    try {


                        if (response.isSuccessful() && response.body() != null
                        ) {
                            GetFlavoursPojo getFlavoursPojo = response.body();

                            if (getFlavoursPojo != null && getFlavoursPojo.getRECORDS().size() > 0) {

                                flavourList = new ArrayList<>();
                                flavourList.add("Flavour");


                              /*  for (int i = 0; i < getFlavoursPojo.getRECORDS().size(); i++) {
                                    flavourList.add(getFlavoursPojo.getRECORDS().get(i) + "");
                                }*/
                                ArrayAdapter<String> flavoursAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, flavourList);
                                flavoursAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
                                spFlavour.setTitle("Select Flavours");
                                spFlavour.setAdapter(flavoursAdapter);

                            } else {
                                flavourList = new ArrayList<>();
                                flavourList.add("Flavour");
                                ArrayAdapter<String> flavoursAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, flavourList);
                                flavoursAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
                                spFlavour.setTitle("Select Flavours");
                                spFlavour.setAdapter(flavoursAdapter);


                            }


                        } else {

                            Toast.makeText(dashboardActivity, "Error in response", Toast.LENGTH_SHORT).show();

                        }

                    } catch (Exception e) {

                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<GetFlavoursPojo> call, Throwable t) {

                    Toast.makeText(dashboardActivity, "Error in response" + t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });
        } else {
            Toast.makeText(activity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    private void getOccassion() {
        if (connectionDetector.isConnectingToInternet()) {


            ApiImplementer.getOccasionImplementer(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, new Callback<GetOccasionPojo>() {
                @Override
                public void onResponse(Call<GetOccasionPojo> call, Response<GetOccasionPojo> response) {


                    // getFlavours();
                    try {
                        GetOccasionPojo getOccasionPojo = response.body();
                        menuList = new ArrayList<>();
                        menuList.add("Menu");

                        if (getOccasionPojo != null && getOccasionPojo.getRECORDS().size() > 0) {

                            for (int i = 0; i < getOccasionPojo.getRECORDS().size(); i++) {
                                menuList.add(getOccasionPojo.getRECORDS().get(i).getComOccasionName());
                            }

                            ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, menuList);
                            menuAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
                            spMenu.setTitle("Select Menu");

                            spMenu.setAdapter(menuAdapter);

                        } else {
                            menuList = new ArrayList<>();
                            menuList.add("Menu");
                            ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, menuList);
                            menuAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
                            spMenu.setTitle("Select Menu");

                            spMenu.setAdapter(menuAdapter);

                        }

                    } catch (Exception e) {

                        Toast.makeText(dashboardActivity, "Error in response" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<GetOccasionPojo> call, Throwable t) {

                    Toast.makeText(dashboardActivity, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });
        } else {
            Toast.makeText(dashboardActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    private void setCakeShape(GetItemsForDashboardPojo getItemsForDashboardPojo) {
        getAddonsItemsList();
        cakeShapeList = new ArrayList<>();
        if (!CommonUtil.checkIsEmptyOrNullCommon(getItemsForDashboardPojo.getRECORDS().get(position).getItmShape())) {


            System.out.println("Shape Available===");
            shape_array = new String[1000];

            shape_array = getItemsForDashboardPojo.getRECORDS().get(position).getItmShape().split(",");
            cakeShapeList = new ArrayList<>(Arrays.asList(shape_array));
            cakeShapeList.add(0, "Shape");
            System.out.println("Shapelistsplit" + cakeShapeList);


            ArrayAdapter<String> cakeShapeAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, cakeShapeList);
            cakeShapeAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
            spShape.setTitle("Select Shape");
            //  spSpecialCakeShop.setBackgroundDrawable(R.drawable.ic_baseline_expand_more_24);
            spShape.setAdapter(cakeShapeAdapter);
        }


    }

    private void setWeight() {

        weightList.add("Weight 1");
        weightList.add("Weight 2");
        weightList.add("Weight 3");
        weightList.add("Weight 4");


    }

    private void setQty() {
        qtyList = new ArrayList<>();
        qtyList.add("Quantity");
        qtyList.add("1");
       /* qtyList.add("2");
        qtyList.add("3");
        qtyList.add("4");
        qtyList.add("5");
        qtyList.add("6");*/

        ArrayAdapter<String> cakeShapeAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, qtyList);
        cakeShapeAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
        spQty.setTitle("Select Qty");
        spQty.setAdapter(cakeShapeAdapter);


    }


    private void GetItemWeight(String item_id) {

        if (connectionDetector.isConnectingToInternet()) {


            ApiImplementer.GetItemWeightImplementer(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, item_id, new Callback<GetItemWeightPojo>() {
                @Override
                public void onResponse(Call<GetItemWeightPojo> call, Response<GetItemWeightPojo> response) {

                    //  DialogUtil.hideProgressDialog();
                    try {
                        if (response.isSuccessful() && response.body() != null) {

                            weightList = new ArrayList<>();
                            weightList.add("Weight");


                            GetItemWeightPojo getItemWeightPojo = response.body();

                            if (getItemWeightPojo != null && getItemWeightPojo.getRECORDS().size() > 0) {


                                for (int i = 0; i < getItemWeightPojo.getRECORDS().size(); i++) {
                                    weightList.add(getItemWeightPojo.getRECORDS().get(i).getQtyValue());

                                }

                                ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, weightList);
                                weightAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
                                spWeight.setTitle("Select Weight");
                                spWeight.setAdapter(weightAdapter);


                            } else {


                                Toast.makeText(getActivity(), getItemWeightPojo.getMESSAGE(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(dashboardActivity, "Error in response", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<GetItemWeightPojo> call, Throwable t) {
                    // DialogUtil.hideProgressDialog();
                    Toast.makeText(getActivity(), "Request Failed::::" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {

            Toast.makeText(dashboardActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();

        }

    }


    private boolean isValidated() {
        boolean flag = true;

        if (spMenu.getSelectedItemPosition() == 0) {

            Toast.makeText(dashboardActivity, "Please Select Menu", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (spShape.getSelectedItemPosition() == 0) {
            Toast.makeText(dashboardActivity, "Please Select Cake Shape", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (spWeight.getSelectedItemPosition() == 0) {
            Toast.makeText(dashboardActivity, "Please Select Weight", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (spFlavour.getSelectedItemPosition() == 0) {
            Toast.makeText(dashboardActivity, "Please Select Flavour", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (tvPhotoUpload.getText().toString().contentEquals("Upload Photo")) {
            Toast.makeText(dashboardActivity, "Please Select Photo", Toast.LENGTH_LONG).show();
            flag = false;

        } else if (spQty.getSelectedItemPosition() == 0) {
            Toast.makeText(dashboardActivity, "Please Select Quantity", Toast.LENGTH_LONG).show();
            flag = false;
        } else if (tvDeliveryDate.getText().toString().contentEquals("Delivery Date")) {
            Toast.makeText(dashboardActivity, "Please Enter Delivery Date", Toast.LENGTH_LONG).show();
            flag = false;
        }

        return flag;


    }

    private HashMap<String, ArrayList<CartItemModel>> selectedCartItems = new HashMap<>();

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.tvDeliveryDate) {
            deliveryDateDialog();
        } else if (view.getId() == R.id.btnProceed) {
            // if (isValidated()) {
            //  cartItemModel = new CartItemModel(spMenu.getSelectedItem() + "", spFlavour.getSelectedItem() + "", spShape.getSelectedItem() + "", "", tvDeliveryDate.getText().toString(), edtSpecialCakeMessage.getText().toString(), edtInstructions.getText().toString(), spWeight.getSelectedItem()+"","","");


            System.out.println("Done=============");
            this.dismiss();
          /*  if (mySharedPreferences.getUserMobileNo().equals("")) {
                BottomSheetDialogForLoginUser bottomSheetDialogForLoginUser = new BottomSheetDialogForLoginUser(dashboardActivity);
                if (!bottomSheetDialogForLoginUser.isAdded()) {
                    bottomSheetDialogForLoginUser.show(dashboardActivity.getSupportFragmentManager(), "test");
                }*/
            // } else {


            ArrayList<CartItemModel> selectedCartItemArrayList = new ArrayList<>();
          //  CartItemModel cartItemModel =
            // selectedCartItemArrayList.add();
            selectedCartItemArrayList.add(new CartItemModel(getItemsForDashboardPojo.getRECORDS().get(position).getItmName(), getItemsForDashboardPojo.getRECORDS().get(position).getPrice() + "", spMenu.getSelectedItem() + "", spFlavour.getSelectedItem() + "", spShape.getSelectedItem() + "", mFile, tvDeliveryDate.getText().toString().trim(), edtSpecialCakeMessage.getText().toString().trim(), edtInstructions.getText().toString(), spWeight.getSelectedItem() + ""));
            selectedCartItems.put(mySharedPreferences.getUserMobileNo(), selectedCartItemArrayList);

            Intent customizeScreenIntent = new Intent(dashboardActivity, CustomizeScreenActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("cartItemModel", selectedCartItemArrayList);
            customizeScreenIntent.putExtras(bundle);
            startActivity(customizeScreenIntent);

            // }


            //  }
        } else if (view.getId() == R.id.tvPhotoUpload) {
           /* Intent intent = new Intent(dashboardActivity, com.jaiselrahman.filepicker.activity.FilePickerActivity.class);


            intent.putExtra(com.jaiselrahman.filepicker.activity.FilePickerActivity.CONFIGS, new Configurations.Builder()
                    .setCheckPermission(true)
                    .setShowImages(true)
                    .setShowAudios(false)
                    .setShowVideos(false)

                    .enableImageCapture(false)
                    .setMaxSelection(1)

                    .setSkipZeroSizeFiles(true)
                    .build());
            startActivityForResult(intent, SPECIAL_ORDER_UPLOAD_PHOTO_GALLERY_REQUEST);*/

            browseDocuments();

        } else if (view.getId() == R.id.imgCloseSpecialOrder) {
            this.dismiss();
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
        DatePickerDialog dialog = new DatePickerDialog(dashboardActivity, R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
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
                    getSchedule(selectedDeliveryDateString, getItemsForDashboardPojo.getRECORDS().get(position).getId() + "");

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

    RequestBody mFile = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SPECIAL_ORDER_UPLOAD_PHOTO_GALLERY_REQUEST && resultCode == RESULT_OK) {
            try {
                Uri uri;

                if (data.getData() == null) {
                    uri = (Uri) data.getExtras().get("data");
                } else {
                    uri = data.getData();

                }

                String fileUrl = FileUtils.getPath(getActivity(), uri);

                File file = new File(fileUrl);
                String file_extension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1);
                mFile = RequestBody.create(MediaType.parse("image/jpeg"), file);

            } catch (Exception e) {

            }


        }
    }

    private ArrayList<String> scheduleList;
    private CartItemModel cartItemModel;

    private void getSchedule(String delv_date, String itm_id) {

        if (connectionDetector.isConnectingToInternet()) {
            ApiImplementer.getScheduleImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, CommonUtil.CUST_ID, delv_date, itm_id, new Callback<GetSchedulePojo>() {
                @Override
                public void onResponse(Call<GetSchedulePojo> call, Response<GetSchedulePojo> response) {

                    try {

                        if (response.isSuccessful() && response.body() != null) {

                            GetSchedulePojo getSchedulePojo = response.body();
                            scheduleList = new ArrayList<>();
                            if (getSchedulePojo != null && getSchedulePojo.getRecords().size() > 0) {

                                // scheduleDialog(getSchedulePojo);

                                for (int i = 0; i < getSchedulePojo.getRecords().size(); i++) {
                                    scheduleList.add(getSchedulePojo.getRecords().get(i).getRsmDepTime());
                                }


                                ArrayAdapter<String> cakeShapeAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, scheduleList);
                                cakeShapeAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
                                spSchedule.setTitle("Select Schedule");
                                spSchedule.setAdapter(cakeShapeAdapter);
                                llSchedule.setVisibility(View.VISIBLE);
                                Toast.makeText(dashboardActivity, getSchedulePojo.getRecords().get(0).getRsmDepTime() + "", Toast.LENGTH_SHORT).show();
                            } else {
                                llSchedule.setVisibility(View.GONE);
                                Toast.makeText(dashboardActivity, getSchedulePojo.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }


                    } catch (Exception e) {
                        Toast.makeText(dashboardActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<GetSchedulePojo> call, Throwable t) {
                    Toast.makeText(dashboardActivity, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(dashboardActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }

    private Dialog dialog;

    private void scheduleDialog(GetSchedulePojo getSchedulePojo) {
        dialog = new Dialog(dashboardActivity);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_shape_for_custom_dialog);//if need to change dialog radius in custom_layout_for_progress_dialog

        dialog.setCancelable(false);
        View customProgressDialog = LayoutInflater.from(dashboardActivity).inflate(R.layout.custom_layout_for_schedule_dialog, null);
        ArrayList<String> scheduleList = new ArrayList<>();

        SearchableSpinner spSchedule = customProgressDialog.findViewById(R.id.spSchedule);
        for (int i = 0; i < getSchedulePojo.getRecords().size(); i++) {
            scheduleList.add(getSchedulePojo.getRecords().get(i).getRsmDepTime());
        }


        ArrayAdapter<String> cakeShapeAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, scheduleList);
        cakeShapeAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
        spSchedule.setTitle("Select Schedule");
        spSchedule.setAdapter(cakeShapeAdapter);


        dialog.setContentView(customProgressDialog);
        dialog.show();

    }

    private void browseDocuments() {


        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);


        intent.setType("*/*");
        String[] mimetypes = {"image/*"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);


        startActivityForResult(Intent.createChooser(intent, "ChooseFile"), IntentConstants.SPECIAL_ORDER_UPLOAD_PHOTO_GALLERY_REQUEST);

    }

    private void savePartialOrderDetail() {
        //ApiImplementer.saveSpecialOrderPartial();

    }


    private ArrayList<Get_Addons_Items_List_Pojo.RECORDSBean.CategoryBean> categoryBeanArrayList;
    private ArrayList<String> categoryNameList;

    private ArrayList<Get_Addons_Items_List_Pojo.RECORDSBean.ItemsBean> itemsBeanArrayList;
    private HashMap<String, ArrayList<AddsOnItemModel>> filteredHashMap;
    private ArrayList<AddsOnItemModel> addsOnItemModelArrayList;

    private void getAddonsItemsList() {
        ApiImplementer.getAddonsItemsList(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, CommonUtil.CUST_ID, new Callback<Get_Addons_Items_List_Pojo>() {
            @Override
            public void onResponse(Call<Get_Addons_Items_List_Pojo> call, Response<Get_Addons_Items_List_Pojo> response) {

                try {
                    if (response.isSuccessful() && response.body() != null) {
                        categoryBeanArrayList = new ArrayList<>();
                        itemsBeanArrayList = new ArrayList<>();
                        categoryNameList = new ArrayList<>();
                        filteredHashMap = new HashMap<>();

                        Get_Addons_Items_List_Pojo get_addons_items_list_pojo = response.body();

                        if (get_addons_items_list_pojo != null && get_addons_items_list_pojo.getRECORDS().getCategory().size() > 0) {

                            for (int i = 0; i < get_addons_items_list_pojo.getRECORDS().getCategory().size(); i++) {
                                categoryBeanArrayList.add(get_addons_items_list_pojo.getRECORDS().getCategory().get(i));
                                categoryNameList.add(get_addons_items_list_pojo.getRECORDS().getCategory().get(i).getCat_name() + "");
                            }

                            for (int i = 0; i < get_addons_items_list_pojo.getRECORDS().getItems().size(); i++) {
                                itemsBeanArrayList.add(get_addons_items_list_pojo.getRECORDS().getItems().get(i));
                            }


                            for (int i = 0; i < get_addons_items_list_pojo.getRECORDS().getCategory().size(); i++) {
                                addsOnItemModelArrayList = new ArrayList<>();

                                for (int j = 0; j < get_addons_items_list_pojo.getRECORDS().getItems().size(); j++) {

                                    if (get_addons_items_list_pojo.getRECORDS().getItems().get(j).getCat_id() == get_addons_items_list_pojo.getRECORDS().getCategory().get(i).getId()) {
                                        addsOnItemModelArrayList.add(new AddsOnItemModel(get_addons_items_list_pojo.getRECORDS().getItems().get(j).getItm_id() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getSh_price() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getPrice() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getUom_id() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getUom_name() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getHsn_code() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getQty() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getMrp() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getCat_id() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getItem_name() + "", get_addons_items_list_pojo.getRECORDS().getItems().get(j).getItm_url() + ""));
                                        filteredHashMap.put(get_addons_items_list_pojo.getRECORDS().getCategory().get(i).getCat_name() + "", addsOnItemModelArrayList);
                                    }


                                }


                            }

                            AddsOnAdapter addsOnAdapter = new AddsOnAdapter(dashboardActivity, filteredHashMap, categoryNameList);
                            exAddsOnn.setAdapter(addsOnAdapter);


                        }


                    }

                } catch (Exception e) {
                    Toast.makeText(dashboardActivity, "Error in response" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Get_Addons_Items_List_Pojo> call, Throwable t) {
                Toast.makeText(dashboardActivity, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
