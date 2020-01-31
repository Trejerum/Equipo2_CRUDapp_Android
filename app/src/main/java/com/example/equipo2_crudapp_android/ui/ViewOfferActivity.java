package com.example.equipo2_crudapp_android.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;
import com.example.equipo2_crudapp_android.ui.adapters.CommentsRecyclerViewAdapter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;

import equipo2_crudapp_classes.classes.Comment;
import equipo2_crudapp_classes.classes.Offer;
import equipo2_crudapp_classes.classes.Shop;
import equipo2_crudapp_classes.classes.Software;
import equipo2_crudapp_classes.classes.User;

public class ViewOfferActivity extends AppCompatActivity {

    private Offer offer;
    private ArrayList<Comment> comments;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_view_offer, container, false);

        Shop shop = new Shop();
        shop.setShopId(1);
        shop.setName("Shop 1");

        Software software = new Software();
        software.setSoftwareId(1);
        software.setName("Software Name");

        comments = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Comment comment = new Comment();
            User user = new User();
            user.setLogin("User " + 1);
            comment.setCommentId(i);
            comment.setComment("This is a comment. It's the comment " + i);
            comment.setUser(user);
            comments.add(comment);
        }

        offer = new Offer();
        offer.setOfferId(1);
        offer.setShop(shop);
        offer.setBasePrice(24.95);
        offer.setDiscount(35);
        offer.setDicountedPrice(offer.getBasePrice() * offer.getDiscount() / 100);
        offer.setExpiringDate(Date.valueOf("20/04/2021"));
        offer.setComments((Set<Comment>) comments);

        editTextTitleSoftware = findViewById(R.id.editTextTitleSoftware);
        textViewTitleWarning = findViewById(R.id.textViewTitleWarning);
        editTextOfferPrice = findViewById(R.id.editTextOfferPrice);
        textViewOfferPriceWarning = findViewById(R.id.textViewOfferPriceWarning);
        editTextOfferShop = findViewById(R.id.editTextOfferShop);
        textViewOfferShopWarning = findViewById(R.id.textViewOfferShopWarning);
        editTextofferBasePrice = findViewById(R.id.editTextOfferBasePrice);
        textViewOfferBasePriceWarning = findViewById(R.id.textViewOfferBasePriceWarning);
        editTextOfferDiscount = findViewById(R.id.editTextOfferDiscount);
        textViewOfferDiscountWarning = findViewById(R.id.textViewOfferDiscountWarning);
        editTextOfferExpirationDate = findViewById(R.id.editTextOfferExpirationDate);
        textViewOfferExpirationDateWarning = findViewById(R.id.textViewOfferExpirationDateWarning);
        recyclerViewComments = findViewById(R.id.recyclerViewComments);
        buttonComment = findViewById(R.id.buttonComment);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonReport = findViewById(R.id.buttonReport);
        buttonEdit = findViewById(R.id.toggleButtonEditOffer);

        editTextTitleSoftware.setText(software.getName());
        editTextOfferPrice.setText(String.valueOf(offer.getDicountedPrice()));
        editTextofferBasePrice.setText(String.valueOf(offer.getBasePrice()));
        editTextOfferDiscount.setText(String.valueOf(offer.getDiscount()));
        editTextOfferShop.setText(shop.getName());
        editTextOfferExpirationDate.setText(offer.getExpiringDate().toString());

        initRecyclerView(root);

        return root;
    }

    /**
     * Inits the recycler view with its adapter and loads in it the loaded comments.
     * @param root The view.
     */
    private void initRecyclerView(View root){
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        CommentsRecyclerViewAdapter adapter = new CommentsRecyclerViewAdapter(root.getContext(), comments);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
    }
}
