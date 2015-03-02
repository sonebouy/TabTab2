package net.kineticsand.www.tabtab2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class HistoryActivity extends ActionBarActivity {

    Button showBtn;
    Button showBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();
        showBtn = (Button) findViewById(R.id.showBtn);
        showBtn2 = (Button) findViewById(R.id.showBtn2);

        showBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProductDict.getInstance().webToDatabase();
            }
        });

        showBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProductDict.getInstance().clearProducts();
            }
        });
    }

    void showText(String msg)
    {
        Toast.makeText(this.getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Toast.makeText(getApplicationContext(), "DDD", Toast.LENGTH_SHORT).show();
    }
}