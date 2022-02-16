package com.example.hyundai.fragmenty;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.hyundai.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.HashMap;

public class ViewFragment extends Fragment {

    ShapeableImageView imageView;
    TextInputEditText fullName,EmailId,password;

    MaterialButton button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final Serializable serializable=getArguments().getSerializable("value");
        View view =inflater.inflate(R.layout.viewfragment, container, false);

        imageView = view.findViewById(R.id.text_header);
        fullName=view.findViewById(R.id.edt_fname);
        EmailId=view.findViewById(R.id.edt_email);
        password=view.findViewById(R.id.edt_password);
        button=view.findViewById(R.id.Edit);

        Glide.with(getActivity())
                .load(((HashMap) serializable).get("avatar"))
                .override(200, 200)
                .into(imageView);
        fullName.setText(((HashMap) serializable).get("first_name").toString());
        EmailId.setText(((HashMap) serializable).get("email").toString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("value", serializable);

                EditFragment fm=new EditFragment();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fm.setArguments(bundle);
                fr.replace(R.id.fragment_name,fm);
                fr.commit();
            }
        });


        return view;





    }
}