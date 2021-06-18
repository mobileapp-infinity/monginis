package com.infinity.monginis.dashboard.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiClient;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.api.IApiInterface;
import com.infinity.monginis.dashboard.activity.AddsOnActivity;
import com.infinity.monginis.dashboard.adapter.SearchCategoryAdapter;
import com.infinity.monginis.dashboard.pojo.SavePartialOrderPojo;
import com.infinity.monginis.dashboard.pojo.SearchCategoryPojo;
import com.infinity.monginis.dashboard.pojo.TestPojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.ConnectionDetector;
import com.infinity.monginis.utils.DialogUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import org.json.JSONArray;

import java.util.ArrayList;

import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements View.OnClickListener,AddsOnActivity.IOnSubmit {

    private Activity activity;
    private IApiInterface apiInterface;
    private static SearchFragment searchFragment = null;
    private AppCompatEditText edtSearchCategory;
    private AppCompatImageView imgClearSearch;
    private ThemedButton tBtnShops;
    private ThemedButton tBtnItems;
    private ThemedToggleButtonGroup tBtnGroup;
    private RecyclerView rvSearchCategory;
    private ArrayList<TestPojo> ItemPojoArrayList = new ArrayList<>();
    private ArrayList<TestPojo> customerPojoArrayList = new ArrayList<>();
    private SearchCategoryAdapter searchCategoryAdapter;

    private LinearLayout llCategory;
    private LinearLayout llSearchCategoryProgressbar;
    private LinearLayout llNoDataFoundSearchCategory;
    private LinearLayout llMain;
    private ConnectionDetector connectionDetector;
    private static boolean isItemSelected;
    private MySharedPreferences mySharedPreferences;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    public static SearchFragment getSearchFragment() {
        if (searchFragment == null) {
            searchFragment = new SearchFragment();
        }
        return searchFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.fragment_serach, container, false);
        initView(view);
        try {
            getSearchCategoryApiCall();
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }


        return view;
    }

    private void initView(View view) {
        apiInterface = ApiClient.getClient().create(IApiInterface.class);
        mySharedPreferences = new MySharedPreferences(getActivity());
        connectionDetector = new ConnectionDetector(activity);
        edtSearchCategory = view.findViewById(R.id.edtSearchCategory);
        imgClearSearch = view.findViewById(R.id.imgClearSearch);
        imgClearSearch.setOnClickListener(this);
        tBtnShops = view.findViewById(R.id.tBtnShops);


        tBtnItems = view.findViewById(R.id.tBtnItems);
        rvSearchCategory = view.findViewById(R.id.rvSearchCategory);
        llCategory = view.findViewById(R.id.llCategory);
        llSearchCategoryProgressbar = view.findViewById(R.id.llSearchCategoryProgressbar);
        llNoDataFoundSearchCategory = view.findViewById(R.id.llNoDataFoundSearchCategory);
        tBtnGroup = view.findViewById(R.id.tBtnGroup);
        llMain = view.findViewById(R.id.llMain);

        ArrayList<TestPojo> testPojoArrayList = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            TestPojo testPojo = new TestPojo();
            if (i == 0) {
                testPojo.setShopName("Monginis");
            } else if (i == 1) {
                testPojo.setShopName("Monginis");
            } else if (i == 2) {
                testPojo.setShopName("Deliciaus");
            } else {
                testPojo.setShopName("Test");
            }
            testPojoArrayList.add(testPojo);
        }
      /*  searchCategoryAdapter = new SearchCategoryAdapter(activity, testPojoArrayList);
        rvSearchCategory.setAdapter(searchCategoryAdapter);*/
        this.ItemPojoArrayList = testPojoArrayList;

        tBtnShops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();

                isItemSelected = false;
                llCategory.setVisibility(View.VISIBLE);
                llNoDataFoundSearchCategory.setVisibility(View.GONE);
                searchCategoryAdapter = new SearchCategoryAdapter(activity, customerPojoArrayList, new SearchCategoryAdapter.IOnShopClicked() {
                    @Override
                    public void onShopClicked() {

                        Toast.makeText(getActivity(),"Shop Clicked",Toast.LENGTH_LONG).show();
                       // saveSpecialOrderPartial();

                    }
                });
                rvSearchCategory.setAdapter(searchCategoryAdapter);
                searchCategoryAdapter.notifyDataSetChanged();
                edtSearchCategory.setText("");
            }
        });

        tBtnItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                isItemSelected = true;
                llCategory.setVisibility(View.VISIBLE);
                llNoDataFoundSearchCategory.setVisibility(View.GONE);
                searchCategoryAdapter = new SearchCategoryAdapter(activity, ItemPojoArrayList, new SearchCategoryAdapter.IOnShopClicked() {
                    @Override
                    public void onShopClicked() {

                    }
                });
                rvSearchCategory.setAdapter(searchCategoryAdapter);
                searchCategoryAdapter.notifyDataSetChanged();
                edtSearchCategory.setText("");

            }
        });

        edtSearchCategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    imgClearSearch.setVisibility(View.VISIBLE);
                } else {
                    imgClearSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        tBtnGroup.selectButton(R.id.tBtnShops);

    }

    void filter(String text) {
        if (isItemSelected) {

            ArrayList<TestPojo> updatedList = new ArrayList();
            for (TestPojo testPojo : ItemPojoArrayList) {

                if (testPojo.getShopName().toLowerCase().contains(text.toLowerCase())) {
                    updatedList.add(testPojo);
                }
            }
            try {
                searchCategoryAdapter.updateList(updatedList);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Please Try again Later", Toast.LENGTH_SHORT).show();
            }


        } else {

            ArrayList<TestPojo> updatedList = new ArrayList();
            for (TestPojo testPojo : customerPojoArrayList) {
                //or use .equal(text) with you want equal match
                //use .toLowerCase() for better matches
                if (testPojo.getShopName().toLowerCase().contains(text.toLowerCase())) {
                    updatedList.add(testPojo);
                }
            }

            try {
                searchCategoryAdapter.updateList(updatedList);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Please Try again Later", Toast.LENGTH_SHORT).show();
            }


        }


    }


    @Override
    public void onResume() {
        super.onResume();
      if (mySharedPreferences.getSelectedItemId() != null && !mySharedPreferences.getSelectedItemId().equals("")){

          Toast.makeText(getActivity(),mySharedPreferences.getSelectedItemId(),Toast.LENGTH_LONG).show();
      }
    }

    private void getSearchCategoryApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llCategory.setVisibility(View.GONE);
            llSearchCategoryProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundSearchCategory.setVisibility(View.GONE);
            ApiImplementer.getSearchCategoryApiImplementer(mySharedPreferences.getVersionCode(), mySharedPreferences.getAndroidID(), mySharedPreferences.getDeviceID(), CommonUtil.USER_ID, ApiUrls.TESTING_KEY, CommonUtil.COMP_ID, new Callback<SearchCategoryPojo>() {
                @Override
                public void onResponse(Call<SearchCategoryPojo> call, Response<SearchCategoryPojo> response) {
                    if (response.isSuccessful() && response.body() != null
                    ) {
                        llMain.setVisibility(View.VISIBLE);
                        llSearchCategoryProgressbar.setVisibility(View.GONE);
                        llCategory.setVisibility(View.VISIBLE);

                        SearchCategoryPojo searchCategoryPojo = response.body();

                        ItemPojoArrayList = new ArrayList<>();
                        customerPojoArrayList = new ArrayList<>();

                        if (searchCategoryPojo != null && searchCategoryPojo.getRECORDS().getCustJson().size() > 0) {


                            for (int i = 0; i < searchCategoryPojo.getRECORDS().getCustJson().size(); i++) {

                                customerPojoArrayList.add(new TestPojo(searchCategoryPojo.getRECORDS().getCustJson().get(i).getCusName(), searchCategoryPojo.getRECORDS().getCustJson().get(i).getAddress(),0));

                            }


                            searchCategoryAdapter = new SearchCategoryAdapter(activity, customerPojoArrayList, new SearchCategoryAdapter.IOnShopClicked() {
                                @Override
                                public void onShopClicked() {
                                    Toast.makeText(getActivity(),"Shop Clicked",Toast.LENGTH_LONG).show();
                                    //saveSpecialOrderPartial();

                                }
                            });
                            rvSearchCategory.setAdapter(searchCategoryAdapter);

                        } else {

                            llCategory.setVisibility(View.GONE);
                            llNoDataFoundSearchCategory.setVisibility(View.VISIBLE);
                        }
                        if (searchCategoryPojo != null && searchCategoryPojo.getRECORDS().getItemJson().size() > 0) {
                            for (int j = 0; j < searchCategoryPojo.getRECORDS().getItemJson().size(); j++) {
                                ItemPojoArrayList.add(new TestPojo(searchCategoryPojo.getRECORDS().getItemJson().get(j).getItmName(), searchCategoryPojo.getRECORDS().getItemJson().get(j).getMrp(),searchCategoryPojo.getRECORDS().getItemJson().get(j).getIs_special_flag()));
                            }

                        }

                        //for (int i=0;i<response.body().getRECORDS().s)
                    } else {
                        llMain.setVisibility(View.GONE);
                        llCategory.setVisibility(View.GONE);
                        llSearchCategoryProgressbar.setVisibility(View.GONE);
                        llNoDataFoundSearchCategory.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<SearchCategoryPojo> call, Throwable t) {
                    llMain.setVisibility(View.GONE);
                    llCategory.setVisibility(View.GONE);
                    llSearchCategoryProgressbar.setVisibility(View.GONE);
                    llNoDataFoundSearchCategory.setVisibility(View.VISIBLE);
                }
            });
        } else {
            Toast.makeText(activity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgClearSearch) {
            edtSearchCategory.setText("");
            imgClearSearch.setVisibility(View.GONE);
        }
    }

    private void hideKeyBoard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void saveSpecialOrderPartial(String occasion_id, String message, String delv_date, String spe_intr, String occasion_name, String price, String weight, String qty, String cgst_per, String sgst_per, String total_addons_price, String schedule_id) {



        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtil.showProgressDialogCancelable(getActivity(), "");
            }
        });


        RequestBody AppVersionCode = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getVersionCode()));
        RequestBody AppAndroidId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getAndroidID()));

