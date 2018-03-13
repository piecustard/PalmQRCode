package palm.cpn.co.th.palmqrcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import palm.cpn.co.th.palmqrcode.R;
import palm.cpn.co.th.palmqrcode.utility.GetFoodWhereQRcode;
import palm.cpn.co.th.palmqrcode.utility.MyConstance;

/**
 * Created by Lorien on 13/03/2018.
 */

public class DisplayQRFragment extends Fragment {

    private String qrScanString;


    public static DisplayQRFragment displayQRInstance(String qrCodeStrings,String[] loginString) {

        DisplayQRFragment displayQRFragment = new DisplayQRFragment();
        Bundle bundle = new Bundle();
        bundle.putString("QRcode", qrCodeStrings);
        bundle.putStringArray("Login",loginString);
        displayQRFragment.setArguments(bundle);
        return displayQRFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Show QR Scan
        showQRScan();

//        Search Controller
        searchController();

//        QRscan Controller
        qRscanController();


    } // Main Method

    private void qRscanController() {
        Button button = getView().findViewById(R.id.btnQRscan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,
                                QRscanFragment.qRscanInstance(getArguments().getStringArray("Login")))
                        .commit();

            }
        });
    }


    private void searchController() {
        Button button = getView().findViewById(R.id.btnSearchQR);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tag = "13MarchV2";
                MyConstance myConstance = new MyConstance();
                String[] columnStrings = myConstance.getColumnFoodStrings();
                String[] valueStrings = new String[columnStrings.length];

                try {

                    GetFoodWhereQRcode getFoodWhereQRcode = new GetFoodWhereQRcode(getActivity());
                    getFoodWhereQRcode.execute(qrScanString,myConstance.getUrlGetFoodWhereQRcode());

                    String jsonString = getFoodWhereQRcode.get();
                    Log.d(tag, "JSON ==> " + jsonString);

                    JSONArray jsonArray = new JSONArray(jsonString);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    for (int i=0 ; i<columnStrings.length;i+=1) {

                        valueStrings[i] = jsonObject.getString(columnStrings[i]);

                    } // for

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentServiceFragment, DetailFragment.detailInstance(
                                    valueStrings[2],
                                    valueStrings[5],
                                    valueStrings[1],
                                    valueStrings[3],
                                    valueStrings[4]))
                            .addToBackStack(null)       // กลับไปหาหน้าที่มันมา
                            .commit();



                } catch (Exception e) {
                    e.printStackTrace();
                }

            } // onClick
        });
    }

    private void showQRScan() {
        TextView textView = getView().findViewById(R.id.txtQRcode);
        qrScanString = getArguments().getString("QRcode");
        textView.setText(qrScanString);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_scan, container, false);
        return view;
    }

}
