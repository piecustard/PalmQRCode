package palm.cpn.co.th.palmqrcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import palm.cpn.co.th.palmqrcode.R;
import palm.cpn.co.th.palmqrcode.utility.GetAllData;
import palm.cpn.co.th.palmqrcode.utility.MyAlert;
import palm.cpn.co.th.palmqrcode.utility.MyConstance;

/**
 * Created by chsuwadee on 07/03/2561.
 */

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

//        Login Controller
        loginController();

    }   // Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                get Value from Edit Text
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Have Space", "Please Fill All User and Password");

                } else {
//                    No Space
                    try {
                        MyConstance myConstance = new MyConstance();
                        GetAllData getAllData = new GetAllData(getActivity());
                        getAllData.execute(myConstance.getUrlReadAllUser());

                        String jsonString = getAllData.get();
                        Log.d("8MarchV1", "JSON ==> " + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);    // สร้าง Variable สำหรับอ่าน JSON
                        String[] columnUserStrings = myConstance.getColumUserTableStrings(); // การดึงข้อมูลจากอีกที่หนึ่งมาใช้งาน
                        String[] loginStrings = new String[columnUserStrings.length];    // ทำการจองห้องไว้เท่ากับ columnUserStrings
                        boolean userStatus = true; //   ใช้ตรวจสอบ user ที่ไม่ถูกต้อง


                        for (int i=0; i<jsonArray.length();i+=1) {  // วน loop อ่าน row
                            JSONObject jsonObject = jsonArray.getJSONObject(i); // สร้างตัวชี้จาก index ที่วนลูป
                            if (userString.equals(jsonObject.getString("User"))) { //   หา Field User
//                                User True
                                userStatus = false; // มีข้อมูล User

                                for (int i1=0; i1<columnUserStrings.length;i1+=1) {   // วน loop อ่าน column
                                    loginStrings[i1] = jsonObject.getString(columnUserStrings[i1]);
                                    Log.d("8MarchV1", "LoginStrings[" + i1 + "] ==> " + loginStrings[i1]);
                                }




                            }
                        }   // for loop




                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment,new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }   // Method Create Mask
}   // Main Class
