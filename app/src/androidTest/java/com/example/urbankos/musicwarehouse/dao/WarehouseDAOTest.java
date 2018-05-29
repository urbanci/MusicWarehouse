package com.example.urbankos.musicwarehouse.dao;

import android.support.test.InstrumentationRegistry;

import com.example.urbankos.musicwarehouse.objects.Warehouse;

import org.junit.Assert;
import org.junit.Test;

public class WarehouseDAOTest {
    private WarehouseDAO warehouseDAO = new WarehouseDAO(InstrumentationRegistry.getTargetContext());

    @Test
    public void insertWarehouse() {
        Warehouse w = new Warehouse("warehouse 1", "Naslov 1", "Maribor", "Slovenia", 34);
        Assert.assertTrue(warehouseDAO.insertWarehouse(w));
    }

    @Test
    public void getAllWarehousesRecycler() {
        Assert.assertNotEquals(0, warehouseDAO.getAllWarehousesRecycler().size());
    }

    @Test
    public void updateWarehouse() {
        Warehouse w = new Warehouse("warehouse 1", "Naslov 1", "Maribor", "Slovenia", 52);
        Assert.assertEquals(1, warehouseDAO.updateWarehouse(w));
    }
}