package com.wordpress.ayo218.spacenews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ayo on 1/12/2018.
 */

public class RocketAdapter extends RecyclerView.Adapter<RocketAdapter.ViewHolder> {
    private String[] mRocketData;
    private ArrayList<RocketData> rocketData;
    private Context context;

    public RocketAdapter(Context context, ArrayList<RocketData> dataArrayList){
        rocketData = dataArrayList;
        this.context = context;
    }
    @Override
    public RocketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.rocket_list,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RocketAdapter.ViewHolder holder, int position) {

        holder.mRocketName.setText(rocketData.get(position).getRocket_name());
        holder.mFlightNumber.setText(rocketData.get(position).getFlight_number());
        holder.mFlightYear.setText(rocketData.get(position).getFlight_yeat());
    }

    @Override
    public int getItemCount() {
        if (null == mRocketData){
        return 0 ;
    } else {
            return mRocketData.length;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mRocketName;
        public final TextView mFlightYear;
        public final TextView mFlightNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            mRocketName = (TextView)itemView.findViewById(R.id.tv_rocket_name);
            mFlightYear = (TextView)itemView.findViewById(R.id.tv_flight_year);
            mFlightNumber = (TextView)itemView.findViewById(R.id.tv_flight_number);
        }
    }
}
