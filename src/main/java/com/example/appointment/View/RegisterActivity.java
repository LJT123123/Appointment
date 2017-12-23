package com.example.appointment.View;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appointment.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private List<String> campus_list = new ArrayList<>();
    private List<String> sex_list = new ArrayList<>();
    private Toolbar toolbar;
    private EditText student_id;
    private EditText name;
    private EditText password;
    private EditText mailbox_address;
    private EditText nickname;
    private Spinner campus;
    private Spinner sex;
    private ArrayAdapter<String> campus_adapter;
    private ArrayAdapter<String> sex_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        campus_list.add("");
        campus_list.add("中心校区");
        campus_list.add("软件园校区");
        campus_list.add("兴隆山校区");
        campus_list.add("洪家楼校区");
        campus_list.add("千佛山校区");
        campus_list.add("趵突泉校区");
        sex_list.add("");
        sex_list.add("男");
        sex_list.add("女");
        campus_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, campus_list);
        campus_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, sex_list);
        sex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        initView();
        initEvent();
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar_Register);
        student_id = findViewById(R.id.student_id_edit_text_Register);
        name = findViewById(R.id.name_edit_text_Register) ;
        password = findViewById(R.id.password_edit_text_Register);
        mailbox_address = findViewById(R.id.mailbox_address_edit_text_Register);
        campus = findViewById(R.id.campus_spinner_Register);
        sex = findViewById(R.id.sex_spinner_Register);
        nickname = findViewById(R.id.nickname_edit_text_Register);
    }

    private void initEvent(){
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
        editor.putString("campus","");
        editor.putString("sex","");
        editor.apply();

        //加载工具栏
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        campus.setAdapter(campus_adapter);
        campus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String campus = campus_list.get(position);
                editor = pref.edit();
                editor.putString("campus",campus);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                editor = pref.edit();
                editor.putString("campus",null);
                editor.apply();
            }
        });

        sex.setAdapter(sex_adapter);
        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String sex = sex_list.get(position);
                editor = pref.edit();
                editor.putString("sex",sex);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                editor = pref.edit();
                editor.putString("sex",null);
                editor.apply();
            }
        });
    }

    //加载toolbar菜单文件
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_register,menu);
        return true;
    }

    //设置工具栏按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.finish:
                Toast.makeText(RegisterActivity.this,pref.getString("campus",null)+pref.getString("sex",null),Toast.LENGTH_LONG).show();
                editor.clear();
                editor.apply();
                finish();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

}
