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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a20130379.androidtask_3.RVCountryLists.Country;
import com.example.a20130379.androidtask_3.RVCountryLists.CountryAdapter;
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

public class MainActivity extends AppCompatActivity {


    private AppCompatButton btnSearch;
//    private LinearLayout btnDetail;
    static ArrayList<Country> countryList;
    ArrayList<Country> countryListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText countryEditText = (EditText) findViewById(R.id.country_query);
                String query = countryEditText.getText().toString();
                searchCountryList(query);
            }
        });

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
                            countryList = new ArrayList<Country>();
                            countryList.addAll(countryListModel);

                            RecyclerView rvCountry = (RecyclerView) findViewById(R.id.rvCountryList);
                            CountryAdapter adapter = new CountryAdapter(countryListModel, MainActivity.this, MainActivity.this);
                            rvCountry.setAdapter(adapter);
                            rvCountry.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                            /*for (int i = 0; i < responseList.length(); i++) {
                                countryList.add(new Country(responseList.getJSONObject(i).getString("Name")));
                            }*/

                            findViewById(R.id.loadingPanel).setVisibility(View.GONE);

                        }
                        catch (Exception e)
                        {

                        }
                    }
                }).execute();
    }

    public void CallDetailIntent(String countryCode)
    {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("detail", countryList);
        intent.putExtra("keyDetail", countryCode);
        startActivity(intent);
    }

}
