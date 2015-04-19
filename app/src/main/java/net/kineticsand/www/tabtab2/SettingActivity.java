package net.kineticsand.www.tabtab2;

import android.app.Dialog;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


public class SettingActivity extends ActionBarActivity {

    ImageView updateProductBtn;
    ImageView clearProductBtn;
    ImageView clearHistoryButton;

    TextView statusProduct;
    TextView recordProduct;
    TextView recordHistory;

    public static SettingActivity instance;

    String loadindStatus="Ready";

    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();

        instance=this;

        updateProductBtn = (ImageView) findViewById(R.id.updateProductBtn);
        clearProductBtn = (ImageView) findViewById(R.id.clearProductBtn);
        clearHistoryButton = (ImageView)findViewById(R.id.clearHistoryBtn);

        statusProduct = (TextView)findViewById(R.id.statusProduct);
        recordProduct = (TextView)findViewById(R.id.recordProduct);
        recordHistory = (TextView)findViewById(R.id.recordHistory);

        final Handler handler = new Handler();
        final Runnable runable = new Runnable() {
            @Override
            public void run() {
                if(loadindStatus=="Loading")
                {
                    ProductDict.getInstance().webToDatabase();
                    loadindStatus="Recording";
                    refreshTextView();
                    handler.postDelayed(this, 1);
                }else if(loadindStatus=="Recording") {
                    if(ProductDict.getInstance().saveLocalToDatabase())
                    {
                        refreshTextView();
                        handler.postDelayed(this, 2);
                    }else{
                        loadindStatus="Ready";
                        refreshTextView();
                    }
                }
            }
        };

        updateProductBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loadindStatus=="Ready") {
                    ProductDict.getInstance().clearProducts();
                    loadindStatus = "Loading";
                    refreshTextView();
                    handler.postDelayed(runable, 1);
                }
            }
        });

        clearProductBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProductDict.getInstance().clearProducts();
                refreshTextView();
            }
        });

        clearHistoryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                HistoryDict.getInstance().clearHistories();
                refreshTextView();
            }
        });

        refreshTextView();
    }

    public void refreshTextView()
    {
        if(loadindStatus=="Recording")
        {
            statusProduct.setText("Loading "+ProductDict.getInstance().getLoadingPercent()+"%");
        }else {
            statusProduct.setText(loadindStatus);
        }
        recordProduct.setText(ProductDict.getInstance().getProductCount()+"");
        recordHistory.setText(HistoryDict.getInstance().getHistoriesCount()+"");
    }


    void showText(String msg)
    {
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
}
