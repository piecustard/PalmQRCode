package palm.cpn.co.th.palmqrcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import palm.cpn.co.th.palmqrcode.MainActivity;
import palm.cpn.co.th.palmqrcode.R;
import palm.cpn.co.th.palmqrcode.utility.MyAlert;
import palm.cpn.co.th.palmqrcode.utility.MyConstance;
import palm.cpn.co.th.palmqrcode.utility.PostNewUserToServer;

/**
 * Created by Lorien on 08/03/2018.
 */

public class RegisterFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();

    }   // Main Method


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.itemUpload) {
            uploadValueToServer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void uploadValueToServer() {
//        Get Value From Edit Text
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText userEditText = getView().findViewById(R.id.edtUser);
        EditText passEditText = getView().findViewById(R.id.edtPassword);

        String nameString = nameEditText.getText().toString().trim();
        String userString = userEditText.getText().toString().trim();
        String passwordString = passEditText.getText().toString().trim();


//        Check Space
        if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {
//            Have Space
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.myDialog("Have Space",
                    "Please Fill All Blank");
        } else {
//            No Space
            try {
                MyConstance myConstance = new MyConstance();
                PostNewUserToServer postNewUserToServer = new PostNewUserToServer(getActivity());
                postNewUserToServer.execute(                     // execute() = ทำให้เกิดการประมวลผล
                                                nameString,
                                                userString,
                                                passwordString,
                                                myConstance.getUrlAddUser()
                                            );
                String result = postNewUserToServer.get();
                Log.d("8MarchV1", "result ==> " + result);

                if (Boolean.parseBoolean(result)) {
                    Toast.makeText(getActivity(), "Welcome to App",
                            Toast.LENGTH_SHORT).show(); // Toast ส่งถามแต่ไม่ได้ต้องการคำตอบจริงจัง จะส่งมาหรือเปล่าก็ได้
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "Error Upload to Server",
                            Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }   // try
        }   // if




    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_register,menu);  // Create Menu
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void createToolbar() {

        setHasOptionsMenu(true);

//        Config Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

//        Setup Title and Subtitle
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.message_register));

//        Setup Navigator
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
