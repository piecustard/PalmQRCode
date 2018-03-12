package palm.cpn.co.th.palmqrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ServiceActivity extends AppCompatActivity {
    //    Explicit
    private String tag = "12MarchV1";
    private String[] loginStrings; // ตัวแปรที่มารับค่า Login





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

//        Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");
        Log.d(tag, "NameLogin ==> " + loginStrings[1]);

    }   // Main Method



}   // Main Class
