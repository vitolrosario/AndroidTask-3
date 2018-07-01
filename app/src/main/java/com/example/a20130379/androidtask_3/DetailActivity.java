package com.example.a20130379.androidtask_3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.a20130379.androidtask_3.RVCountryLists.Country;
import com.example.a20130379.androidtask_3.RVCountryLists.Currency;
import com.example.a20130379.androidtask_3.RVCountryLists.Language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DetailActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Country country = (Country) getIntent().getSerializableExtra("detail");

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
        country_currency.setText(country.getCurrencies().stream().map(Currency::getName).collect(Collectors.joining(", ")));

        TextView country_lang = (TextView) findViewById(R.id.country_languages);
        country_lang.setText(country.getLanguages().stream().map(Language::getName).collect(Collectors.joining(", ")));

        TextView country_population = (TextView) findViewById(R.id.country_population);
        country_population.setText(String.valueOf(country.getPopulation()));

        TextView country_maps = (TextView) findViewById(R.id.country_maps);
        String mapsUrl = "http://maps.google.com/maps?q=" + country.getName().replace(" ", "%20");
        country_maps.setText(R.string.open_with_maps);
        country_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl));
                startActivity(browser);
            }
        });


    }
}
