package ca.mobileappsolutions.jdheatingcooling;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nick on 16-08-28.
 */
public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm, dd);
    }
    public void populateSetDate(int year, int month, int day) {
        SharedPreferences myPrefs = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = myPrefs.edit();
        e.putInt("service_year", year);
        e.putInt("service_month", month);
        e.putInt("service_day", day);
        e.putBoolean("had_previous_service", true);
        e.apply();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, new Service()).commit();

    }

}