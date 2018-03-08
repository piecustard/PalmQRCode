package palm.cpn.co.th.palmqrcode.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by Lorien on 08/03/2018.
 */

public class PostNewUserToServer extends AsyncTask<String, Void, String> {

    private Context context;

    public PostNewUserToServer(Context context) {   // Constructor Method
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Name", strings[0])
                    .add("User", strings[1])
                    .add("Password", strings[2])
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[3]).post(requestBody).build();    // จ่าหน้าซองจดมายสำหรับส่งไป
            Response response = okHttpClient.newCall(request).execute();            // ส่งไปและรับค่าที่ทำงานเสร็จแล้ว
            return response.body().string();                                        // ส่งค่าที่ได้กลับออกไป

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
