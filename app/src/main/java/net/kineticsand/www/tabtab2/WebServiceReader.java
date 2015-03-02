package net.kineticsand.www.tabtab2;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Bouy on 11/02/2015.
 */
public class WebServiceReader {

    private String productURL = "http://wonderworld.in.th/ebasket/showAllData.php";

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();
        try {
            String result = getHttpGet(productURL);
            JSONArray data = new JSONArray(result);
            for(int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);
                Product product = new Product();
                product.pid = c.getString("pid");
                product.name = c.getString("name");
                product.price = Double.parseDouble(c.getString("price"));
                product.barcode = c.getString("barcode");
                product.update_date = c.getString("update_date");
                product.did = c.getString("did");
                products.add(product);
            }
        }catch (Exception e){
            return new ArrayList<Product>();
        }
        return products;
    }

    public String getHttpGet(String url) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        try {
            URI website = new URI(url);
            HttpGet httpGet = new HttpGet(website);
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return str.toString();
    }

}
