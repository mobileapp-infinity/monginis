package com.infinity.monginis.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.infinity.monginis.R;
import com.infinity.monginis.api.ApiImplementer;
import com.infinity.monginis.api.ApiUrls;
import com.infinity.monginis.dashboard.adapter.AddsOnAdapter;
import com.infinity.monginis.dashboard.pojo.Get_Addons_Items_List_Pojo;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.MySharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.infinity.monginis.dashboard.adapter.AddsOnAdapter.tvAddsOnCategoryName;

public class AddsOnActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBackAddsOn;
    private MySharedPreferences mySharedPreferences;
    private ExpandableListView exAddsOnn;
    private ArrayList<String> categoryNameList;
    private HashMap<String, List<Get_Addons_Items_List_Pojo.Item>> filteredHashMap;
    FloatingActionButton skip_adds_on, submit_adds_on;
    TextView add_alarm_action_text, add_submit_adds_on;

    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;
    // Use the ExtendedFloatingActionButton to handle the
    // parent FAB
    ExtendedFloatingActionButton mAddFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adds_on);
        init();
        getAddonsItemsList();
    }


    private void fabinit(){
        mAddFab = findViewById(R.id.add_fab);
        // FAB button
        skip_adds_on = findViewById(R.id.skip_adds_on);
        submit_adds_on = findViewById(R.id.submit_adds_on);

        // Also register the action name text, of all the
        // FABs. except parent FAB action name text
        add_alarm_action_text = findViewById(R.id.add_alarm_action_text);
        add_submit_adds_on = findViewById(R.id.add_submit_adds_on);
       // add_submit_adds_on
        // Now set all the FABs and all the action name
        // texts as GONE
        skip_adds_on.setVisibility(View.GONE);
        submit_adds_on.setVisibility(View.GONE);
        add_alarm_action_text.setVisibility(View.GONE);
        add_submit_adds_on.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are
        // invisible
        isAllFabsVisible = false;
        skip_adds_on.setVisibility(View.GONE);
        submit_adds_on.setVisibility(View.GONE);
        add_alarm_action_text.setVisibility(View.GONE);
        add_submit_adds_on.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are
        // invisible
        isAllFabsVisible = false;

        // Set the Extended floating action button to
        // shrinked state initially
        mAddFab.shrink();


        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            skip_adds_on.show();
                            submit_adds_on.show();
                            add_alarm_action_text.setVisibility(View.VISIBLE);
                            add_submit_adds_on.setVisibility(View.VISIBLE);

                            // Now extend the parent FAB, as
                            // user clicks on the shrinked
                            // parent FAB
                            mAddFab.extend();

                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            skip_adds_on.hide();
                            submit_adds_on.hide();
                            add_alarm_action_text.setVisibility(View.GONE);
                            add_submit_adds_on.setVisibility(View.GONE);

                            // Set the FAB to shrink after user
                            // closes all the sub FABs
                            mAddFab.shrink();

                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                    }
                });

        // below is the sample action to handle add person
        // FAB. Here it shows simple Toast msg. The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        submit_adds_on.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getAllSelectedAddsOn();
                    }
                });

        skip_adds_on.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(AddsOnActivity.this, "Alarm Added", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    private void init(){
        fabinit();
        ivBackAddsOn = findViewById(R.id.ivBackAddsOn);
        exAddsOnn = findViewById(R.id.exAddsOnn);
        mySharedPreferences = new MySharedPreferences(this);

    }



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
                            Toast.makeText(AddsOnActivity.this, get_addons_items_list_pojo.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        AddsOnAdapter addsOnAdapter = new AddsOnAdapter(AddsOnActivity.this, filteredHashMap, categoryNameList,exAddsOnn);
                        exAddsOnn.setAdapter(addsOnAdapter);
                        exAddsOnn.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                            @Override
                            public void onGroupExpand(int groupPosition) {

                                tvAddsOnCategoryName.setCompoundDrawables(null,null, ContextCompat.getDrawable(AddsOnActivity.this,R.drawable.ic_baseline_expand_less_24),null);
                            }
                        });
                        exAddsOnn.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                            @Override
                            public void onGroupCollapse(int groupPosition) {
                                tvAddsOnCategoryName.setCompoundDrawables(null,null, ContextCompat.getDrawable(AddsOnActivity.this,R.drawable.ic_baseline_expand_more_24),null);
                            }
                        });


                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<Get_Addons_Items_List_Pojo> call, Throwable t) {
                Toast.makeText(AddsOnActivity.this, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.ivBackAddsOn){
            onBackPressed();
        }
    }

    private List<Get_Addons_Items_List_Pojo.Item> specialCategoryAddOnSelectedItemArrayList = new ArrayList<>();
    private void getAllSelectedAddsOn(){

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

            selectedItemJson.put("item_id",1);
            selectedItemJson.put("item_name", "test");
            selectedItemJson.put("hsn_code", Integer.parseInt("012234"));
            selectedItemJson.put("uom_id", "uom");
            selectedItemJson.put("price", 100);
            selectedItemJson.put("weight", Double.parseDouble("120"));
            selectedItemJson.put("cgst_per", "0.0");
            selectedItemJson.put("sgst_per", "0.0");
            selectedItemJson.put("qty", Integer.parseInt("1"));
            selectedItemJson.put("mrp", Double.parseDouble("100.0"));
            selectedItemJson.put("flavour", "test" + "");
            selectedItemJson.put("shape", "shape" + "");


        } catch (JSONException e) {
            e.printStackTrace();

            System.out.println("error in json creation!!!!!");
        }
        special_item_array.put(selectedItemJson);

    }
}