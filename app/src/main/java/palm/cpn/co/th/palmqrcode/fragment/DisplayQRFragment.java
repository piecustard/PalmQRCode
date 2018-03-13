package palm.cpn.co.th.palmqrcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import palm.cpn.co.th.palmqrcode.R;

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

            }
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
