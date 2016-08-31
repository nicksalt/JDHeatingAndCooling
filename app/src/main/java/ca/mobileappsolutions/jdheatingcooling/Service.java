package ca.mobileappsolutions.jdheatingcooling;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nick on 16-08-25.
 */
public class Service extends Fragment{
    public Service(){}
    SharedPreferences myPrefs;
    Map<Integer, String> dict;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.service, container, false);
        myPrefs = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        setFurnaceSpinner(rootView);
        setButtons(rootView);
        setDict();
        setText(rootView);
        return rootView;
    }
    public void setFurnaceSpinner(View view){
        Spinner spinner = (Spinner) view.findViewById(R.id.furnace_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.furnace_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
    }
    public void setButtons(View view){
        Button enterDate = (Button) view.findViewById(R.id.enter_date_service);
        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
