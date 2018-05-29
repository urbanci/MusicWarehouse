package com.example.urbankos.musicwarehouse.dao;

import android.support.test.InstrumentationRegistry;

import com.example.urbankos.musicwarehouse.objects.WarehouseItem;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WarehouseItemDAOTest {

    WarehouseItemDAO warehouseItemDAO = new WarehouseItemDAO(InstrumentationRegistry.getTargetContext());

    @Test
    public void insertWarehouseItem() {
        ArrayList<WarehouseItem> warehouseItems = new ArrayList<>();

        for(int i=0; i<3; i++){
            warehouseItems.add(new WarehouseItem(i, 2, i+i*2));
        }

        Assert.assertTrue(warehouseItemDAO.insertWarehouseItem(warehouseItems, 2));
    }

    @Test
    public void insertWarehouseItemFull() {
        WarehouseItem item = new WarehouseItem(1, 2, 5*2);
        Assert.assertTrue(warehouseItemDAO.insertWarehouseItemFull(item));
    }

    @Test
    public void getQuantityesInWarehouses() {
        Assert.assertNotEquals(0, warehouseItemDAO.getQuantityesInWarehouses(String.valueOf(1)).size());
    }

    @Test
    public void updateWarehouseItem() {
        ArrayList<WarehouseItem> warehouseItems = new ArrayList<>();

        for(int i=0; i<3; i++){
            warehouseItems.add(new WarehouseItem(i, 2, i+i*2));
        }

        Assert.assertNotEquals(-1, warehouseItemDAO.updateWarehouseItem(warehouseItems));
    }

    @Test
    public void getWarehouseItems() {
        Assert.assertNotEquals(0, warehouseItemDAO.getWarehouseItems().size());
    }

    @Test
    public void updateWarehouseItemFull() {
        WarehouseItem item = new WarehouseItem(1, 2, 5*2);
        Assert.assertNotEquals(0, warehouseItemDAO.updateWarehouseItemFull(item));
    }
}