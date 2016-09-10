package ca.mobileappsolutions.jdheatingcooling;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Nick on 2016-09-03.
 */

public class Recommend extends Fragment {
    public Recommend(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.recommend, container, false);
        setEmail(rootView);
        return rootView;
    }
    public void setEmail(View view){
        final EditText editText = (EditText) view.findViewById(R.id.toEmail);
        final EditText editText1 = (EditText) view.findViewById(R.id.message);
        Button button = (Button) view.findViewById(R.id.send_email);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriText =
                        "mailto:"+ editText.getText().toString() +
                                "?subject=" + Uri.encode("Check out the JD Heating & Cooling App!") +
                                "&body=" + Uri.encode(editText1.getText().toString() +"\n" +"\n" + "I use JD Heating & Cooling for my service needs.  This app, helps me track my cleaning/maintenance and book my next appointment.  Check it out! Or visit their website at www.jdhomeheating.com.");;
                Uri uri = Uri.parse(uriText);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("message/rfc822");
                intent.setData(uri);
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

    }
}
