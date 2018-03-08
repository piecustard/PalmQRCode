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
