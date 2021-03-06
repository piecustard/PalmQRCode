package palm.cpn.co.th.palmqrcode;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import palm.cpn.co.th.palmqrcode.fragment.DisplayQRFragment;
import palm.cpn.co.th.palmqrcode.fragment.InfoFragment;
import palm.cpn.co.th.palmqrcode.fragment.QRscanFragment;
import palm.cpn.co.th.palmqrcode.fragment.ShowAllFragment;

public class ServiceActivity extends AppCompatActivity {
    //    Explicit
    private String tag = "12MarchV1";
    private String[] loginStrings; // ตัวแปรที่มารับค่า Login
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle; // การทำ Toggle บน toolbar
    private boolean aBoolean = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

//        Get Value From Intent
        getValueFromIntent();

//        Create Toolbar
        createToolbar();

//        Add Fragment to Activity
        addFragment(savedInstanceState);

//        Show Read All
        showReadAll();

//        QR Scan Controller
        qrController();

//        Exit Controller
        exitController();

    }   // Main Method





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_service,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {  // การควบคุมปุ่ม Back

    }

    private void exitController() {
        TextView textView = findViewById(R.id.txtExit);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void qrController() {
        TextView textView = findViewById(R.id.txtQRscan);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction()                          // Open Page
                        .replace(R.id.contentServiceFragment, QRscanFragment.qRscanInstance(loginStrings))
                        .commit();
                drawerLayout.closeDrawers();                                            // Close Drawer

            }
        });
    }

    private void showReadAll() {
        TextView textView = findViewById(R.id.txtShowAll);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentServiceFragment, new ShowAllFragment())
                        .commit();
                drawerLayout.closeDrawers(); // ถ้ามี Drawer ให้ปิดอัตโนมัติด้วย

            }
        });
    }


    private void addFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            if (aBoolean) {                                                         // กรณี true ไปหน้า ShowAllFragment
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.contentServiceFragment, new ShowAllFragment())
                        .commit();
            } else {                                                                // กรณี false ไปหน้า DisplayQRFragment
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.contentServiceFragment,
                                DisplayQRFragment.displayQRInstance(getIntent().getStringExtra("QRcode"),loginStrings))
                        .commit();
            }
        }
    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarService);
        setSupportActionBar(toolbar);

//        Setup Title
        getSupportActionBar().setTitle("My Service");
        getSupportActionBar().setSubtitle(loginStrings[1]);

        getSupportActionBar().setHomeButtonEnabled(true);       // ขอเปิดใช้บริการปุ่ม Home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //ขอให้ปุ่ม Home Click ได้

//        Create Hamberger Icon
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(ServiceActivity.this,
                drawerLayout,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.itemInfo) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentServiceFragment, new InfoFragment())
                    .commit();
            return true;
        }

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();

    }


    private void getValueFromIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login");

        aBoolean = getIntent().getBooleanExtra("Status", true); // รับค่า Key Name Status โดยถ้าไม่มีการส่งมา ให้ส่ง true

        Log.d(tag, "NameLogin ==> " + loginStrings[1]);
    }


}   // Main Class
