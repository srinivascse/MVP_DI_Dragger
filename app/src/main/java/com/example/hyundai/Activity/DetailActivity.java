package com.example.hyundai.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.hyundai.R;
import com.example.hyundai.fragmenty.ViewFragment;

import java.io.Serializable;

public class DetailActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity);

        Serializable str=  getIntent().getSerializableExtra("name");


        loadFragment(new ViewFragment(),str);







    }




    private void loadFragment(Fragment fragment,Serializable serializable) {


        Bundle bundle = new Bundle();
        bundle.putSerializable("value", serializable);

        System.out.println(serializable);
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_name, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
