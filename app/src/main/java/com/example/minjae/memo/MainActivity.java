package com.example.minjae.memo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button share;
    private Button save;
    private EditText editText;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public String texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = (Button) findViewById(R.id.save);
        share = (Button) findViewById(R.id.share);
        editText = (EditText) findViewById(R.id.memo);

        sharedPreferences = getSharedPreferences("texts", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        texts = sharedPreferences.getString("texts", "");
        editText.setText(texts);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texts = editText.getText().toString();
                editor.putString("texts", texts);
                editor.commit();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, texts);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.red :
                editText.setTextColor(Color.RED);
            case R.id.blue :
                editText.setTextColor(Color.BLUE);
            case R.id.green :
                editText.setTextColor(Color.GREEN);
        }
        return true;
    }
}
