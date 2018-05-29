package com.example.urbankos.musicwarehouse.rest;

import com.example.urbankos.musicwarehouse.objects.Item;
import com.example.urbankos.musicwarehouse.objects.Warehouse;
import com.example.urbankos.musicwarehouse.objects.WarehouseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RestInterface {

    @GET("/warehouse")
    Call<List<Warehouse>> listWarehouses();

    @POST("/warehouse")
    Call<Warehouse> insertWarehouse(@Body Warehouse warehouse);

    @PUT("/warehouse")
    Call<Warehouse> updateWarehouse(@Body Warehouse warehouse);

    @GET("/item")
    Call<List<Item>> listItems();

    @POST("/item")
    Call<List<Item>> insertItems(@Body Item item);

    @PUT("/item")
    Call<Warehouse> updateItem(@Body Item item);

    @GET("/warehouseItem")
    Call<List<WarehouseItem>> listWarehouseItem();

    @POST("/warehouseItem")
    Call<List<Item>> insertWarehouseItem(@Body WarehouseItem warehouseItem);

    @PUT("/warehouseItem")
    Call<WarehouseItem> updateWarehouseItem(@Body WarehouseItem warehouseItem);
}
