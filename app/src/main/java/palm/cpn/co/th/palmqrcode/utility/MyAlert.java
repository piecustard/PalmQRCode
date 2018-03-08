package palm.cpn.co.th.palmqrcode.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import palm.cpn.co.th.palmqrcode.R;

/**
 * Created by Lorien on 08/03/2018.
 */

public class MyAlert {

    private Context context;  // Variable ที่สำหรับให้ต้นทางส่งค่าเข้ามา (เปรียบเสมือนท่อต่อ)

    public MyAlert(Context context) {
        this.context = context;
    } // Constructor Method

    public void myDialog(String titleString, String messageString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  // ทำการต่อท่อ
        builder.setCancelable(false);                   // Feature ที่ป้องกันไม่ให้กด Cancel ใช้เมื่อต้องการให้ทำงานให้เสร็จก่อน
        builder.setIcon(R.drawable.ic_action_alert);    // Set Icon
        builder.setTitle(titleString);                  // Set Title
        builder.setMessage(messageString);              // Set Mag String
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

} // Main Class
