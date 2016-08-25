package ca.mobileappsolutions.jdheatingcooling;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nick on 16-08-16.
 */
public class About extends Fragment {

    public About() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about, container, false);
        address(rootView);
        phone(rootView);
        email(rootView);
        return rootView;
    }
    public void maps(){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://www.google.ca/maps/place/370+Boes+Rd,+Brighton,+ON+K0K+1H0/@44.0485615,-77.7051115,17z/data=!3m1!4b1!4m5!3m4!1s0x89d612d919c625d5:0x7965306533681c9a!8m2!3d44.0485615!4d-77.7029228"));
        startActivity(intent);
    }
    public void address(View rootView){
        Button address1 = (Button) rootView.findViewById(R.id.address_1);
        address1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maps();
            }
        });
    }
    public void phone(View rootView){
        Button call =(Button) rootView.findViewById(R.id.phone_number);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:6136619805"));
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    Toast.makeText(getActivity(), "Call permission not granted. Adjust app permissions in your phone settings.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        ImageView imageView = (ImageView) rootView.findViewById(R.id.phone_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:6136619805"));
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    Toast.makeText(getActivity(), "Call permission not granted. Adjust app permissions in your phone settings.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void email(View rootView){
        Button button = (Button) rootView.findViewById(R.id.email);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriText =
                        "mailto:jdheatingandcooling@gmail.com" +
                                "?subject=" + Uri.encode("Send from the JD Heating & Cooling Android app");
                Uri uri = Uri.parse(uriText);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("message/rfc822");
                intent.setData(uri);
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

            }

        });
        ImageView imageView = (ImageView) rootView.findViewById(R.id.email_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriText =
                        "mailto:jdheatingandcooling@gmail.com" +
                                "?subject=" + Uri.encode("Send from the JD Heating & Cooling Android App");
                Uri uri = Uri.parse(uriText);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("message/rfc822");
                intent.setData(uri);
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

            }

        });
    }

}