//        RequestBody Type_api = RequestBody.create(MediaType.parse("text/plain"), Type);
        RequestBody reg_id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mySharedPreferences.getDeviceID()));
        RequestBody reg_user_id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(CommonUtil.USER_ID));
        RequestBody req_key = RequestBody.create(MediaType.parse("text/plain"), ApiUrls.TESTING_KEY);
        RequestBody req_company_id = RequestBody.create(MediaType.parse("text/plain"), CommonUtil.COMP_ID);
        RequestBody req_cust_id = RequestBody.create(MediaType.parse("text/plain"), CommonUtil.CUST_ID);
        RequestBody req_occasion_id = RequestBody.create(MediaType.parse("text/plain"), occasion_id);
        RequestBody req_message = RequestBody.create(MediaType.parse("text/plain"), message);
        RequestBody req_delv_date = RequestBody.create(MediaType.parse("text/plain"), delv_date);
        RequestBody req_spe_intr = RequestBody.create(MediaType.parse("text/plain"), spe_intr);

        RequestBody req_occasion_name = RequestBody.create(MediaType.parse("text/plain"), occasion_name);
        RequestBody req_price = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody req_weight = RequestBody.create(MediaType.parse("text/plain"), weight);
        RequestBody req_qty = RequestBody.create(MediaType.parse("text/plain"), qty);
        RequestBody req_cgst_per = RequestBody.create(MediaType.parse("text/plain"), cgst_per);
        RequestBody req_sgst_per = RequestBody.create(MediaType.parse("text/plain"), sgst_per);
        RequestBody req_total_addons_price = RequestBody.create(MediaType.parse("text/plain"), total_addons_price);
        RequestBody req_schedule_id = RequestBody.create(MediaType.parse("text/plain"), schedule_id);
        RequestBody req_json_item_detail = RequestBody.create(MediaType.parse("text/plain"), mySharedPreferences.getSelectedJsonObject().toString());
        RequestBody req_json_addonse_item_details = RequestBody.create(MediaType.parse("text/plain"), mySharedPreferences.getSelecteItemJsonArray().toString());


        Call<SavePartialOrderPojo> call = apiInterface.saveSpecialOrderPartial(
                AppVersionCode,
                AppAndroidId,
                reg_id,
                reg_user_id,
                req_key,
                req_company_id,
                req_cust_id,
                req_occasion_id,
                req_message,
                req_delv_date,
                req_spe_intr,
                req_occasion_name,
                req_price,
                req_weight,
                req_qty,
                req_cgst_per,
                req_sgst_per,
                req_total_addons_price,
                req_schedule_id,
                null,
                req_json_item_detail,
                req_json_addonse_item_details

        );


        call.enqueue(new Callback<SavePartialOrderPojo>() {
            @Override
            public void onResponse(Call<SavePartialOrderPojo> call, retrofit2.Response<SavePartialOrderPojo> response) {
                DialogUtil.hideProgressDialog();


                try {
                    if (response.isSuccessful() && response.body() != null) {


                        SavePartialOrderPojo savePartialOrderPojo = response.body();

                        if (savePartialOrderPojo != null && savePartialOrderPojo.getFLAG() == 1) {
                            getActivity().finish();
                            // Intent special_order_confirmation_receipt_intent = new Intent(CustomizeScreenActivity.this, Special_Order_Confirmation_Receipt.class);
                            //  special_order_confirmation_receipt_intent.putExtra("id", savePartialOrderPojo.getID() + "");
                            //  special_order_confirmation_receipt_intent.putExtra("total_addons_price", total_addons_price);
                            // startActivity(special_order_confirmation_receipt_intent);
                            //  finish();
                        } else {
                            Toast.makeText(getActivity(), savePartialOrderPojo.getMESSAGE(), Toast.LENGTH_SHORT).show();
                        }


                    }
                } catch (Exception e) {

                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SavePartialOrderPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


    @Override
    public void submitData() {
        Toast.makeText(getActivity(),"Donee",Toast.LENGTH_LONG).show();

    }
}