package com.example.urbankos.musicwarehouse.dao;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.example.urbankos.musicwarehouse.MainActivity;
import com.example.urbankos.musicwarehouse.objects.Item;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getContext;

public class ItemDAOTest {

    private ItemDAO itemDAO = new ItemDAO(InstrumentationRegistry.getTargetContext());

    @Test
    public void insertItem() {
        Item item = new Item("Heewaef5f", "Gieeaefffson", 23.23, "Gibson", 23);
        Assert.assertNotEquals(-1, itemDAO.insertItem(item));
    }

    @Test
    public void getCategoriesOfItemsInWarehouse() {
        Assert.assertNotEquals(0, itemDAO.getCategoriesOfItemsInWarehouse(String.valueOf(1)).size());
    }

    @Test
    public void getItemNameFirm() {
        Assert.assertNotEquals(0, itemDAO.getItemNameFirm("kategory", String.valueOf(1)).size());
    }

    @Test
    public void getItem() {
        Assert.assertNotEquals(null, itemDAO.getItem(String.valueOf(84)));
    }

    @Test
    public void updateItem() {
        Item item = new Item(5,"Heewaef5f", "Gieeaefffson", 23.23, "Gibson", 23);
        Assert.assertEquals(null, itemDAO.updateItem(item));
    }

    @Test
    public void getItems() {
        Assert.assertNotEquals(0, itemDAO.getItems().size());
    }
}