package ca.mobileappsolutions.jdheatingcooling;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nick on 16-08-22.
 */
public class Filters extends Fragment {
    public Filters(){}
    Map<Integer, String> dict;
    View rootView;
    int day;
    int month;
    int year;
    SharedPreferences myPrefs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myPrefs = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        if (myPrefs.getBoolean("datePicked", true)){
            rootView= inflater.inflate(R.layout.calendar, container, false);
            Log.d("RUN", "LOOP");
            setDatePicker();
            
        }
        else{
            rootView = inflater.inflate(R.layout.fliter, container, false);
            setDict();
            filterPage();
        }
        return rootView;
    }
    public void setDatePicker(){
        final DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        datePicker.setMaxDate(new Date().getTime());
        Button next = (Button) rootView.findViewById(R.id.date_set);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor e = myPrefs.edit();
                day = datePicker.getDayOfMonth();
                month = datePicker.getMonth();
                year= datePicker.getYear();
                e.putInt("year", year);
                e.putInt("month", month);
                e.putInt("day", day);
                e.putBoolean("datePicked", false);
                e.apply();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, new Filters()).commit();
            }
        });
    }
    public void filterPage(){
        TextView lastDate = (TextView) rootView.findViewById(R.id.current_filter_date);
        TextView nextDate = (TextView) rootView.findViewById(R.id.filter_notified);
        day = myPrefs.getInt("day", 0);
        month = myPrefs.getInt("month", 0);
        year = myPrefs.getInt("year", 0);
        String text = " " + getResources().getString(R.string.filter_3) + " " + dict.get(month) + " " + Integer.toString(day) + ", " + Integer.toString(year);
        lastDate.setText(text);
        if (month>8){
            year+=1;
            Log.d("Test", dict.get(month) + " " + Integer.toString(day) + ", " + Integer.toString(year));
            month-=12;
        }
        month+=3;
        Log.d("Test", dict.get(month) + " " + Integer.toString(day) + ", " + Integer.toString(year));
        if (day ==31){
            if (month==3 || month==5 || month==8 || month==5 || month==10){
                day-=30;
                month+=1;
            }
        }
        else if (month == 1) {
            if (year % 4 == 0) {
                if (day > 29) {
                    day -= 29;
                    month += 1;
                }
            } else {
                if (day > 28) {
                    day -= 28;
                    month += 1;
                }
            }
        }
        Log.d("Test2", dict.get(month) + " " + Integer.toString(day) + ", " + Integer.toString(year));
        String myDate = Integer.toString(day)+"/"+Integer.toString(month+1)+"/"+Integer.toString(year);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date strDate = sdf.parse(myDate);
            if (new Date().after(strDate)) {
                text = getResources().getString(R.string.filter_5);
            }
            else {
                text = getResources().getString(R.string.filter_4) + " " +  dict.get(month) + " " + Integer.toString(day) + ", " + Integer.toString(year);
            }
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        nextDate.setText(text);
        Button changeDate = (Button) rootView.findViewById(R.id.change_date_filter);
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor e = myPrefs.edit();
                e.putBoolean("datePicked", true);
                e.apply();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, new Filters()).commit();
            }
        });

    }
    public void setDict(){
        dict = new HashMap<Integer, String>();
        dict.put(0, "January");
        dict.put(1, "February");
        dict.put(2, "March");
        dict.put(3, "April");
        dict.put(4, "May");
        dict.put(5, "June");
        dict.put(6, "July");
        dict.put(7, "August");
        dict.put(8, "September");
        dict.put(9, "October");
        dict.put(10, "November");
        dict.put(11, "December");
    }
}
