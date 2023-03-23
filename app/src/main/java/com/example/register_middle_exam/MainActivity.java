package com.example.register_middle_exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.register_middle_exam.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtUserName;
    private EditText edtPassword;
    private EditText edtConfirmPass;
    private Button btnRegister;
    private RecyclerView rcvUser;

    private UserAdapter userAdapter;
    private List<User> mListUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        userAdapter = new UserAdapter();
        mListUser = new ArrayList<>();
        userAdapter.setData(mListUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);
        rcvUser.setAdapter(userAdapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
    }



    private void initUi(){
        edtEmail = findViewById(R.id.edt_email);
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPass = findViewById(R.id.edt_confirm_password);
        rcvUser = findViewById(R.id.rcv_user);
        btnRegister = findViewById(R.id.btn_register);

    }
    private void addUser() {
        String strEmail = edtEmail.getText().toString().trim();
        String strUsername = edtUserName.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();
        String strConfirmPass = edtConfirmPass.getText().toString().trim();
        if (TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strEmail)|| TextUtils.isEmpty(strConfirmPass)|| TextUtils.isEmpty(strPassword)){
            return;
        }

        User user = new User(strUsername,strEmail,strPassword,strConfirmPass);

        if(isUserExist(user)){
            Toast.makeText(this,"User exist!!",Toast.LENGTH_SHORT).show();
            return;
        }

        UserDatabase.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(this,"Add user successfully",Toast.LENGTH_SHORT).show();
        edtEmail.setText("");
        edtUserName.setText("");
        edtPassword.setText("");
        edtConfirmPass.setText("");
        hideSoftKeyboard();
        loadData();
    }
    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }
    private boolean isUserExist(User user){
        List<User> list = UserDatabase.getInstance(this).userDAO().checkUser(user.getUsername());
        return list != null && !list.isEmpty();
    }
    private void loadData(){
        mListUser = UserDatabase.getInstance(this).userDAO().getListUser();
        userAdapter.setData(mListUser);
    }
}