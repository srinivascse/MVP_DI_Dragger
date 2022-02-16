package com.example.hyundai.fragmenty;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hyundai.Activity.UserList;
import com.example.hyundai.R;
import com.example.hyundai.mvp.Login.LoginContract;
import com.example.hyundai.mvp.Login.LoginPresenter;
import com.example.hyundai.retrofit.APIInterface;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.HashMap;

import javax.inject.Inject;

public class EditFragment extends Fragment implements LoginContract.View {


    ShapeableImageView imageView;
    TextInputEditText fullName,EmailId,password;

    @Inject
    LoginPresenter presenter;

    MaterialButton button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final Serializable serializable=getArguments().getSerializable("value");
        View view = inflater.inflate(R.layout.editfragment, container, false);
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
                presenter.onLoginClick();
            }
        });


        return view;

    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(getActivity(), UserList.class));
    }



    @Override
    public String getEmail() {
        return EmailId.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void showInputError(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPassword(String password) {
        EmailId.setText(password);
    }

    @Override
    public void setEmail(String email) {
        password.setText(email);
    }

    @Override
    public String getUserName() {
        return fullName.getText().toString();
    }

    @Override
    public void setUserName(String userName) {
        fullName.setText(userName);

    }
}
