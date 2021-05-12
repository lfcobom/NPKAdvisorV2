package com.example.npkadvisorv2;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDynamic {

    private TableLayout tableLayout;
    private Context context;
    private String[]header;
    private ArrayList<String[]>data;
    private TableRow tableRow;
    private TextView txtCell;
    private int IndexC;
    private int IndexR;

    public TableDynamic(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }
    public void addHeader(String[]header){
        this.header = header;
        createHeader();
    }
    public void addData(ArrayList<String[]>data){
        this.data = data;
        CreateDataTable();
    }
    private void newRow (){
        tableRow = new TableRow(context);
    }
    private void newCell(){
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(25);
    }
    private void createHeader(){
        IndexC = 0;
        newRow();
        while (IndexC < header.length){
            newCell();
            txtCell.setText(header[IndexC++]);
            tableRow.addView(txtCell);
        }
        tableLayout.addView(tableRow);
    }
    private void CreateDataTable(){
        String info;
        for(IndexR = 1; IndexR <= header.length ; IndexR++){
            newRow();
            for (IndexC = 1; IndexC <= header.length; IndexC++ ){
                newCell();
                String[] row = data.get(IndexR-1);
                info = (IndexC < row.length)?row[IndexC]:"";
                txtCell.setText(info);
                tableRow.addView(txtCell, newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }
    public void addItems(String[]item){
        String info;
        data.add(item);
        IndexC = 0;
        newRow();
        while(IndexC < header.length){
            newCell();
            info = (IndexC < item.length)?item[IndexC++]:"";
            txtCell.setText(info);
            tableRow.addView(txtCell , newTableRowParams());
        }
        tableLayout.addView(tableRow,data.size()-1);
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight = 1;
        return params;
    }
}
