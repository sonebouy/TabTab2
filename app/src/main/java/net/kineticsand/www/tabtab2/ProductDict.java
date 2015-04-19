package net.kineticsand.www.tabtab2;

import android.content.Context;
import android.os.AsyncTask;
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

    public List<Product> products;
    int dbCount=-1;

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
        products = wsr.getProducts();
    }

    public boolean saveLocalToDatabase()
    {
        for(int i=0;i<19;i++) {
            int count = getProductCount();
            if (count >= products.size()) {
                return false;
            }
            db.addProduct(products.get(count));
            dbCount++;
        }
        return true;
    }

    public int getLoadingPercent()
    {
        int now = getProductCount();
        int total = products.size();
        return (now*100)/total;
    }


    public void clearProducts()
    {
        dbCount=0;
        db.clearProducts();
    }

    public int getProductCount()
    {
        if(dbCount==-1)
        {
            dbCount=db.getProductCount();
        }
        return dbCount;
    }

    void showText(String msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }


}

