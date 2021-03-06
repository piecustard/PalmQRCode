package palm.cpn.co.th.palmqrcode.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by Lorien on 08/03/2018.
 */

public class GetAllData extends AsyncTask<String, Void, String>{
    private Context context;

    public GetAllData(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[0]).build(); // อ่านข้อความที่เป็น JSON
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();



        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
