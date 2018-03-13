package palm.cpn.co.th.palmqrcode.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import palm.cpn.co.th.palmqrcode.R;

/**
 * Created by Lorien on 13/03/2018.
 */

public class QRscanFragment extends Fragment{

    //    Explicit
    private ZXingScannerView zXingScannerView;
    private String tag = "13MarchV1";
    private String[] loginStrings;

    public static QRscanFragment qRscanInstance(String[] loginStrings) {    // Method สำหรับส่งค่า Log in แบบ Argument
        QRscanFragment qRscanFragment = new QRscanFragment();   // ประกาศตัวแปรสำหรับส่งข้อมูลออกไปเป็นกลุ่ม
        Bundle bundle = new Bundle();                           // ตัวที่ใช้ในการรับค่ามา
        bundle.putStringArray("Login",loginStrings);            // นำค่า Login มาเก็บไว้ใน Bundle
        qRscanFragment.setArguments(bundle);                    // นำ Bundle มาเก็บไว้ใน Argument
        return qRscanFragment;                                  // ส่งค่าออกไป
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value
        loginStrings = getArguments().getStringArray("Login");  // รับค่า Log in

//        QR scan Controller
        QRscanController();


    } // Main Method

    private void QRscanController() {
        Button button = getView().findViewById(R.id.btnQRscan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                zXingScannerView = new ZXingScannerView(getActivity());
                getActivity().setContentView(zXingScannerView);         // ทำการย้ายการทำงานไปที่ zXingScannerView
                zXingScannerView.startCamera();                         //เปิดกล้อง

                zXingScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
                    @Override
                    public void handleResult(Result result) {

                        String resultString = result.getText().toString();
                        Log.d(tag, "QR Code ==> " + resultString);

                        zXingScannerView.stopCamera();//ปิดกล้อง
                        getActivity().setContentView(R.layout.activity_service); //กลับมาหน้าเดิม

                        Intent intent = getActivity().getIntent();                 // กำหนด Layout ที่ต้องการให้ไปเมื่อ restart activity
                        intent.putExtra("Login", loginStrings);              // ใส่ log in
                        intent.putExtra("Status", false);               // เพื่อกำหนดว่าไปหน้า layout ไหน
                        intent.putExtra("QRcode", resultString);            // ส่งค่า QR (กรณีไม่มีการรับก็ไม่เป็นไร)
                        startActivity(intent);                                     // restart activity

//                        getActivity().getSupportFragmentManager()                //เปลี่ยนหน้าไปยังหน้า Display QR Code
//                                .beginTransaction()
//                                .replace(R.id.contentServiceFragment, new DisplayQRFragment())
//                                .commit();

                    }
                });

            } // onClick
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qrscan, container, false);
        return view;
    }
}
