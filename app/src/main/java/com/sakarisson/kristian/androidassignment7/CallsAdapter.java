package com.sakarisson.kristian.androidassignment7;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.ViewHolder> {
    private ArrayList<Call> calls;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public CallsAdapter(ArrayList<Call> calls) {
        this.calls = calls;
    }

    @Override
    public CallsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_numbers_element, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = calls.get(position).getNumber() + "\n";
        text += calls.get(position).getTimestamp() + " GMT \n";
        text += "Latitude: " + calls.get(position).getLatitude();
        text += ", Longitude" + calls.get(position).getLongitude();
        holder.mTextView.setText(text);
    }

    @Override
    public int getItemCount() {
        return calls.size();
    }
}
