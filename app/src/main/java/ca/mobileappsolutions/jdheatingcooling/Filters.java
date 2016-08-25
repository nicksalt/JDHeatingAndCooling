package ca.mobileappsolutions.jdheatingcooling;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nick on 16-08-22.
 */
public class Filters extends Fragment {
    public Filters(){}
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       SharedPreferences myPrefs = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        if (myPrefs.getBoolean("datePicked", true)){
            rootView= inflater.inflate(R.layout.calendar, container, false);
            
        }
        else{
            rootView = inflater.inflate(R.layout.fliter, container, false);
        }
        return rootView;
    }
}
