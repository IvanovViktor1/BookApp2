package com.example.bookapp2.ui.setting;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp2.ProfileActivity;
import com.example.bookapp2.R;
import com.example.bookapp2.Urls;
import com.example.bookapp2.UserList;
import com.example.bookapp2.UserManager;
import com.example.bookapp2.UserManagment;
import com.example.bookapp2.databinding.FragmentForSettingBinding;
import com.example.bookapp2.ui.searcH.SearchViewModel;

import java.util.HashMap;
import java.util.Map;


public class SettingFragment extends Fragment {

    private FragmentForSettingBinding binding;

    EditText Name, Phone, Firstname, Lastname;
    Button update, cPass;
    ProgressDialog progressDialog;
    UserManager userManagment;
    TextView textFname;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingViewModel settingViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);

        binding = FragmentForSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);




        userManagment = new UserManager(getActivity());
        userManagment.checkLogin();
        HashMap<String, String> user = userManagment.userDetails();

        EditText Firstname = binding.pFirstname;
        EditText Lastname = binding.pLastname;
        EditText Name = binding.pName;
        EditText Phone = binding.pPhone;

        String mName = user.get(userManagment.NAME);
        final String mPhone = user.get(userManagment.PHONE);
        String mFirstname = user.get(userManagment.FIRSTNAME);
        String mLastname = user.get(userManagment.LASTNAME);

        Name.setText(mName);
        Phone.setText(mPhone);
        Firstname.setText(mFirstname);
        Lastname.setText(mLastname);

//        Firstname.setText(user.get(UserManagment.FIRSTNAME));
//        Lastname.setText(user.get(UserManagment.LASTNAME));
//        Name.setText(user.get(UserManagment.NAME));
//        Phone.setText(user.get(UserManagment.PHONE));




        //для просмотра списка студентов
        final Button btngoStudentHome = binding.goStudentHome;
        btngoStudentHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), UserList.class);
                startActivity(intent);
            }
        });




        //для изменения  пароля
        final Button btn_changePassword = binding.btnChangePassword;
        btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                View resetpasswordlayout = LayoutInflater.from(getActivity()).inflate(R.layout.change_password, null);
                EditText Oldpass = resetpasswordlayout.findViewById(R.id.edtName);
                EditText NewPass = resetpasswordlayout.findViewById(R.id.edtPhone);
                EditText ConformPass = resetpasswordlayout.findViewById(R.id.edtNewPass2);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("CHANGE PASSWORD");
                builder.setView(resetpasswordlayout);

                builder.setPositiveButton("CHANGE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oldpassword = Oldpass.getText().toString().trim();
                        String newpassword = NewPass.getText().toString().trim();
                        String conformpassword = ConformPass.getText().toString().trim();

                        if(oldpassword.isEmpty() || newpassword.isEmpty() || conformpassword.isEmpty()){
                            Toast.makeText(getActivity(), "some filed are empty", Toast.LENGTH_SHORT).show();
                        } else {

                            progressDialog.show();
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.RESET_PASSWORD_URL, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }){
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("oldpassword", oldpassword);
                                    params.put("newpassword", newpassword);
                                    params.put("conformpassword", conformpassword);
                                    params.put("phone", mPhone);
                                    return params;
                                }
                            };
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.add(stringRequest);
                        }

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });



        final Button btn_update = binding.btnUpdate;
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = Name.getText().toString().trim();
                final String phone = Phone.getText().toString().trim();
                final String firstname = Firstname.getText().toString().trim();
                final String lastname = Lastname.getText().toString().trim();

                if(name.isEmpty()||phone.isEmpty()||firstname.isEmpty()||lastname.isEmpty()){
                    Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.setTitle("Updating...");
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.UPDATE_USER_INFO_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map <String, String> updateParams = new HashMap<>();
                            updateParams.put("name", name);
                            updateParams.put("phone", phone);
                            updateParams.put("mPhone", mPhone);
                            updateParams.put("firstname", firstname);
                            updateParams.put("lastname", lastname);
                            return updateParams;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(stringRequest);
                }
            }
        });






















        //для выхода из учетки
        final Button btn_logout = binding.btnLogout;
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userManagment.logout();
            }
        });












        final TextView textView = binding.textDashboard2;
        settingViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}