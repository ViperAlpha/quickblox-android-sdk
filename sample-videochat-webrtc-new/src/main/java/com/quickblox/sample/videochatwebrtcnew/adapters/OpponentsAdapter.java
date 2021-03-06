package com.quickblox.sample.videochatwebrtcnew.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.quickblox.sample.videochatwebrtcnew.R;
import com.quickblox.sample.videochatwebrtcnew.User;
import com.quickblox.sample.videochatwebrtcnew.activities.ListUsersActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tereha on 27.01.15.
 */
public class OpponentsAdapter extends BaseAdapter {

    private List<User> opponents;
    private LayoutInflater inflater;
    public static int i;
    public List<User> selected = new ArrayList<>();

    public OpponentsAdapter(Context context, List<User> users) {
        this.opponents = users;
        this.inflater = LayoutInflater.from(context);

    }

    public List<User> getSelected() {
        return selected;
    }

    public int getCount() {
        return opponents.size();
    }

    public User getItem(int position) {
        return opponents.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    private int getNumber(List<User> opponents, User user) {
        return opponents.indexOf(user);
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_opponents, null);
            holder = new ViewHolder();
            holder.opponentsNumber = (TextView) convertView.findViewById(R.id.opponentsNumber);
            holder.opponentsName = (TextView) convertView.findViewById(R.id.opponentsName);
            holder.opponentsRadioButton = (RadioButton) convertView.findViewById(R.id.opponentsCheckBox);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final User user = opponents.get(position);


        if (user != null) {

            holder.opponentsNumber.setText(String.valueOf(ListUsersActivity.getUserIndex(user.getId())));

            holder.opponentsNumber.setBackgroundResource(ListUsersActivity.resourceSelector
                    (ListUsersActivity.getUserIndex(user.getId())));
            holder.opponentsName.setText(user.getFullName());
            holder.opponentsRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        i = user.getId();
                        selected.removeAll(selected);
                        selected.add(user);
                    } else {
                        if (i == user.getId()) {
                            i = 0;
                        }
                        selected.remove(user);
                        holder.opponentsRadioButton.setChecked(false);
//                        selected.removeAll(selected);
                    }
                    notifyDataSetChanged();
                }
            });

//            Log.d(TAG, "Method getView. i = " + i + "");
//            Log.d(TAG, "Method getView. User id" + user.getId() + "");
            holder.opponentsRadioButton.setChecked(i == user.getId());

        }

        return convertView;
    }


    public static class ViewHolder {
        TextView opponentsNumber;
        TextView opponentsName;
        RadioButton opponentsRadioButton;
    }
}
