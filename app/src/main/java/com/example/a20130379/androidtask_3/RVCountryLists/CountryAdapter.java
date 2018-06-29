package com.example.a20130379.androidtask_3.RVCountryLists;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.a20130379.androidtask_3.MainActivity;
import com.example.a20130379.androidtask_3.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<Country> mCountry;
    private Activity mActivity;
    // Pass in the contact array into the constructor
    public CountryAdapter(List<Country> country, Activity activity) {
        mCountry = country;
        mActivity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView countryTextView;
        public ImageView flagImageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            countryTextView = (TextView) itemView.findViewById(R.id.country_name);
            flagImageView = (ImageView) itemView.findViewById(R.id.country_flag);
        }
    }

    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View countryView = inflater.inflate(R.layout.rv_country, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(countryView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CountryAdapter.ViewHolder viewHolder, int position) {
        Country country = mCountry.get(position);

        TextView textView = viewHolder.countryTextView;
        textView.setText(country.getName());
        ImageView flag = viewHolder.flagImageView;

        SvgLoader.pluck()
                .with(this.mActivity)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(country.getFlag(), flag);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mCountry.size();
    }


}