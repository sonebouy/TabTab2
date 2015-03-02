package net.kineticsand.www.tabtab2;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nut on 18/1/2558.
 */
public class ProductDict {
    DatabaseHandler db;
    WebServiceReader wsr;
    Context context;
    private static ProductDict ourInstance = new ProductDict();
    public static ProductDict getInstance() {
        return ourInstance;
    }
    private ProductDict() {}

    public void init(Context context){
        db = new DatabaseHandler(context);
        wsr = new WebServiceReader();
        this.context = context;
    }

    void addProduct(Product newProduct)
    {
        db.addProduct(newProduct);
    }

    public Product getProduct(String barcode)
    {
        return db.getProduct(barcode);
    }

    public boolean hasBarcode(String barcode){
        try{
            Product product =  db.getProduct(barcode);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public void webToDatabase(){
        List<Product> products = wsr.getProducts();
        for(int i=0;i<products.size();i++) {
            db.addProduct(products.get(i));
        }
        showText("Get data from web "+products.size()+"records.");
    }

    public void clearProducts()
    {
        db.clearProducts();
    }


    void showText(String msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }


}
