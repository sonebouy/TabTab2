package net.kineticsand.www.tabtab2;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends ActionBarActivity {

    private TabHost myTabHost;
    private LocalActivityManager myLocalActivityManager;

    int PromotionTabIndex=0;
    int HistoryTabIndex=1;
    int SlipTabIndex=2;
    int ScannerTabIndex=3;
    int SettingTabIndex=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        myTabHost = (TabHost)findViewById(R.id.TabHostMasterUNG);
        myLocalActivityManager = new LocalActivityManager(this,false);
        myTabHost.setup(myLocalActivityManager);
        myLocalActivityManager.dispatchCreate(savedInstanceState);

        TabHost.TabSpec spec;

        Intent goPromotion = new Intent().setClass(this,PromotionActivity.class);
        spec = myTabHost.newTabSpec("TabPromotion").setIndicator("Promotion",
                getResources().getDrawable(R.drawable.icon_history))
                .setContent(goPromotion);
        myTabHost.addTab(spec);

        Intent goHistory = new Intent().setClass(this,HistoryActivity.class);
        spec = myTabHost.newTabSpec("TabHistory").setIndicator("History",
                getResources().getDrawable(R.drawable.icon_history))
                .setContent(goHistory);
        myTabHost.addTab(spec);

        Intent goSlip = new Intent().setClass(this,SlipActivity.class);
        spec = myTabHost.newTabSpec("TabList").setIndicator("List",
                getResources().getDrawable(R.drawable.icon_list))
                .setContent(goSlip);
        myTabHost.addTab(spec);

        Intent goScanner = new Intent().setClass(this,ScannerActivity.class);
        spec = myTabHost.newTabSpec("TabCamera").setIndicator("Camera",
                getResources().getDrawable(R.drawable.icon_camera))
                .setContent(goScanner);
        myTabHost.addTab(spec);

        Intent goSetting = new Intent().setClass(this,SettingActivity.class);
        spec = myTabHost.newTabSpec("TabSetting").setIndicator("Setting",
                getResources().getDrawable(R.drawable.icon_camera))
                .setContent(goSetting);
        myTabHost.addTab(spec);

        myTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("TabCamera")) {
                    callScanner();
                }else if(tabId.equals("TabPromotion")){
                    setPromotionHomepage();
                }else if(tabId.equals("TabHistory")){
                    setHistoryHomepage();
                }
            }
        });

        for (int i = 0; i < myTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) myTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
        }

        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ProductDict.getInstance().init(this.getApplicationContext());
        HistoryDict.getInstance().init(this.getApplicationContext());
    }


    public void switchTab(int tab){
        myTabHost.setCurrentTab(tab);
    }

    public void callScanner()
    {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            Toast toast = Toast.makeText(getApplicationContext(),
                    scanContent, Toast.LENGTH_SHORT);
            sendBarcodeToSlip(scanContent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void sendBarcodeToSlip(String barcode)
    {
        switchTab(SlipTabIndex);
        Activity currentActivity = myLocalActivityManager.getActivity(myTabHost.getCurrentTabTag());
        if(currentActivity != null && currentActivity instanceof SlipActivity)
        {
// pass to children
            ((SlipActivity)currentActivity).addItem(barcode);
        }
    }

    public void setPromotionHomepage()
    {
        switchTab(PromotionTabIndex);
        Activity currentActivity = myLocalActivityManager.getActivity(myTabHost.getCurrentTabTag());
        if(currentActivity != null && currentActivity instanceof PromotionActivity)
        {
// pass to children
            ((PromotionActivity)currentActivity).showPromotionListPage();
        }
    }

    public void setHistoryHomepage()
    {
        switchTab(HistoryTabIndex);
        Activity currentActivity = myLocalActivityManager.getActivity(myTabHost.getCurrentTabTag());
        if(currentActivity != null && currentActivity instanceof HistoryActivity)
        {
// pass to children
            ((HistoryActivity)currentActivity).showPage1();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
