package com.lsvdeveloper.svt.lindt.by_time_003;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lsvdeveloper.svt.lindt.by_time_003.R.layout.my_list_layout;

public class SettingsActivity extends AppCompatActivity {
    private ListView vs;
    private List<String> settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        vs = (ListView) findViewById(R.id.viewSettings);
        settings= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.settings_title)));
        vs.setAdapter(new Adapter());

        vs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                    // изменить фон
                        break;
                }
            }
        });
    }



    private class Adapter extends BaseAdapter {
        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            final int r;
            if (position < 1) {
                r = 0;
            } else {
                r = 1;
            }
            return r;
        }

        @Override
        public int getCount() {
            return settings.size();
        }

        @Override
        public Object getItem(int i) {
            return settings.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                int item = (int) getItemId(i);
                int viewType = getItemViewType(i);
                if (viewType == 0){
                    view = View.inflate(SettingsActivity.this, R.layout.my_list_layout,null);
                    ViewHolder vh = new ViewHolder();
                    vh.itemTextView = (TextView) view.findViewById(R.id.textView1);
                    view.setTag(vh);
                    vh.itemTextView.setText((String) getItem(i));
                }

                if (viewType == 1){
                    view = View.inflate(SettingsActivity.this, R.layout.my_list_layout_with_switch, null);
                    ViewHolder vh = new ViewHolder();
                    vh.onOffSwitch = (Switch) view.findViewById(R.id.switch1);
                    view.setTag(vh);
                    vh.onOffSwitch.setText((String) getItem(i));
                }
               ViewHolder vh = (ViewHolder) view.getTag();
                switch ((int)getItemId(i)){
                    case 1:{
                        vh.onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                                if (isChecked) {
                                    Log.d("s","sound");


                                }else{
                                    Log.d("s","soundOff");
                                    //убрать звук и сохранить изменения в приложении

                                }
                            }
                        });
                        break;
                    }
                    case 2:{
                        vh.onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                                if (isChecked) {
                                    Log.d("s","vibration");

                                }else{
                                    Log.d("s","vibrationOff");
                                    //убрать вибрацию и сохранить изменения в приложении

                                }
                            }
                        });
                        break;
                    }
                }

            }
            return view;
        }

        private class ViewHolder {
            TextView itemTextView;
            Switch onOffSwitch;
        }

    }

}
