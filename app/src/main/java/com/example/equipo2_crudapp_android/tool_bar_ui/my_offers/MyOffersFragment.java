package com.example.equipo2_crudapp_android.tool_bar_ui.my_offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.equipo2_crudapp_android.R;
import com.example.equipo2_crudapp_android.client.interfaces.ShopInterface;
import com.example.equipo2_crudapp_android.client.retrofit.ShopAPIClient;
import com.example.equipo2_crudapp_android.ui.adapters.ShopsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import equipo2_crudapp_classes.classes.Offer;
import equipo2_crudapp_classes.classes.Shop;
import equipo2_crudapp_classes.classes.Software;
import equipo2_crudapp_classes.classes.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Fragment for the offers view.
 */
public class MyOffersFragment extends Fragment {

    private View root;

    private TableLayout tableLayoutOffers;
    private ArrayList<Offer> offers = new ArrayList<>();
    private ArrayList<Software> softwares = new ArrayList<>();
    private List<EditText> tableEditTexts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_my_offers, container, false);

        tableEditTexts = new ArrayList<EditText>();
        tableLayoutOffers = root.findViewById(R.id.tableLayoutOffers);

        for (int i = 0; i < 10; i++){
            Offer offer = new Offer();
            offer.setOfferId(i);
            offer.setBasePrice(i * 25.10);
            offer.setDiscount(i * 9);
            offer.setDicountedPrice(offer.getBasePrice() * offer.getDiscount() / 100);
            offers.add(offer);

            Software software = new Software();
            software.setSoftwareId(i);
            software.setName("Software " + i);
            softwares.add(software);
        }

        populateTable();

        return root;
    }

    private void populateTable() {
        for(int i = 0; i < offers.size(); i++) {
            TableRow row = new TableRow(root.getContext());
            row.setId(100+i);

            TextView softwareName = new TextView(root.getContext());
            softwareName.setText(softwares.get(i).getName());
            softwareName.setId(200+i);

            EditText editTextPrice = new EditText(root.getContext());
            editTextPrice.setText(offers.get(i).getDicountedPrice().toString());
            editTextPrice.setId(300+i);

            EditText editTextDiscount = new EditText(root.getContext());
            editTextDiscount.setText(offers.get(i).getDiscount().toString());
            editTextDiscount.setId(400+i);

            tableEditTexts.add(editTextPrice);
            row.addView(softwareName);
            row.addView(editTextPrice);

            tableLayoutOffers.addView(row);
        }
    }
}