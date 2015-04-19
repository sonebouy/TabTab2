package net.kineticsand.www.tabtab2;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class PromotionActivity extends ActionBarActivity {

    TextView HeaderText;
    LinearLayout Layout1;
    LinearLayout Layout2;
    LinearLayout Layout3;

    ImageView IconRedHot;
    ImageView IconSale;
    ImageView IconBuy11;
    ImageView IconBuy21;
    ImageView IconBuy31;
    ImageView IconSale2;

    ImageView BannerRedHot;
    ImageView BannerSale;
    ImageView BannerBuy11;
    ImageView BannerBuy21;
    ImageView BannerBuy31;
    ImageView BannerSale2;

    ScrollView ProlistRedHot;
    ScrollView ProlistSale;
    ScrollView ProlistBuy11;
    ScrollView ProlistBuy21;
    ScrollView ProlistBuy31;
    ScrollView ProlistSale2;

    ImageView BackBtn;

    int selectedIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        getSupportActionBar().hide();

        HeaderText = (TextView)findViewById(R.id.promotionHeaderText);
        Layout1 =(LinearLayout)findViewById(R.id.layout_1);
        Layout2 =(LinearLayout)findViewById(R.id.layout_2);
        Layout3 =(LinearLayout)findViewById(R.id.layout_3);
        BannerRedHot = (ImageView)findViewById(R.id.banner_redhot);
        BannerSale = (ImageView)findViewById(R.id.banner_sale);
        BannerBuy11 = (ImageView)findViewById(R.id.banner_buy11);
        BannerBuy21 = (ImageView)findViewById(R.id.banner_buy21);
        BannerBuy31 = (ImageView)findViewById(R.id.banner_buy31);
        BannerSale2 = (ImageView)findViewById(R.id.banner_sale2);
        ProlistRedHot = (ScrollView)findViewById(R.id.prolist_redhot);
        ProlistSale = (ScrollView)findViewById(R.id.prolist_sale);
        ProlistBuy11 = (ScrollView)findViewById(R.id.prolist_buy11);
        ProlistBuy21 = (ScrollView)findViewById(R.id.prolist_buy21);
        ProlistBuy31 = (ScrollView)findViewById(R.id.prolist_buy31);
        ProlistSale2 = (ScrollView)findViewById(R.id.prolist_sale2);
        BackBtn = (ImageView)findViewById(R.id.backBtn);

        IconRedHot =(ImageView)findViewById(R.id.icon_redhot);
        IconSale =(ImageView)findViewById(R.id.icon_sale);
        IconBuy11 =(ImageView)findViewById(R.id.icon_buy11);
        IconBuy21 =(ImageView)findViewById(R.id.icon_buy21);
        IconBuy31 =(ImageView)findViewById(R.id.icon_buy31);
        IconSale2 =(ImageView)findViewById(R.id.icon_sale2);

        IconRedHot.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex=0;
                showPromotionDetailPage();
            }
        });
        IconSale.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex=1;
                showPromotionDetailPage();
            }
        });
        IconBuy11.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex=2;
                showPromotionDetailPage();
            }
        });
        IconBuy21.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex=3;
                showPromotionDetailPage();
            }
        });
        IconBuy31.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex=4;
                showPromotionDetailPage();
            }
        });
        IconSale2.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex=5;
                showPromotionDetailPage();
            }
        });

        BackBtn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPromotionListPage();
            }
        });

        showPromotionListPage();

    }

    public void showPromotionListPage()
    {
        HeaderText.setVisibility(View.VISIBLE);
        Layout1.setVisibility(View.VISIBLE);
        Layout2.setVisibility(View.VISIBLE);
        Layout3.setVisibility(View.VISIBLE);

        BannerRedHot.setVisibility(View.GONE);
        BannerSale.setVisibility(View.GONE);
        BannerBuy11.setVisibility(View.GONE);
        BannerBuy21.setVisibility(View.GONE);
        BannerBuy31.setVisibility(View.GONE);
        BannerSale2.setVisibility(View.GONE);
        ProlistRedHot.setVisibility(View.GONE);
        ProlistSale.setVisibility(View.GONE);
        ProlistBuy11.setVisibility(View.GONE);
        ProlistBuy21.setVisibility(View.GONE);
        ProlistBuy31.setVisibility(View.GONE);
        ProlistSale2.setVisibility(View.GONE);
        BackBtn.setVisibility(View.GONE);
    }

    void showPromotionDetailPage()
    {
        HeaderText.setVisibility(View.GONE);
        Layout1.setVisibility(View.GONE);
        Layout2.setVisibility(View.GONE);
        Layout3.setVisibility(View.GONE);

        BannerRedHot.setVisibility(View.GONE);
        BannerSale.setVisibility(View.GONE);
        BannerBuy11.setVisibility(View.GONE);
        BannerBuy21.setVisibility(View.GONE);
        BannerBuy31.setVisibility(View.GONE);
        BannerSale2.setVisibility(View.GONE);
        ProlistRedHot.setVisibility(View.GONE);
        ProlistSale.setVisibility(View.GONE);
        ProlistBuy11.setVisibility(View.GONE);
        ProlistBuy21.setVisibility(View.GONE);
        ProlistBuy31.setVisibility(View.GONE);
        ProlistSale2.setVisibility(View.GONE);

        BackBtn.setVisibility(View.VISIBLE);
        getProLayout(selectedIndex).setVisibility(View.VISIBLE);
        getProBanner(selectedIndex).setVisibility(View.VISIBLE);
    }


    ScrollView getProLayout(int index)
    {
        switch(index)
        {
            case 0:return ProlistRedHot;
            case 1:return ProlistSale;
            case 2:return ProlistBuy11;
            case 3:return ProlistBuy21;
            case 4:return ProlistBuy31;
            case 5:return ProlistSale2;
        }
        return ProlistRedHot;
    }

    ImageView getProBanner(int index)
    {
        switch(index)
        {
            case 0:return BannerRedHot;
            case 1:return BannerSale;
            case 2:return BannerBuy11;
            case 3:return BannerBuy21;
            case 4:return BannerBuy31;
            case 5:return BannerSale2;
        }
        return BannerRedHot;
    }

    void showText(String msg)
    {
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_promotion, menu);
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
