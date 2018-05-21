package com.example.urbankos.musicwarehouse.database_sqlite;

public class DatabaseNames {

    public static class Entities{
        public static final String TABLE_WAREHOUSE = "warehouse";
        public static final String TABLE_ITEM = "item";
        public static final String TABLE_ITEM_WAREHOUSE = "warehouse_item";
    }

    public static class Attributes{
//      warehouse
        public static final String WAREHOUSE_ID = "id";
        public static final String WAREHOUSE_NAME = "name";
        public static final String WAREHOUSE_ADDRESS = "address";
        public static final String WAREHOUSE_TOWN = "town";
        public static final String WAREHOUSE_COUNTRY = "country";
        public static final String WAREHOUSE_CAPACITY = "capacity";

//      item
        public static final String ITEM_ID = "id";
        public static final String ITEM_NAME = "name";
        public static final String ITEM_FIRM = "firm";
        public static final String ITEM_PRICE = "price";
        public static final String ITEM_QUANTITY = "quantity";
        public static final String ITEM_CATEGORY = "category";

//      warehouse_item
        public static final String WAREHOUSE_ITEM_ID = "id";
        public static final String WAREHOUSE_ITEM_FK_ITEM = "fk_id_item";
        public static final String WAREHOUSE_ITEM_FK_WAREHOUSE = "fk_id_warehouse";
        public static final String WAREHOUSE_ITEM_QUANTITY = "quantity";
    }

    public static class Queries{
//      create
        public static final String CREATE_TABLE_WAREHOUSE = "create table "+Entities.TABLE_WAREHOUSE+" ("
                +Attributes.WAREHOUSE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Attributes.WAREHOUSE_NAME+" TEXT NOT NULL, "
                +Attributes.WAREHOUSE_ADDRESS+" TEXT NOT NULL, "
                +Attributes.WAREHOUSE_TOWN+" TEXT NOT NULL, "
                +Attributes.WAREHOUSE_COUNTRY+" TEXT NOT NULL, "
                +Attributes.WAREHOUSE_CAPACITY+" INTEGER NOT NULL, "
                +"CONSTRAINT unique_warehouse UNIQUE ("+Attributes.ITEM_NAME+", "+Attributes.WAREHOUSE_ADDRESS+"));";


        public static final String CREATE_TABLE_ITEM = "create table "+Entities.TABLE_ITEM+" ("
                +Attributes.ITEM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Attributes.ITEM_NAME+" TEXT NOT NULL, "
                +Attributes.ITEM_FIRM+" TEXT NOT NULL, "
                +Attributes.ITEM_CATEGORY+" TEXT NOT NULL, "
                +Attributes.ITEM_PRICE+" REAL NOT NULL, "
                +Attributes.ITEM_QUANTITY+" INTEGER NOT NULL, "
                + "CONSTRAINT unique_item UNIQUE ("+Attributes.ITEM_NAME+"));";


        public static final String CREATE_TABLE_ITEM_WAREHOUSE = "create table "+Entities.TABLE_ITEM_WAREHOUSE+" ("
                +Attributes.WAREHOUSE_ITEM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE+" INTEGER NOT NULL, "
                +Attributes.WAREHOUSE_ITEM_FK_ITEM+" INTEGER NOT NULL, "
                +Attributes.WAREHOUSE_ITEM_QUANTITY+" INTEGER NOT NULL, "
                +"FOREIGN KEY("+Attributes.WAREHOUSE_ITEM_FK_WAREHOUSE+") REFERENCES "
                +Entities.TABLE_WAREHOUSE+"("+Attributes.WAREHOUSE_ID+"), "
                +"FOREIGN KEY("+Attributes.WAREHOUSE_ITEM_FK_ITEM+") REFERENCES "
                +Entities.TABLE_ITEM+"("+Attributes.ITEM_ID+"));";

//      drop
        public static final String DROP_TABLE_WAREHOUSE = "drop table if exists "+Entities.TABLE_WAREHOUSE+";";
        public static final String DROP_TABLE_ITEM = "drop table if exists "+Entities.TABLE_ITEM+";";
        public static final String DROP_TABLE_ITEM_WAREHOUSE = "drop table if exists "+Entities.TABLE_ITEM_WAREHOUSE+";";

    }

}
