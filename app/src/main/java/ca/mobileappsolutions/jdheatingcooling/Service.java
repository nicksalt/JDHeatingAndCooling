package ca.mobileappsolutions.jdheatingcooling;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nick on 16-08-25.
 */
public class Service extends Fragment{
    public Service(){}
    SharedPreferences myPrefs;
    SharedPreferences.Editor e;
    Spinner spinner;
    Map<Integer, String> dict;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.service, container, false);
        myPrefs = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        e = myPrefs.edit();
        setFurnaceSpinner(rootView);
        setButtons(rootView);
        setDict();
        setText(rootView);
        return rootView;
    }
    public void setFurnaceSpinner(View view){
        spinner = (Spinner) view.findViewById(R.id.furnace_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.furnace_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(myPrefs.getInt("position", 0));
    }
    public void setButtons(View view){
        Button enterDate = (Button) view.findViewById(R.id.enter_date_service);
        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = spinner.getSelectedItemPosition();
                e.putInt("position", position);
                e.apply();
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });
    }
    public void setText(View view){
        if (myPrefs.getBoolean("had_previous_service", false)){
            TextView textView = (TextView)view.findViewById(R.id.service_date_entered);
            String year = String.valueOf(myPrefs.getInt("service_year", 0));
            Integer month = myPrefs.getInt("service_month", 0);
            String day = String.valueOf(myPrefs.getInt("service_day", 0));
            String text = getResources().getString(R.string.service_4) + " " + dict.get(month) + " "+ day + ", " + year;
            textView.setText(text);
            String furnaceType = spinner.getSelectedItem().toString();
            String[] arrayList = getResources().getStringArray(R.array.furnace_type);
            int time = 0;
            SharedPreferences.Editor e = myPrefs.edit();
            if (furnaceType.equals(arrayList[0])){
                time = 1;
                e.putString("serviceDateSet", arrayList[0]);
            }
            else if (furnaceType.equals(arrayList[1])){
                time=2;
                e.putString("serviceDateSet", arrayList[1]);
            }
            else if (furnaceType.equals(arrayList[2])){
                time=2;
                e.putString("serviceDateSet", arrayList[2]);
            }
            Integer yearInt = Integer.valueOf(year);
            Integer dayInt =Integer.valueOf(day);
            yearInt+=time;
            if (month == 1) {
                if (yearInt % 4 > 0) {
                    if (dayInt > 28) {
                        dayInt -= 28;
                        month += 1;
                    }}}
            year = String.valueOf(yearInt);
            day = String.valueOf(dayInt);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date strDate = null;
            try {
                strDate = sdf.parse(day+"/"+String.valueOf(month+1)+"/"+year);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            if (new Date().after(strDate)) {
                text = getString(R.string.service_8);
                TextView textView1 = (TextView) view.findViewById(R.id.nex_service_text);
                textView1.setText(text);
                return;
            }
            text = getResources().getString(R.string.service_6)+ " " + dict.get(month) + " "+ day + ", " + year +". "
                    + getResources().getString(R.string.service_7);
            e.putInt("serviceDay", dayInt);
            e.putInt("serviceMonth", month);
            e.putInt("serviceMonth", yearInt);
            e.putBoolean("serviceDateSet", true);
            e.apply();
            Intent startService = new Intent(getActivity(), TimeService.class);
            getActivity().startService(startService);
            TextView textView1 = (TextView) view.findViewById(R.id.nex_service_text);
            textView1.setText(text);


        }
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
