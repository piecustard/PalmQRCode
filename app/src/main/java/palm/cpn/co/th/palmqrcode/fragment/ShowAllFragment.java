package palm.cpn.co.th.palmqrcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import palm.cpn.co.th.palmqrcode.R;
import palm.cpn.co.th.palmqrcode.utility.GetAllData;
import palm.cpn.co.th.palmqrcode.utility.MyAdapter;
import palm.cpn.co.th.palmqrcode.utility.MyConstance;

/**
 * Created by Lorien on 12/03/2018.
 */

public class ShowAllFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create ListView
        createListView();


    } // Main Method


    private void createListView() {

        ListView listView = getView().findViewById(R.id.listViewFood);  // Create ListView
        String tag = "12MarchV1";
        MyConstance myConstance = new MyConstance();

        try {

            GetAllData getAllData = new GetAllData(getActivity());      // Create Obj สำหรับใช้ Class GetAllData
            getAllData.execute(myConstance.getUrlReadAllFood());        // Get Data จาก URL ที่อยู่ใน getUrlReadAllFood

            String jsonString = getAllData.get();                       // Get Data มาใส่ใน jsonString (เป็นแบบ JSON)
            Log.d(tag, "JSON ==> " + jsonString);                  // เก็บ Logcat

            JSONArray jsonArray = new JSONArray(jsonString);            // Create เพื่อเก็บ String ที่อยู่ในรูปแบบ JSON

            final String[] nameFoodStrings = new String[jsonArray.length()];  // เก็บ Array ของ name
            final String[] imagePathStrings = new String[jsonArray.length()]; // เก็บ Array ของ imagePath
            final String[] categoryStrings = new String[jsonArray.length()];
            final String[] priceStrings = new String[jsonArray.length()];
            final String[] detailStrings = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);             // อ่านทีละ row
                nameFoodStrings[i] = jsonObject.getString("NameFood");    // เก็บค่า NameFood ใส่ Array
                imagePathStrings[i] = jsonObject.getString("ImagePath");  // เก็บค่า ImagePath ใส่ Array
                categoryStrings[i] = jsonObject.getString("Category");
                priceStrings[i] = jsonObject.getString("Price");
                detailStrings[i] = jsonObject.getString("Detail");
            } // For Loop

            MyAdapter myAdapter = new MyAdapter(getActivity(), nameFoodStrings, imagePathStrings);
            listView.setAdapter(myAdapter); // นำข้อมูลที่ได้ไปใส่ใน listView

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // ใส่ Event ให้ ListView
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                    Replace Fragment with Detail
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentServiceFragment, DetailFragment.detailInstance(
                                    nameFoodStrings[i],
                                    imagePathStrings[i],
                                    categoryStrings[i],
                                    priceStrings[i],
                                    detailStrings[i]))
                            .addToBackStack(null).commit();

                }
            });

        } catch (Exception e) {
            e.printStackTrace(); // ให้ทำการพิมพ์ Log สีแดง เมื่อเกิด Error
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_all, container, false);
        return view;
    }

}   // Main Class
