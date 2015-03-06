package net.kineticsand.www.tabtab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nut on 19/1/2558.
 */
public class BasketAdapter extends ArrayAdapter<ItemInfo> {

    private final Context context;
    private final ItemInfo[] values;

    public SlipActivity slipActivity;

    public BasketAdapter(Context context,ItemInfo[] values)
    {
        super(context,R.layout.basket_list_item,values);
        this.context = context;
        this.values = values;
    }

    @Override
    public void add(ItemInfo item)
    {
        super.add(item);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.basket_list_item, parent, false);

        TextView itemNameTextView = (TextView) rowView.findViewById(R.id.itemName);
        TextView itemAmountTextView = (TextView) rowView.findViewById(R.id.itemAmount);
        TextView itemPriceTextView = (TextView) rowView.findViewById(R.id.itemPrice);

        Product product = ProductDict.getInstance().getProduct(values[position].barcode);
        itemNameTextView.setText(product.name);
        itemAmountTextView.setText(Integer.toString(values[position].amount));
        itemPriceTextView.setText(String.format( "%.2f",product.price));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView itemNameTextView = (TextView)v.findViewById(R.id.itemName);
                TextView itemAmountTextView = (TextView) v.findViewById(R.id.itemAmount);
                String productName = itemNameTextView.getText().toString();
                String productAmountText = itemAmountTextView.getText().toString();
                int productAmount = Integer.parseInt(productAmountText);
                slipActivity.openNumberDialog(productName,productAmount);
            }
        });

        return rowView;
    }

    void showtext(String text) {
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}
