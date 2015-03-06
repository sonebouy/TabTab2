package net.kineticsand.www.tabtab2;

import android.app.Dialog;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class SlipActivity extends ActionBarActivity {

    BasketAdapter adapter;
    Button saveBtn;
    ListView listview;
    TextView totalText;
    TextView discountText;
    TextView netTotalText;

    List<ItemInfo> allItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip);
        getSupportActionBar().hide();

        listview = (ListView) findViewById(R.id.basketListView);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        totalText =(TextView) findViewById(R.id.totalText);
        discountText =(TextView) findViewById(R.id.discountText);
        netTotalText =(TextView) findViewById(R.id.netTotalText);

        allItem = new ArrayList<ItemInfo>();
        refreshAdapter();
    }

    void refreshAdapter(){
        double total=0;
        double discount=0;

        ItemInfo[] items = new ItemInfo[allItem.size()];
        for(int i=0;i<allItem.size();i++)
        {
            items[i] = allItem.get(i);
            total+=items[i].amount * items[i].product.price;
        }
        adapter = new BasketAdapter(this,items);
        adapter.slipActivity = this;
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        totalText.setText(String.format( "%.2f",total));
        discountText.setText(String.format( "%.2f",discount));
        netTotalText.setText(String.format( "%.2f",total-discount));
    }

    public void addItem(String barcode){
        ProductDict dict = ProductDict.getInstance();
        if(!dict.hasBarcode(barcode))
        {
            Toast.makeText(getApplicationContext(),"Not have "+barcode, Toast.LENGTH_SHORT).show();
            return;
        }
        for(int i=0;i<allItem.size();i++)
        {
            ItemInfo item = allItem.get(i);
            if(item.barcode.equals(barcode))
            {
                item.amount++;
                refreshAdapter();
                return;
            }
        }
        ItemInfo item = new ItemInfo();
        item.amount = 1;
        item.barcode = barcode;
        item.product = dict.getProduct(barcode);
        allItem.add(item);
        refreshAdapter();
    }

    public void openNumberDialog(String productName,int oldValue){
        final Dialog dialog = new Dialog(this);

        //tell the Dialog to use the dialog.xml as it's layout description
        dialog.setContentView(R.layout.number_dialog);
        dialog.setTitle("Set quantity of product");

        final NumberPicker np = (NumberPicker)dialog.findViewById(R.id.numberPicker);
        np.setMinValue(0);
        np.setMaxValue(99);
        np.setWrapSelectorWheel(false);
        np.setValue(oldValue);

        final TextView tv = (TextView)dialog.findViewById(R.id.dialogText);
        tv.setText(productName);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButton);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                int newValue = np.getValue();
                String _productName = tv.getText().toString();
              //  showText(value+"");
               // showtext(newValue+"");
                setProduct(_productName,newValue);
            }
        });

        dialog.show();
    }

    void setProduct(String productName,int newValue)
    {
        int index = -1;
        for(int i=0;i<allItem.size();i++)
        {
            ItemInfo item = allItem.get(i);
            if(item.product.name.equals(productName))
            {
                index = i;
                break;
            }
        }
        if(index==-1)
        {
            return;
        }
        if(newValue<=0)
        {
            allItem.remove(index);
        }else {
            ItemInfo item = allItem.get(index);
            item.amount = newValue;
        }
        refreshAdapter();
    }


    void showtext(String text) {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_slip, menu);
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
