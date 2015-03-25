package net.kineticsand.www.tabtab2;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class HistoryActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();

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
