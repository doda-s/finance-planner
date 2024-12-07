package me.dodas.financeplanner.utils;

import java.util.Arrays;
import java.util.List;

public class TableFormatter {

    List<String> headers;
    List<String[]> rows;
    int[] columnWidths;

    public TableFormatter(List<String> headers, List<String[]> rows) {
        /* if (headers == null) {
            throw new IllegalArgumentException("Headers can't be null.");
        }

        if (rows == null) {
            throw new IllegalArgumentException("Rows can't be null.");
        } */
        this.headers = headers;
        this.rows = rows;
        columnWidths = calculateColumnWidths();
    }

    public boolean addColumn(String header) {
        if (header != null) {
            return headers.add(header);
        }
        return false;
    }

    public boolean addRow(String[] row) {
        if (row != null) {
            return rows.add(row);
        }
        return false;
    }

    public void printTable() {
        printSeparator();
        System.out.println(generateRow(headers));
        printSeparator();
        for (String[] row : rows) {
            System.out.println((generateRow(Arrays.asList(row))));
        }
        printSeparator();
    }

    public void printRow(int index, boolean printHeader) {
        if (printHeader) {
            printSeparator();
            System.out.println(generateRow(headers));
        }
        printSeparator();
        System.out.println(generateRow(Arrays.asList(rows.get(index))));
        printSeparator();
    }

    public String asString() {
        return null;
    }

    private int[] calculateColumnWidths() {
        int[] columnWidths = headers.stream()
                                    .mapToInt(String::length)
                                    .toArray();

        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                columnWidths[i] = Math.max(columnWidths[i], row[i].length());
            }
        }
        return columnWidths;
    }

    private String generateRow(List<String> row) {
        StringBuilder rowBuilder = new StringBuilder("|");
        for (int i = 0; i < row.size(); i++) {
            String format = " %-" + columnWidths[i] + "s |";
            rowBuilder.append(String.format(format, row.get(i)));
        }
        return rowBuilder.toString();
        
    }

    private void printSeparator() {
        StringBuilder separatorBuilder = new StringBuilder("+");
        for (int width : columnWidths) {
            separatorBuilder.append("-".repeat(width + 2)).append("+");
        }
        System.out.println(separatorBuilder.toString());
    }
}
