package palm.cpn.co.th.palmqrcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import palm.cpn.co.th.palmqrcode.R;

/**
 * Created by Lorien on 12/03/2018.
 */

public class DetailFragment extends Fragment {


    public static DetailFragment detailInstance(String nameFood,
                                         String imagePath,
                                         String category,
                                         String price,
                                         String detail) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();                   // คนที่นำข้อมูลใน parameter ไปเก็บไว้ที่กล่องไว้
        bundle.putString("NameFood", nameFood);
        bundle.putString("ImagePath", imagePath);
        bundle.putString("Category", category);
        bundle.putString("Price", price);
        bundle.putString("Detail", detail);
        detailFragment.setArguments(bundle);            // นำ Bundle ไปเก็บไว้ในกล่อง (Argument)

        return detailFragment;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }   // Main Method


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }  // เชื่อม Layout กับ Fragment


}
