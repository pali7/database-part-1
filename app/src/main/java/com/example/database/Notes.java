package com.example.database;

public class Notes {



    private int id;
    private String title;
    private String description;

    public   static final String TABLE_NAME="notes";

    public static final String COLUMN_ID="id";
    public static final String COLUMN_TITLE="title";
    public static final String COLUMN_DESCRIPTION="column_description";

    // query to create table
     public static final String CREATE_TABLE=
            " CREATE TABLE "+ TABLE_NAME+ "(" +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_TITLE+ " TEXT,"+
                    COLUMN_DESCRIPTION+ " TEXT "+")";
     public Notes(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Notes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
