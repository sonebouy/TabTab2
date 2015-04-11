package net.kineticsand.www.tabtab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Nut on 30/3/2558.
 */
public class HistoryAdapter  extends ArrayAdapter<History> {
    private final Context context;
    private final History[] values;

    public HistoryAdapter(Context context,History[] values)
    {
        super(context,R.layout.basket_list_item,values);
        this.context = context;
        this.values = values;
    }

    @Override
    public void add(History history)
    {
        super.add(history);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.basket_list_item, parent, false);

        TextView itemNameTextView = (TextView) rowView.findViewById(R.id.itemName);
        TextView itemAmountTextView = (TextView) rowView.findViewById(R.id.itemAmount);
        TextView itemPriceTextView = (TextView) rowView.findViewById(R.id.itemPrice);

        itemNameTextView.setText(values[position].name);
        itemAmountTextView.setText(values[position].amount);
        itemPriceTextView.setText(String.format( "%.2f",Double.parseDouble(values[position].price)));

        return rowView;
    }
}
