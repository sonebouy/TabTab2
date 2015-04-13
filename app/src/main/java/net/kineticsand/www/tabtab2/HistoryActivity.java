package net.kineticsand.www.tabtab2;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends ActionBarActivity {

    TextView historyHeaderText;
    DatePicker dpResult;
    Button historyShowBtn;
    TextView historyDateTextView;
    LinearLayout historyListHeader;
    ListView historyListView;
    LinearLayout historyTotalLayout;
    LinearLayout historyDiscountLayout;
    LinearLayout historyNetLayout;
    Button historyBackBtn;
    TextView historyTotalText;
    TextView historyDiscountText;
    TextView historyNetText;

    HistoryAdapter adapter;
    List<History> allHistory;

    String selectDate="";

    DatabaseHistoryHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();

        db = new DatabaseHistoryHandler(getApplicationContext());

        historyHeaderText = (TextView) findViewById(R.id.historyHeaderText);
        dpResult = (DatePicker) findViewById(R.id.dpResult);
        historyShowBtn = (Button) findViewById(R.id.historyShowBtn);
        historyDateTextView = (TextView) findViewById(R.id.historyDateTextView);
        historyListHeader = (LinearLayout) findViewById(R.id.historyListHeader);
        historyListView = (ListView) findViewById(R.id.historyListView);
        historyTotalLayout = (LinearLayout) findViewById(R.id.historyTotalLayout);
        historyDiscountLayout = (LinearLayout) findViewById(R.id.historyDiscountLayout);
        historyNetLayout = (LinearLayout) findViewById(R.id.historyNetLayout);
        historyBackBtn = (Button) findViewById(R.id.historyBackBtn);
        historyTotalText = (TextView)findViewById(R.id.historyTotalText);
        historyDiscountText = (TextView)findViewById(R.id.historyDiscountText);
        historyNetText = (TextView)findViewById(R.id.historyNetText);

        allHistory = new ArrayList<History>();

        historyShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPage2();
                selectDate = dpResult.getDayOfMonth()+"/"+(dpResult.getMonth()+1)+"/"+dpResult.getYear();
                allHistory = HistoryDict.getInstance().getHistories(selectDate);
                historyDateTextView.setText(selectDate);
                refreshAdapter();
            }
        });

        historyBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPage1();
            }
        });

        showPage1();

    }

    public void showPage1(){
        historyHeaderText.setVisibility(View.VISIBLE);
        dpResult.setVisibility(View.VISIBLE);
        historyShowBtn.setVisibility(View.VISIBLE);

        historyDateTextView.setVisibility(View.GONE);
        historyListHeader.setVisibility(View.GONE);
        historyListView.setVisibility(View.GONE);
        historyTotalLayout.setVisibility(View.GONE);
        historyDiscountLayout.setVisibility(View.GONE);
        historyNetLayout.setVisibility(View.GONE);
        historyBackBtn.setVisibility(View.GONE);
    }

    void showPage2(){
        historyHeaderText.setVisibility(View.GONE);
        dpResult.setVisibility(View.GONE);
        historyShowBtn.setVisibility(View.GONE);

        historyDateTextView.setVisibility(View.VISIBLE);
        historyListHeader.setVisibility(View.VISIBLE);
        historyListView.setVisibility(View.VISIBLE);
        historyTotalLayout.setVisibility(View.VISIBLE);
        historyDiscountLayout.setVisibility(View.VISIBLE);
        historyNetLayout.setVisibility(View.VISIBLE);
        historyBackBtn.setVisibility(View.VISIBLE);
    }

    void refreshAdapter() {
        double total=0;
        double discount=0;

        History[] histories = new History[allHistory.size()];
        for(int i=0;i<allHistory.size();i++)
        {
            histories[i] = allHistory.get(i);
            total+= Integer.parseInt(histories[i].amount) * Double.parseDouble(histories[i].price);
        }
        adapter = new HistoryAdapter(this,histories);
        historyListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        historyTotalText.setText(String.format( "%.2f",total));
        historyDiscountText.setText(String.format( "%.2f",discount));
        historyNetText.setText(String.format( "%.2f",total-discount));
    }

    public void open(){
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.number_dialog);
        dialog.setTitle("Set quantity of product");

        final NumberPicker np = (NumberPicker)dialog.findViewById(R.id.numberPicker);
        np.setMinValue(0);
        np.setMaxValue(99);
        np.setWrapSelectorWheel(false);
        np.setValue(10);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButton);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                int value = np.getValue();
                showText(value+"");
            }
        });

        dialog.show();
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
