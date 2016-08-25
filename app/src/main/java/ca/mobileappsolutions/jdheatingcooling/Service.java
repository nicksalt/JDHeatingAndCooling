package ca.mobileappsolutions.jdheatingcooling;

import android.app.AlertDialog;
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

import java.util.Date;

/**
 * Created by nick on 16-08-25.
 */
public class Service extends Fragment{
    public Service(){}
    SharedPreferences myPrefs;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.service, container, false);
        myPrefs = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        setFurnaceSpinner(rootView);
        Button enterDate = (Button) rootView.findViewById(R.id.enter_date_service);
        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendar(inflater, container);
            }
        });
        return rootView;
    }
    public void setFurnaceSpinner(View view){
        Spinner spinner = (Spinner) view.findViewById(R.id.furnace_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.furnace_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
    }
    public void showCalendar(LayoutInflater inflater, ViewGroup container){
        View dateDialogView = View.inflate(getActivity(), R.layout.date_picker, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        View tempView = inflater.inflate(R.layout.service, container, false);
        DatePicker datePicker = (DatePicker) tempView.findViewById(R.id.service_date_picker);
        datePicker.setMaxDate(new Date().getTime());
        alertDialog.dismiss();
        alertDialog.setView(dateDialogView);
        alertDialog.show();
    }
}
