package net.kineticsand.www.tabtab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Nut on 19/1/2558.
 */
public class BasketAdapter extends ArrayAdapter<ItemInfo> {

    private final Context context;
    private final ItemInfo[] values;

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

        return rowView;
    }
}
