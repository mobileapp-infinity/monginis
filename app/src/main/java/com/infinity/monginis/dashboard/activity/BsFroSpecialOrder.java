package com.infinity.monginis.dashboard.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infinity.monginis.R;
import com.infinity.monginis.addson.activity.AddsOnActivity;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.api.IApiInterface;
import com.infinity.monginis.custom_class.TextViewRegularFont;


import com.infinity.monginis.addson.model.AddsOnItemModel;
import com.infinity.monginis.dashboard.model.CartItemModel;
import com.infinity.monginis.dashboard.model.SaveOrder;
import com.infinity.monginis.dashboard.pojo.GetAllShopPojo;
import com.infinity.monginis.dashboard.pojo.GetFlavoursPojo;
import com.infinity.monginis.dashboard.pojo.GetItemWeightPojo;
import com.infinity.monginis.dashboard.pojo.GetItemsForDashboardPojo;
import com.infinity.monginis.dashboard.pojo.GetOccasionPojo;
import com.infinity.monginis.dashboard.pojo.GetSchedulePojo;
import com.infinity.monginis.dashboard.pojo.Get_Addons_Items_List_Pojo;
import com.infinity.monginis.dashboard.pojo.ItemMrpByFlavourAndWeightPojo;
import com.infinity.monginis.dashboard.pojo.TestPojo;
import com.infinity.monginis.itemDetails.ItemDetailsActivity;
import com.infinity.monginis.login.BsLogin;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.FileUtils;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.infinity.monginis.dashboard.activity.DashboardActivity.vpDashboard;
import static com.infinity.monginis.utils.IntentConstants.SPECIAL_ORDER_UPLOAD_PHOTO_GALLERY_REQUEST;

public class BsFroSpecialOrder extends BottomSheetDialogFragment implements View.OnClickListener {


    SearchableSpinner spMenu;
    SearchableSpinner spShape;
    SearchableSpinner spWeight;
    SearchableSpinner spFlavour;
    SearchableSpinner spQty;
    Button btnProceed;
    private EditText edtSpecialCakeMessage;
    private IApiInterface apiInterface;
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
    private String SELECTED_MENU_ID = "";
    private String SELECTED_MENU = "";
    private String SELECTED_SCHEDULE_ID = "";
    ArrayList<String> menuList, menuListId;
    ArrayList<String> flavourList;
    ArrayList<String> weightList;
    ArrayList<String> cakeShapeList;
    ArrayList<String> qtyList;
    ArrayList<TestPojo> testPojoArrayList;
    private Button btnAddsOn;
    private AppCompatImageView ivSelectedImage;
    private boolean isFromSearch = false;

    public BsFroSpecialOrder(ItemDetailsActivity activity) {
        this.activity = activity;
    }

  /*  public BottomSheetDialogForSpecialOrder(ItemDetailsActivity activity) {
        this.activity = activity;
    }*/

    public BsFroSpecialOrder(DashboardActivity activity, GetItemsForDashboardPojo getItemsForDashboardPojo, int position) {
        this.dashboardActivity = activity;
        this.getItemsForDashboardPojo = getItemsForDashboardPojo;
        this.position = position;
    }

