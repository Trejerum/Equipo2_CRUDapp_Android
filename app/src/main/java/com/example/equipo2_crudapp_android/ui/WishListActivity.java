package com.example.equipo2_crudapp_android.ui;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.equipo2_crudapp_android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import equipo2_crudapp_classes.classes.Software;
import equipo2_crudapp_classes.classes.Wish;

public class WishListActivity extends AppCompatActivity implements  View.OnClickListener {
    private TableLayout tableLayoutWishList;
    private EditText editTextWishListSearch;
    private Button buttonWishListSearch;
    private CheckBox checkBoxWishListEdit;
    private List<EditText> tableEditTexts;
    private List<Wish> wishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_wish_list);

        tableLayoutWishList = this.findViewById(R.id.tableLayoutWishList);
        editTextWishListSearch = this.findViewById(R.id.editTextWishListSearch);
        buttonWishListSearch = this.findViewById(R.id.buttonWishListSearch);
        checkBoxWishListEdit = this.findViewById(R.id.checkBoxWishListEdit);

        tableEditTexts = new ArrayList<EditText>();
        buttonWishListSearch.setOnClickListener(this);
        checkBoxWishListEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                View view = getWindow().getDecorView().getRootView();
                toggleEdit();
            }
        });

        wishList = new ArrayList<Wish>();
        Software s1 = new Software();
        Software s2 = new Software();
        Wish w1 = new Wish();
        Wish w2 = new Wish();
        s1.setSoftwareId(1);
        s2.setSoftwareId(2);
        s1.setName("s1");
        s2.setName("s2");
        w1.setMinPrice(1.0);
        w2.setMinPrice(2.0);
        w1.setSoftware(s1);
        w2.setSoftware(s2);

        wishList.add(w1);
        wishList.add(w2);

        populateTable(wishList);
        toggleEdit();
    }


    private void populateTable(List<Wish> data) {
        for (int i = 0; i < data.size(); i++) {
            TableRow row = new TableRow(this);
            row.setId(100 + i);
            TextView softwareName = new TextView(this);
            softwareName.setId(200 + i);
            softwareName.setText(data.get(i).getSoftware().getName());
            EditText editTextPrice = new EditText(this);
            editTextPrice.setText(data.get(i).getMinPrice().toString());
            editTextPrice.setId(300 + i);
            editTextPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            tableEditTexts.add(editTextPrice);
            row.addView(softwareName);
            row.addView(editTextPrice);

            tableLayoutWishList.addView(row);
        }
    }

    private void toggleEdit() {
        if (checkBoxWishListEdit.isChecked()) {
            checkBoxWishListEdit.setText("Save");
            for (int i = 0; i < tableEditTexts.size(); i++) {
                setReadOnly(tableEditTexts.get(i), false);
            }
        } else {
            checkBoxWishListEdit.setText("Edit");
            List<Wish> auxWishes = wishList;
            for (int i = 0; i < tableEditTexts.size(); i++) {
                setReadOnly(tableEditTexts.get(i), true);
                //Update prices
                auxWishes.get(i).setMinPrice(Double.parseDouble(tableEditTexts.get(i).getText().toString()));
            }
            if (!auxWishes.equals(wishList)) {
                //TODO: update the changes on wishes
            }
        }
    }

    private void setReadOnly(EditText editText, boolean readOnly) {
        editText.setFocusable(!readOnly);
        editText.setFocusableInTouchMode(!readOnly);
        editText.setClickable(!readOnly);
        editText.setLongClickable(!readOnly);
        editText.setCursorVisible(!readOnly);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonWishListSearch:
                if (editTextWishListSearch.getText().toString() != null || editTextWishListSearch.getText().toString() != "") {
                    List<Wish> filteredWishes = new ArrayList<>();
                    filteredWishes.addAll(wishList);
                    //Filter results
                    filteredWishes.removeIf(new Predicate<Wish>() {
                        @Override
                        public boolean test(Wish w) {
                            return !w.getSoftware().getName().toLowerCase().contains(editTextWishListSearch.getText().toString().toLowerCase());
                        }
                    });
                    tableLayoutWishList.removeViews(1, tableLayoutWishList.getChildCount() -1);
                    populateTable(filteredWishes);
                    break;
                }
                else {
                    populateTable(wishList);
                }
        }
    }
}
