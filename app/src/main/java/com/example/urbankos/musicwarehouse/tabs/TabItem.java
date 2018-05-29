package com.example.urbankos.musicwarehouse.tabs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urbankos.musicwarehouse.ItemListActivity;
import com.example.urbankos.musicwarehouse.R;
import com.example.urbankos.musicwarehouse.dao.ItemDAO;
import com.example.urbankos.musicwarehouse.objects.Item;
import com.example.urbankos.musicwarehouse.recyclers.ItemsListAdapter;
import com.transitionseverywhere.TransitionManager;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

@SuppressLint("ValidFragment")
public class TabItem extends Fragment{

//  DAO
    private ItemDAO daoItem;

//  ITEM
    private Item item;

//  ITEM ID
    private String id_item;

//  LAYOUT ELEMENTS
    private EditText tab_item_name;
    private TextView tab_item_name_title;
    private EditText tab_item_firm;
    private EditText tab_item_category;
    private EditText tab_item_quantity;
    private EditText tab_item_price;

    private Button button_edit;
    private Button button_update;

//  VIEW
    private View rootView;

    public TabItem(String i) {
        this.id_item = i;
    }

//  ON CREATE --------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_item, container, false);

        Log.d("POSILJANEJ -> ", id_item);

        //  Dao
        if(daoItem == null){
            daoItem = new ItemDAO(getActivity().getApplicationContext());
        }

        //  Get item
        item = getItem();

        //  Get & Set layout elements
        getLayoutElements();
        setLayoutElements();
        getButtons();

//      Button edit action
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClickableButtons();
            }
        });

//      Button update action
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLayoutElements();

                if(updateItem()){
                    Toast.makeText(getActivity().getApplicationContext(), "Item was successfully updated.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "ERROR", Toast.LENGTH_SHORT);
                }

                setClickableButtons();
            }
        });

        return rootView;
    }

//  ------------------------------------------------------------------------------------------------

    private Item getItem(){
        return daoItem.getItem(id_item);
    }

    private void getLayoutElements(){
//      textView
        tab_item_name_title = rootView.findViewById(R.id.tab_item_name_title);

//      EditText
        tab_item_firm = rootView.findViewById(R.id.tab_item_firm);
        tab_item_category = rootView.findViewById(R.id.tab_item_category);
        tab_item_quantity = rootView.findViewById(R.id.tab_item_quantity);
        tab_item_price = rootView.findViewById(R.id.tab_item_price);
        tab_item_name = rootView.findViewById(R.id.tab_item_name);
    }

    private void setLayoutElements(){
//      TextView
        tab_item_name_title.setText(item.getName());

//      EditText
        tab_item_category.setText(item.getCategory());
        tab_item_firm.setText(item.getFirm());
        tab_item_quantity.setText(String.valueOf(item.getQuantity()));
        tab_item_price.setText(String.valueOf(item.getPrice()));
        tab_item_name.setText(item.getName());
    }

    private void getButtons(){
        button_edit = rootView.findViewById(R.id.tab_item_edit);
        button_update = rootView.findViewById(R.id.tab_item_update);
    }

    private void setClickableButtons(){
        TransitionManager.beginDelayedTransition((ViewGroup) rootView);

        if(button_update.getVisibility() == View.GONE) {
            button_update.setVisibility(View.VISIBLE);

            tab_item_category.setFocusable(true);
            tab_item_firm.setFocusable(true);
            tab_item_price.setFocusable(true);
            tab_item_name.setFocusable(true);

            tab_item_category.setFocusableInTouchMode(true);
            tab_item_firm.setFocusableInTouchMode(true);
            tab_item_price.setFocusableInTouchMode(true);
            tab_item_name.setFocusableInTouchMode(true);

        }else{
            button_update.setVisibility(View.GONE);

            tab_item_category.setFocusable(false);
            tab_item_firm.setFocusable(false);
            tab_item_quantity.setFocusable(false);
            tab_item_price.setFocusable(false);
            tab_item_name.setFocusable(false);
        }
    }

    private boolean updateItem(){
        Item item = new Item(
                Integer.valueOf(id_item),
                tab_item_name.getText().toString(),
                tab_item_firm.getText().toString(),
                Double.valueOf(tab_item_price.getText().toString()),
                tab_item_category.getText().toString(),
                Integer.valueOf(tab_item_quantity.getText().toString())
        );

        if(daoItem.updateItem(item)==1){
            return true;
        }else{
            return false;
        }
    }
}