    public BsFroSpecialOrder(DashboardActivity d, ArrayList<TestPojo> testPojoArrayList, int position
            , boolean isFromSearch) {
        this.dashboardActivity = d;
        this.testPojoArrayList = testPojoArrayList;
        this.position = position;
        this.isFromSearch = isFromSearch;
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

        spWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getItemMrpbyWeightAndFlavour();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

String mrp = "";
    String cgst_per = "";
    String sgst_per = "";
    private void initView(View view) {
        //saveOrderDatabase = SaveOrderDatabase.getInstance(dashboardActivity);
        ivSelectedImage = view.findViewById(R.id.ivSelectedImage);

        mySharedPreferences = new MySharedPreferences(dashboardActivity);
        spFlavour = view.findViewById(R.id.spFlavour);
        btnAddsOn = view.findViewById(R.id.btnAddsOn);
        btnAddsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidated()){

                    if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserMobileNo())) {

                        dismiss();
                        Intent intent = new Intent(dashboardActivity, AddsOnActivity.class);
                        intent.putExtra("item_id",getItemsForDashboardPojo.getRecords().get(position).getId()+"");
                        intent.putExtra("item_name",getItemsForDashboardPojo.getRecords().get(position).getItmName()+"");
                        intent.putExtra("hsn_code",getItemsForDashboardPojo.getRecords().get(position).getHsnCode());
                        intent.putExtra("uom_id",getItemsForDashboardPojo.getRecords().get(position).getItmUom()+"");
                        intent.putExtra("price",getItemsForDashboardPojo.getRecords().get(position).getPrice()+"");
                        intent.putExtra("weight", spWeight.getSelectedItem() + "");
                        intent.putExtra("cgst_per",cgst_per);
                        intent.putExtra("sgst_per",sgst_per);
                        intent.putExtra("qty","1");
                        intent.putExtra("mrp",mrp);
                        intent.putExtra("flavour",spFlavour.getSelectedItem() + "");
                        intent.putExtra("shape",spShape.getSelectedItem()+"");
                        intent.putExtra("occassionId",SELECTED_MENU_ID);
                        intent.putExtra("occassionName",SELECTED_MENU);
                        intent.putExtra("delv_date",selectedDeliveryDateString);
                        intent.putExtra("addsonPrice","120");
                        intent.putExtra("message",edtInstructions.getText().toString());
                        intent.putExtra("spcialIntro",edtSpecialCakeMessage.getText().toString());
                        intent.putExtra("schedule_id",SELECTED_SCHEDULE_ID);
                        if (selectedFile != null){
                            intent.putExtra("file",selectedFile.toString());
                        }else{
                            intent.putExtra("file","");
                        }

                        startActivity(intent);

                    }else{



                        BsLogin bsLogin = new BsLogin(dashboardActivity, false, false, true);
                        if (!bsLogin.isAdded()) {
                            bsLogin.show(dashboardActivity.getSupportFragmentManager(), "test");
                        }
                      /*  dismiss();
                        BsLogin bsLogin = new BsLogin(getActivity(),true,false,false);
                        if (!bsLogin.isAdded()) {
                            bsLogin.show(getSupportFragmentManager(), "test");
                        }*/

                    }

                }


            }
        });
        connectionDetector = new ConnectionDetector(dashboardActivity);
       // saveOrderDatabase = SaveOrderDatabase.getInstance(dashboardActivity);
        spMenu = view.findViewById(R.id.spMenu);
        spShape = view.findViewById(R.id.spShape);
        spWeight = view.findViewById(R.id.spWeight);
        //  spQty = view.findViewById(R.id.spQty);
        tvQty = view.findViewById(R.id.tvQty);
        tvQty.setEnabled(false);
        tvDeliveryDate = view.findViewById(R.id.tvDeliveryDate);
        tvPhotoUpload = view.findViewById(R.id.tvPhotoUpload);
      //  btnProceed = view.findViewById(R.id.btnProceed);
        tvSpecialItemName = view.findViewById(R.id.tvSpecialItemName);
        tvSpecialItemPrice = view.findViewById(R.id.tvSpecialItemPrice);
        if (isFromSearch) {
            tvSpecialItemName.setText(testPojoArrayList.get(position).getShopName());
            tvSpecialItemPrice.setText(testPojoArrayList.get(position).getShopAddress() + "(" + "per kg" + ")");
        } else {
            tvSpecialItemName.setText(getItemsForDashboardPojo.getRecords().get(position).getItmName());
            tvSpecialItemPrice.setText(getItemsForDashboardPojo.getRecords().get(position).getPrice() + "(" + "per kg" + ")");
        }

        sdf_full = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        serverDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        llAlmostThere = view.findViewById(R.id.llAlmostThere);
        llSchedule = view.findViewById(R.id.llSchedule);
        spSchedule = view.findViewById(R.id.spSchedule);
        imgCloseSpecialOrder = view.findViewById(R.id.imgCloseSpecialOrder);
        tvDeliveryDate.setOnClickListener(this);
        imgCloseSpecialOrder.setOnClickListener(this);
        //btnProceed.setOnClickListener(this);
        tvPhotoUpload.setOnClickListener(this);
        exAddsOnn = view.findViewById(R.id.exAddsOnn);
        exAddsOnn.setGroupIndicator(null);
      /*  if (mySharedPreferences.getUserMobileNo().equals("")) {
            llAlmostThere.setVisibility(View.VISIBLE);
        } else {
            llAlmostThere.setVisibility(View.GONE);
        }*/
        edtSpecialCakeMessage = view.findViewById(R.id.edtSpecialCakeMessage);
        edtInstructions = view.findViewById(R.id.edtInstructions);
        exAddsOnn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        spMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    SELECTED_MENU_ID = menuListId.get(position);
                    SELECTED_MENU = menuList.get(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SELECTED_MENU_ID = "";
            }
        });

        spSchedule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    SELECTED_SCHEDULE_ID = scheduleListId.get(position);
                    System.out.println(SELECTED_SCHEDULE_ID);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SELECTED_SCHEDULE_ID = "";
            }
        });

    }


    private void setFlavours() {
        getFlavours(getItemsForDashboardPojo.getRecords().get(position).getId()+"");


       /* if (!CommonUtil.checkIsEmptyOrNullCommon(getItemsForDashboardPojo.getRecords().get(position).getItmFlv())) {


            System.out.println("Flavour Available===");
            flavours_array = new String[1000];

            flavours_array = getItemsForDashboardPojo.getRecords().get(position).getItmFlv().split(",");
            flavourList = new ArrayList<>(Arrays.asList(flavours_array));
            flavourList.add(0, "Flavours");
            System.out.println("FlavourList" + flavourList);


            ArrayAdapter<String> cakeFlavourAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, flavourList);
            cakeFlavourAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
            spFlavour.setTitle("Select Flavour");
            //  spSpecialCakeShop.setBackgroundDrawable(R.drawable.ic_baseline_expand_more_24);
            spFlavour.setAdapter(cakeFlavourAdapter);

        }*/


    }


    private void getFlavours(String itemId) {
        if (connectionDetector.isConnectingToInternet()) {


            ApiImplementer.getFlavoursImplementer(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID,itemId, new Callback<GetFlavoursPojo>() {
                @Override
                public void onResponse(Call<GetFlavoursPojo> call, Response<GetFlavoursPojo> response) {


                    try {


                        if (response.isSuccessful() && response.body() != null
                        ) {
                            GetFlavoursPojo getFlavoursPojo = response.body();

                            if (getFlavoursPojo != null && getFlavoursPojo.getRecords().size() > 0) {

                                flavourList = new ArrayList<>();
                                flavourList.add("Flavour");


                                for (int i = 0; i < getFlavoursPojo.getRecords().size(); i++) {
                                    flavourList.add(getFlavoursPojo.getRecords().get(i).getIadValue() + "");
                                }
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
                            GetItemWeight(getItemsForDashboardPojo.getRecords().get(position).getId() + "");

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
                        menuListId = new ArrayList<>();
                        menuList.add("Menu");
                        menuListId.add("0");

                        if (getOccasionPojo != null && getOccasionPojo.getRECORDS().size() > 0) {

                            for (int i = 0; i < getOccasionPojo.getRECORDS().size(); i++) {
                                menuList.add(getOccasionPojo.getRECORDS().get(i).getComOccasionName());
                                menuListId.add(getOccasionPojo.getRECORDS().get(i).getId() + "");
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
        if (!CommonUtil.checkIsEmptyOrNullCommon(getItemsForDashboardPojo.getRecords().get(position).getItmShape())) {


            System.out.println("Shape Available===");
            shape_array = new String[1000];

            shape_array = getItemsForDashboardPojo.getRecords().get(position).getItmShape().split(",");
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
        } /*else if (tvPhotoUpload.getText().toString().contentEquals("Upload Photo")) {
            Toast.makeText(dashboardActivity, "Please Select Photo", Toast.LENGTH_LONG).show();
            flag = false;

        }*/ /*else if (spQty.getSelectedItemPosition() == 0) {
            Toast.makeText(dashboardActivity, "Please Select Quantity", Toast.LENGTH_LONG).show();
            flag = false;
        }*/ else if (tvDeliveryDate.getText().toString().contentEquals("Delivery Date")) {
            Toast.makeText(dashboardActivity, "Please Enter Delivery Date", Toast.LENGTH_LONG).show();
            flag = false;
        }

        return flag;


    }

    private HashMap<String, ArrayList<CartItemModel>> selectedCartItems = new HashMap<>();
    private List<Get_Addons_Items_List_Pojo.Item> specialCategoryAddOnSelectedItemArrayList = new ArrayList<>();
    private ArrayList<CartItemModel> cartItemModelArrayList = new ArrayList();
    HashMap<String, ArrayList<CartItemModel>> cartItemHashMap = new HashMap<>();


    //****//

  //  private SaveOrderDatabase saveOrderDatabase;

    private SaveOrder saveOrder;
    //****//

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.tvDeliveryDate) {
            deliveryDateDialog();
        } else if (view.getId() == R.id.btnProceed) {
            // if (isValidated()) {
            this.dismiss();

            saveOrder = new SaveOrder(getItemsForDashboardPojo.getRecords().get(position).getItmName() + "",getItemsForDashboardPojo.getRecords().get(position).getHsnCode() + "",getItemsForDashboardPojo.getRecords().get(position).getItmUom() + "","test","test","test","test","test","test","test","test","test");

            // create worker thread to insert data into database
            new SaveOrderTask(dashboardActivity,saveOrder).execute();

            /***Addons array*/
            JSONArray special_item_details_adds_on_array = new JSONArray();
            double totalAddsOn = 0.0;
            for (String category : filteredHashMap.keySet()) {
                List<Get_Addons_Items_List_Pojo.Item> specialCategoryModelArrayList = filteredHashMap.get(category);


                for (int i = 0; i < specialCategoryModelArrayList.size(); i++) {


                    if (specialCategoryModelArrayList.get(i).getQty() != 0.0) {

                        specialCategoryAddOnSelectedItemArrayList.add(specialCategoryModelArrayList.get(i));


                    }


                }


            }
            JSONObject special_item_adds_on_object = new JSONObject();
            for (int i = 0; i < specialCategoryAddOnSelectedItemArrayList.size(); i++) {
                totalAddsOn += specialCategoryAddOnSelectedItemArrayList.get(i).getTotalAmt();
                special_item_adds_on_object = new JSONObject();

                try {

                    special_item_adds_on_object.put("itm_id", specialCategoryAddOnSelectedItemArrayList.get(i).getItmId());
                    special_item_adds_on_object.put("itm_name", specialCategoryAddOnSelectedItemArrayList.get(i).getItemName());
                    special_item_adds_on_object.put("itm_hsn_code", specialCategoryAddOnSelectedItemArrayList.get(i).getHsnCode());
                    special_item_adds_on_object.put("itm_uom_id", specialCategoryAddOnSelectedItemArrayList.get(i).getUomId());
                    special_item_adds_on_object.put("itm_is_addon", 1);
                    special_item_adds_on_object.put("act_price", specialCategoryAddOnSelectedItemArrayList.get(i).getPrice());
                    special_item_adds_on_object.put("itm_weight", specialCategoryAddOnSelectedItemArrayList.get(i).getQty());
                    special_item_adds_on_object.put("cgst_per", 0);
                    special_item_adds_on_object.put("sgst_per", 0);
                    special_item_adds_on_object.put("cgst_tot", 0);
                    special_item_adds_on_object.put("sgst_tot", 0);
                    special_item_adds_on_object.put("itm_total_amt", specialCategoryAddOnSelectedItemArrayList.get(i).getTotalAmt());
                    special_item_adds_on_object.put("itm_net_amt", specialCategoryAddOnSelectedItemArrayList.get(i).getTotal_net_amt());
                    special_item_adds_on_object.put("mrp", specialCategoryAddOnSelectedItemArrayList.get(i).getMrp());


                } catch (JSONException e) {
                    e.printStackTrace();

                    System.out.println("error in json creation!!!!!");
                }


                special_item_details_adds_on_array.put(special_item_adds_on_object);

            }

            /***Addons array*/
            /**Json Item Array**/
            JSONArray special_item_array = new JSONArray();
            JSONObject selectedItemJson = new JSONObject();


            try {

                selectedItemJson.put("item_id", getItemsForDashboardPojo.getRecords().get(position).getId());
                selectedItemJson.put("item_name", getItemsForDashboardPojo.getRecords().get(position).getItmName());
                selectedItemJson.put("hsn_code", Integer.parseInt(getItemsForDashboardPojo.getRecords().get(position).getHsnCode()));
                selectedItemJson.put("uom_id", getItemsForDashboardPojo.getRecords().get(position).getItmUom());
                selectedItemJson.put("price", getItemsForDashboardPojo.getRecords().get(position).getPrice());
                selectedItemJson.put("weight", Double.parseDouble(spWeight.getSelectedItem() + ""));
                selectedItemJson.put("cgst_per", cgst_per);
                selectedItemJson.put("sgst_per", sgst_per);
                selectedItemJson.put("qty", Integer.parseInt("1"));
                selectedItemJson.put("mrp", mrp);
                selectedItemJson.put("flavour", spFlavour.getSelectedItem() + "");
                selectedItemJson.put("shape", spShape.getSelectedItem() + "");


            } catch (JSONException e) {
                e.printStackTrace();

                System.out.println("error in json creation!!!!!");
            }
            special_item_array.put(selectedItemJson);

            /**Json Item Array**/


            if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserMobileNo())) {

                if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getUserWiseCartItems())) {
                    Gson gson = new Gson();
                    String stringFromsharedPref = mySharedPreferences.getUserWiseCartItems();
                    java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<CartItemModel>>>() {
                    }.getType();
                    HashMap<String, ArrayList<CartItemModel>> testHashMap2 = gson.fromJson(stringFromsharedPref, type);
                    cartItemModelArrayList = testHashMap2.get(mySharedPreferences.getUserMobileNo());
                    cartItemModelArrayList.add(new CartItemModel(getItemsForDashboardPojo.getRecords().get(position).getId() + "", getItemsForDashboardPojo.getRecords().get(position).getItmName(), "25.0", spMenu.getSelectedItem() + ""
                            , spWeight.getSelectedItem() + "", spFlavour.getSelectedItem() + "", spShape.getSelectedItem() + "", "1", tvDeliveryDate.getText().toString(), edtSpecialCakeMessage.getText().toString(), edtInstructions.getText().toString(), special_item_details_adds_on_array.toString(), special_item_array.toString(), SELECTED_MENU_ID, SELECTED_SCHEDULE_ID, totalAddsOn + "", null));
                    cartItemHashMap.put(mySharedPreferences.getUserMobileNo(), cartItemModelArrayList);
                    mySharedPreferences.setUserWiseCartItems(cartItemHashMap);
                    vpDashboard.setCurrentItem(2);

                } else {
                    cartItemModelArrayList.add(new CartItemModel(getItemsForDashboardPojo.getRecords().get(position).getId() + "", getItemsForDashboardPojo.getRecords().get(position).getItmName(), "25.0", spMenu.getSelectedItem() + ""
                            , spWeight.getSelectedItem() + "", spFlavour.getSelectedItem() + "", spShape.getSelectedItem() + "", "1", tvDeliveryDate.getText().toString(), edtSpecialCakeMessage.getText().toString(), edtInstructions.getText().toString(), special_item_details_adds_on_array.toString(), special_item_array.toString(), SELECTED_MENU_ID, SELECTED_SCHEDULE_ID, totalAddsOn + "", null));
                    cartItemHashMap.put(mySharedPreferences.getUserMobileNo(), cartItemModelArrayList);
                    mySharedPreferences.setUserWiseCartItems(cartItemHashMap);
                    System.out.println(cartItemHashMap);
                    vpDashboard.setCurrentItem(2);
                }


            } else {
                this.dismiss();

                BsLogin bsLogin = new BsLogin(dashboardActivity, false, false, true);
                if (!bsLogin.isAdded()) {
                    bsLogin.show(dashboardActivity.getSupportFragmentManager(), "test");
                }

                // }
            }

            //  }





         /*   int total = 0;
            for (String category : filteredHashMap.keySet()) {

                ArrayList<AddsOnItemModel> specialCategoryModelArrayList = filteredHashMap.get(category);


                for (int i = 0; i < specialCategoryModelArrayList.size(); i++) {
                    total += Integer.parseInt(specialCategoryModelArrayList.get(i).getTotal_price());

                }
            }*/

            /*for (String category : filteredHashMap.keySet()) {
                ArrayList<AddsOnItemModel> specialCategoryModelArrayList = filteredHashMap.get(category);


                for (int i = 0; i < specialCategoryModelArrayList.size(); i++) {
                    if (!specialCategoryModelArrayList.get(i).getQty().equals("0")) {

                        specialCategoryAddOnSelectedItemArrayList.add(specialCategoryModelArrayList.get(i));


                    }


                }
            }

            //specialItemArray = new JSONArray();
            // specialItemDetailsAddsOnArray = new JSONArray();

            JSONObject jsonObject = new JSONObject();*/


        /*    try {

                jsonObject.put("item_id", Integer.parseInt(getItemsForDashboardPojo.getRecords().get(position).getId() + ""));
                jsonObject.put("item_name", getItemsForDashboardPojo.getRecords().get(position).getItmName() + "");
                jsonObject.put("hsn_code", Integer.parseInt(getItemsForDashboardPojo.getRecords().get(position).getHsnCode() + ""));
                jsonObject.put("uom_id", Integer.parseInt(getItemsForDashboardPojo.getRecords().get(position).getItmUom() + ""));
                jsonObject.put("price", getItemsForDashboardPojo.getRecords().get(position).getPrice() + "");
                jsonObject.put("weight", Double.parseDouble(spWeight.getSelectedItem() + ""));
                //    jsonObject.put("cgst_per", cgst_per);
                //  jsonObject.put("sgst_per", sgst_per);
                // jsonObject.put("qty", Integer.parseInt(selected_qty));
                //  jsonObject.put("mrp", Double.parseDouble(mrp));
                //  jsonObject.put("flavour", selected_flavour);
                //  jsonObject.put("shape", selected_shape);


            } catch (JSONException e) {
                e.printStackTrace();

                System.out.println("error in json creation!!!!!");
            }*/


            // } else {


          /*  ArrayList<CartItemModel> selectedCartItemArrayList = new ArrayList<>();
            //  CartItemModel cartItemModel =
            // selectedCartItemArrayList.add();
            selectedCartItemArrayList.add(new CartItemModel(getItemsForDashboardPojo.getRecords().get(position).getItmName(), getItemsForDashboardPojo.getRecords().get(position).getPrice() + "", spMenu.getSelectedItem() + "", spFlavour.getSelectedItem() + "", spShape.getSelectedItem() + "", mFile, tvDeliveryDate.getText().toString().trim(), edtSpecialCakeMessage.getText().toString().trim(), edtInstructions.getText().toString(), spWeight.getSelectedItem() + "", SELECTED_MENU_ID, "1", SELECTED_SCHEDULE_ID));
            selectedCartItems.put(mySharedPreferences.getUserMobileNo(), selectedCartItemArrayList);

            Intent customizeScreenIntent = new Intent(dashboardActivity, CustomizeScreenActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("cartItemModel", selectedCartItemArrayList);
            customizeScreenIntent.putExtras(bundle);
            startActivity(customizeScreenIntent);
*/
            // }


            //  }
        } else if (view.getId() == R.id.tvPhotoUpload) {

            browseDocuments();

        } else if (view.getId() == R.id.imgCloseSpecialOrder) {
            this.dismiss();
        }

    }

    private Date deliveryDate;
    private SimpleDateFormat sdf_full, serverDateFormat;
    private String selectedDeliveryDateString;

    File selectedFile;
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
                    getSchedule(selectedDeliveryDateString, getItemsForDashboardPojo.getRecords().get(position).getId() + "");

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

                selectedFile = new File(fileUrl);
                String file_extension = selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().lastIndexOf(".") + 1);
                mFile = RequestBody.create(MediaType.parse("image/jpeg"), selectedFile);
                tvPhotoUpload.setText(selectedFile.getName()+"");
                Bitmap myBitmap = BitmapFactory.decodeFile(selectedFile.getAbsolutePath());
                ivSelectedImage.setVisibility(View.VISIBLE);
                ivSelectedImage.setImageBitmap(myBitmap);

            } catch (Exception e) {

            }


        }
    }

    private ArrayList<String> scheduleList;
    private ArrayList<String> scheduleListId;
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
                            scheduleList.add("Select Schedule");
                            scheduleListId = new ArrayList<>();
                            scheduleListId.add("0");
                            if (getSchedulePojo != null && getSchedulePojo.getRecords().size() > 0) {

                                // scheduleDialog(getSchedulePojo);

                                for (int i = 0; i < getSchedulePojo.getRecords().size(); i++) {
                                    scheduleList.add(getSchedulePojo.getRecords().get(i).getRsmDepTime());
                                    scheduleListId.add(getSchedulePojo.getRecords().get(i).getScheduleId() + "");
                                }


                                ArrayAdapter<String> cakeShapeAdapter = new ArrayAdapter<String>(dashboardActivity, R.layout.spinner_common_layout, scheduleList);
                                cakeShapeAdapter.setDropDownViewResource(R.layout.spinner_common_layout);
                                spSchedule.setTitle("Select Schedule");
                                spSchedule.setAdapter(cakeShapeAdapter);
                                llSchedule.setVisibility(View.VISIBLE);

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
        ;

    }


    // private ArrayList<Get_Addons_Items_List_Pojo.RECORDSBean.CategoryBean> categoryBeanArrayList;
    private ArrayList<String> categoryNameList;

    //private ArrayList<Get_Addons_Items_List_Pojo.RECORDSBean.ItemsBean> itemsBeanArrayList;
    private HashMap<String, List<Get_Addons_Items_List_Pojo.Item>> filteredHashMap;

    private ArrayList<AddsOnItemModel> addsOnItemModelArrayList;

    private void getAddonsItemsList() {
        ApiImplementer.getAddonsItemsListImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, CommonUtil.CUST_ID, new Callback<Get_Addons_Items_List_Pojo>() {
            @Override
            public void onResponse(Call<Get_Addons_Items_List_Pojo> call, Response<Get_Addons_Items_List_Pojo> response) {

                try {
                    if (response.isSuccessful() && response.body() != null) {
                        Get_Addons_Items_List_Pojo get_addons_items_list_pojo = response.body();
                        filteredHashMap = new HashMap<>();
                        categoryNameList = new ArrayList<>();
                        if (get_addons_items_list_pojo != null && get_addons_items_list_pojo.getRecords().size() > 0) {

                            for (int i = 0; i < get_addons_items_list_pojo.getRecords().size(); i++) {


                                categoryNameList.add(get_addons_items_list_pojo.getRecords().get(i).getCatName());
                                filteredHashMap.put(get_addons_items_list_pojo.getRecords().get(i).getCatName(), get_addons_items_list_pojo.getRecords().get(i).getItems());
                            }


                        } else {
                            Toast.makeText(dashboardActivity, get_addons_items_list_pojo.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        //AddsOnAdapter addsOnAdapter = new AddsOnAdapter(dashboardActivity, filteredHashMap, categoryNameList);
                        //exAddsOnn.setAdapter(addsOnAdapter);


                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<Get_Addons_Items_List_Pojo> call, Throwable t) {
                Toast.makeText(dashboardActivity, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getAllShop() {

        ApiImplementer.getAllShopImplementer(String.valueOf(mySharedPreferences.getVersionCode()), mySharedPreferences.getAndroidID(), String.valueOf(mySharedPreferences.getDeviceID()), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, new Callback<GetAllShopPojo>() {
            @Override
            public void onResponse(Call<GetAllShopPojo> call, Response<GetAllShopPojo> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        GetAllShopPojo getAllShopPojo = response.body();

                        if (getAllShopPojo != null && getAllShopPojo.getRecords().size() > 0) {


                        }

                    }

                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<GetAllShopPojo> call, Throwable t) {

            }
        });

    }


    public  class SaveOrderTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<Context> activityReference;
        private SaveOrder saveOrder;

        // only retain a weak reference to the activity
        SaveOrderTask(Context context, SaveOrder saveOrder) {
            activityReference = new WeakReference<>(context);
            this.saveOrder = saveOrder;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {
          //  saveOrderDatabase.getNoteDao().insert(saveOrder);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {

            //Toast.makeText(activityReference,"Added Saved",Toast.LENGTH_SHORT).show();
           /* if (bool){
                activityReference.get().setResult(note,1);
            }*/
        }

    }


    private void getItemMrpbyWeightAndFlavour(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(getActivity(), "");
            }
        });


        ApiImplementer.getItemMrpByWeightAndFlavourorNewImplementer(String.valueOf(mySharedPreferences.getVersionCode()), String.valueOf(mySharedPreferences.getAndroidID()), String.valueOf(mySharedPreferences.getDeviceID()), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, String.valueOf(getItemsForDashboardPojo.getRecords().get(position).getId()), "Gujarat", "AHMEDABAD", getItemsForDashboardPojo.getRecords().get(position).getHsnCode(), "Khari", new Callback<ItemMrpByFlavourAndWeightPojo>() {
            @Override
            public void onResponse(Call<ItemMrpByFlavourAndWeightPojo> call, Response<ItemMrpByFlavourAndWeightPojo> response) {
                try{
                    if (response.isSuccessful()){

                        http://192.168.30.70/SFDelicious/Get_item_mrp_by_weight_and_flavour_for_new?app_version=1&android_id=dvdvdsvsv&device_id=0&user_id=0&key=NMEQpClmUy&comp_id=5&item_id=54&state_name=Gujarat&city_name=AHMEDABAD&hsn_code=123456&Flavour=khari
                        DialogUtil.hideProgressDialog();
                        ItemMrpByFlavourAndWeightPojo itemMrpByFlavourAndWeightPojo = response.body();
                        if (itemMrpByFlavourAndWeightPojo != null && itemMrpByFlavourAndWeightPojo.getRecords().size() > 0 ){
                            double weight = Double.parseDouble(spWeight.getSelectedItem()+"");
                            double mrpp = weight * itemMrpByFlavourAndWeightPojo.getRecords().get(0).getPrice();
                            mrp = mrpp+"";
                            cgst_per = itemMrpByFlavourAndWeightPojo.getRecords().get(0).getCgstPer()+"";
                            sgst_per = itemMrpByFlavourAndWeightPojo.getRecords().get(0).getSgstPer()+"";



                        }else{

                        }


                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<ItemMrpByFlavourAndWeightPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
            }
        });
        //Get_item_mrp_by_weight_and_flavour
    }




}
