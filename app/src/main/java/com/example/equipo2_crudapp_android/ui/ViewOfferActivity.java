package com.example.equipo2_crudapp_android.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;

import equipo2_crudapp_classes.classes.Offer;
import equipo2_crudapp_classes.classes.Shop;
import equipo2_crudapp_classes.classes.Software;

public class ViewOfferActivity extends AppCompatActivity {

    private Offer offer;

    private EditText editTextTitleSoftware;
    private TextView textViewTitleWarning;
    private EditText editTextOfferPrice;
    private TextView textViewOfferPriceWarning;
    private EditText editTextOfferShop;
    private TextView textViewOfferShopWarning;
    private EditText editTextofferBasePrice;
    private TextView textViewOfferBasePriceWarning;
    private EditText editTextOfferDiscount;
    private TextView textViewOfferDiscountWarning;
    private EditText editTextOfferExpirationDate;
    private TextView textViewOfferExpirationDateWarning;
    private RecyclerView recyclerViewComments;
    private Button buttonComment;
    private Button buttonDelete;
    private Button buttonReport;
    private Button buttonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_offer);

        Shop shop = new Shop();
        shop.setShopId(1);
        shop.setName("Shop 1");

        Software software = new Software();
        software.setSoftwareId(1);
        software.setName("Software Name");

        offer = new Offer();
        offer.setOfferId(1);
        offer.setShop(shop);
        offer.setBasePrice(24.95);
        offer.setDiscount(35);

        editTextTitleSoftware = findViewById(R.id.editTextTitleSoftware);
        textViewTitleWarning = findViewById(R.id.textViewTitlteWarning);
        editTextOfferPrice = findViewById(R.id.editTextOfferPrice);
        textViewOfferPriceWarning = findViewById(R.id.textViewOfferPriceWarning);
        editTextOfferShop = findViewById(R.id.editTextOfferShop);
        textViewOfferShopWarning = findViewById(R.id.textViewOfferShopWarning;
        editTextofferBasePrice = findViewById(R.id.editTextOfferBasePrice);
        textViewOfferBasePriceWarning = findViewById(R.id.textViewOfferBasePriceWarning);
        editTextOfferDiscount = findViewById(R.id.editTextOfferDiscount);
        textViewOfferDiscountWarning = findViewById(R.id.textViewOfferDiscountWarning);
        editTextOfferExpirationDate = findViewById(R.id.editTextOfferExpirationDate);
        textViewOfferExpirationDateWarning = findViewById(R.id.textViewOfferExpirationDateWarning;
        recyclerViewComments = findViewById(R.id.recyclerViewComments);
        buttonComment = findViewById(R.id.buttonComment);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonReport = findViewById(R.id.buttonReport);
        buttonEdit = findViewById(R.id.toggleButtonEditOffer);
    }
}
