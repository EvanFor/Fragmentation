package me.yokeyword.sample;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        initView();
    }

    private void initView() {
        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        findViewById(R.id.btn_flow).setOnClickListener(v -> startActivity(new Intent(EnterActivity.this, me.yokeyword.sample.demo_flow.MainActivity.class)));

        findViewById(R.id.btn_wechat).setOnClickListener(v -> startActivity(new Intent(EnterActivity.this, me.yokeyword.sample.demo_wechat.MainActivity.class)));

        findViewById(R.id.btn_zhihu).setOnClickListener(v -> startActivity(new Intent(EnterActivity.this, me.yokeyword.sample.demo_zhihu.MainActivity.class)));
    }
}
