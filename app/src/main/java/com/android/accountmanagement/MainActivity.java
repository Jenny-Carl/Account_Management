package com.android.accountmanagement;

import static com.android.accountmanagement.R.layout.activity_main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
    }

    public void OnOpenInGoogleMaps(View view){
        EditText teamAddress = findViewById(R.id.teamAddressField);
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q="+teamAddress.getText());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void OnSetAvatarButton(View view){
        Intent intent1 = new Intent(getApplicationContext(),ProfileActivity.class);
        startActivityForResult(intent1,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage = findViewById(R.id.defaultImage);
        //Figuring out the correct image
        String drawableName;
        switch (data.getIntExtra("imageID", R.id.logoImage00)) {
            case R.id.logoImage01:
                drawableName = "ic_logo_01";
                break;
            case R.id.logoImage02:
                drawableName = "ic_logo_03";
                break;
            case R.id.logoImage03:
                drawableName = "ic_logo_02";
                break;
            case R.id.logoImage04:
                drawableName = "ic_logo_05";
                break;
            case R.id.logoImage05:
                drawableName = "ic_logo_04";
                break;
            default:
                drawableName = "ic_logo_00";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable",
                getPackageName());
        avatarImage.setImageResource(resID);
    }
}