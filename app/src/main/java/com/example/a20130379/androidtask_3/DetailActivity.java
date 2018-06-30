package com.example.a20130379.androidtask_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.a20130379.androidtask_3.RVCountryLists.Country;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DetailActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Country country = new Country();

        ArrayList<Country> countryList = (ArrayList<Country>) getIntent().getSerializableExtra("detail");
        String countryCode = getIntent().getStringExtra("keyDetail");

        for (Country c : countryList) {
            if (c.getNumericCode().equals(countryCode))
            {
                country = c;
            }
        }

        TextView country_name = (TextView) findViewById(R.id.country_name);
        country_name.setText(country.getName());

        TextView country_capital = (TextView) findViewById(R.id.country_capital);
        country_capital.setText(country.getCapital());

        ImageView country_flag = (ImageView) findViewById(R.id.country_flag);
        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(country.getFlag(), country_flag);

        TextView country_calling_codes = (TextView) findViewById(R.id.country_calling_codes);
        country_calling_codes.setText(TextUtils.join(", ", country.getCallingCodes()));

        TextView country_region = (TextView) findViewById(R.id.country_region);
        country_region.setText(country.getRegion() + ", " + country.getSubregion());

        TextView country_timezone = (TextView) findViewById(R.id.country_timezone);
        country_timezone.setText(TextUtils.join(", ", country.getTimezones()));

        TextView country_currency = (TextView) findViewById(R.id.country_currency);
        country_currency.setText(country.getCurrencies().get(0).getName());

        TextView country_lang = (TextView) findViewById(R.id.country_languages);
        country_lang.setText(country.getLanguages().get(0).getName());


        /*Comparator<Country> c = new Comparator<Country>()
        {
            public int compare(Country c1, Country c2)
            {
                return c1.getNumericCode().compareTo(c2.getNumericCode());
            }
        };

        int index = Collections.binarySearch(countryList, new Country(countryCode, 0), c);
        country = countryList.get(index);*/

    }
}
