package com.example.a20130379.androidtask_3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a20130379.androidtask_3.RVCountryLists.Country;
import com.example.a20130379.androidtask_3.RVCountryLists.CountryAdapter;
import com.example.a20130379.androidtask_3.RVCountryLists.RecyclerViewClickListener;
import com.example.hi.androidtask2.GeneralClass;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {


    private ImageButton btnSearch;
    static ArrayList<Country> countryList;
    ArrayList<Country> countryListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText countryEditText = (EditText) findViewById(R.id.country_query);
                String query = countryEditText.getText().toString();
                searchCountryList(query);
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        CountryAdapter adapter = new CountryAdapter(countryList, this, this);

    }

    @Override
    public void recyclerViewListClicked(View v, Country country) {
        CallDetailIntent(country);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void searchCountryList(String query)
    {
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

        new HttpRequestTask(
                new HttpRequest("https://restcountries.eu/rest/v2/name/" + query, HttpRequest.GET, null),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        try {
                            Type listType = new TypeToken<ArrayList<Country>>(){}.getType();
                            countryListModel = new Gson().fromJson(response.body, listType);

                            FillAdapter(countryListModel);

                            findViewById(R.id.loadingPanel).setVisibility(View.GONE);



                        }
                        catch (Exception e)
                        {

                        }
                    }
                }).execute();
    }

    public void CallDetailIntent(Country country)
    {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("detail", country);
        startActivity(intent);
    }

    private void FillAdapter(ArrayList<Country> list)
    {
        RecyclerView rvCountry = (RecyclerView) findViewById(R.id.rvCountryList);
        CountryAdapter adapter = new CountryAdapter(list, MainActivity.this, this);
        rvCountry.setAdapter(adapter);
        rvCountry.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

}
