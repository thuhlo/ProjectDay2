package com.expleo.qe;

public class DataModel {
    String database, table, keyColumn, keyValue, column, value, message;

    public DataModel(String database, String table, String keyColumn, String keyValue,
                     String column, String value, String message) {
        setDatabase(database);
        setTable(table);
        setKeyColumn(keyColumn);
        setKeyValue(keyValue);
        setColumn(column);
        setValue(value);
        setMessage(message);
    }

    public DataModel(String database, String table, String keyColumn, String keyValue,
                     String column, String value) {
        //this.database = database;
        setDatabase(database);
        //this.table = table;
        setTable(table);
        //this.keyColumn = keyColumn;
        setKeyColumn(keyColumn);
        //this.keyValue = keyValue;
        //this.column = column;
        setColumn(column);
        //this.value = value;
        setValue(value);
        setKeyValue(keyValue);
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        if (database.isEmpty()) {
            this.database = null;
            //System.out.println("Database values ");
        } else {
            this.database = database;
        }
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        if (table.isEmpty()) {
            this.table = null;
        } else {
            this.table = table;
        }
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        if (keyColumn.isEmpty()) {
            this.keyColumn = null;
        } else {
            this.keyColumn = keyColumn;
        }

    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        if (keyValue.isEmpty()) {
            this.keyValue = null;
        } else {
            this.keyValue = keyValue;
        }

    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        if (column.isEmpty()) {
            this.column = null;
        } else {
            this.column = column;
        }

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value.isEmpty()) {
            this.value = null;
        } else {
            this.value = value;
        }

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message == null) {
            this.message = "";
        } else {
            this.message = message;
        }
    }
}
