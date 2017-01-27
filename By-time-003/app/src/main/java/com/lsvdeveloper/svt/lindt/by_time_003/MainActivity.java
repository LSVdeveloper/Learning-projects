package com.lsvdeveloper.svt.lindt.by_time_003;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    FragmentAbout fragAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

        fragAbout = new FragmentAbout();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //noinspection SimplifiableIfStatement
        switch(id){

            case R.id.action_settings:
                Intent intent1 = new Intent(this, com.lsvdeveloper.svt.lindt.by_time_003.SettingsActivity.class);
                startActivity(intent1);
            return true;

            case R.id.action_info:
                fragmentTransaction.replace(R.id.frgmCont,fragAbout);
                fragmentTransaction.commit();
            return true;

            case R.id.action_exit:
                System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intentButton;

    switch(view.getId()){
        case R.id.button1:
            intentButton = new Intent(MainActivity.this, com.lsvdeveloper.svt.lindt.by_time_003.RandomGeneratorActivity.class);
            intentButton.putExtra("nameTable","forOne");
            startActivity(intentButton);
             break;

        case R.id.button2:
            intentButton = new Intent(MainActivity.this, com.lsvdeveloper.svt.lindt.by_time_003.RandomGeneratorActivity.class);
            intentButton.putExtra("nameTable","forCompany");
            startActivity(intentButton);
            break;

        case R.id.button3:
            intentButton = new Intent(MainActivity.this, com.lsvdeveloper.svt.lindt.by_time_003.RandomGeneratorActivity.class);
            intentButton.putExtra("nameTable","forChildren_1_3");
            startActivity(intentButton);
            break;

        case R.id.button4:
            intentButton = new Intent(MainActivity.this, com.lsvdeveloper.svt.lindt.by_time_003.RandomGeneratorActivity.class);
            intentButton.putExtra("nameTable","forChildren_3_6");
            startActivity(intentButton);
            break;

        case R.id.button5:
            intentButton = new Intent(MainActivity.this, com.lsvdeveloper.svt.lindt.by_time_003.RandomGeneratorActivity.class);
            intentButton.putExtra("nameTable","forChildren_6_");
            startActivity(intentButton);
            break;

        case R.id.button6:
            intentButton = new Intent(MainActivity.this, com.lsvdeveloper.svt.lindt.by_time_003.RandomGeneratorActivity.class);
            intentButton.putExtra("nameTable","reading");
            startActivity(intentButton);
            break;
        }
    }
}
