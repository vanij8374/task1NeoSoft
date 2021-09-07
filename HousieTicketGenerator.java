package vani;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HousieTicketGenerator {

    private static Map<Integer,Integer> rowCount = new HashMap<>();

    private static Map<Integer,Integer> columnCount = new HashMap<>();

    private static Set<Cell> currentCells = new HashSet<>();

    public static void main(String[] args) {
        int rows = 3;
        int columns=9;
        int maxCellsToBeFiledPerColumn = 2;
        int maxCellsToBeFiledPerRow = 5;
        int maxCellToBeFiled = 15;
        HousieTicketGenerator housie = new HousieTicketGenerator();
        int[][] ticket= housie.generateTicket(rows,columns,
                maxCellsToBeFiledPerColumn,maxCellsToBeFiledPerRow,
            maxCellToBeFiled);
        housie.printTicket(ticket);
    }

    private void printTicket(int[][] ticket){
        for (int[] row:ticket){
            for (int cell:row){
                if(cell == 0){
                    System.out.print(",");
                }else {
                    System.out.print(cell+",");
                }
            }
            System.out.println();
        }
    }

    public int[][] generateTicket(int rows,int columns,int maxCellsToBeFiledPerColumn,int maxCellsToBeFiledPerRow,int maxCellToBeFiled){
        int[][] ticket = generateCells(rows,columns);
        for(int i=0;i<maxCellToBeFiled;i++){
           Cell cell = getRandomCellToAssign();
           assignCellValue(ticket,cell);
           currentCells.remove(cell);
           incrementCounts(cell);
           removeColumns(cell,maxCellsToBeFiledPerColumn);
           removeRows(cell,maxCellsToBeFiledPerRow);
        }
        return ticket;
    }

    private int[][] generateCells(int rows,int columns){
        for (int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                currentCells.add(new Cell(i+1,j+1));
            }
        }
        return new int[rows][columns];
    }

    private void assignCellValue(int[][] ticket,Cell cell){
        int value = generateRandomCellValue(cell);
        while (columnContainsValue(ticket,cell.columnNumber-1,value)){
            value = generateRandomCellValue(cell);
        }
        ticket[cell.rowNumber-1][cell.columnNumber-1] = value;
    }
    private boolean columnContainsValue(int[][] ticket,int column,int value){
        for (int[] row:ticket){
            if(value==row[column]){
                return true;
            }
        }
        return false;
    }
    private int generateRandomCellValue(Cell cell){
        return (cell.columnNumber-1) * 10 + new Random().nextInt(10) + 1 ;
    }

    private void incrementCounts(Cell cell){
        Integer colCount = columnCount.computeIfAbsent(cell.columnNumber,v->0);
        columnCount.put(cell.columnNumber,++colCount);
        Integer rCount = rowCount.computeIfAbsent(cell.rowNumber,v->0);
        rowCount.put(cell.rowNumber,++rCount);
    }

    private void removeColumns(Cell cell,int maxNumberPerColumn){
        if(columnCount.get(cell.columnNumber) > maxNumberPerColumn-1) {
            currentCells = currentCells.stream()
                    .filter(c -> c.columnNumber != cell.columnNumber)
                    .collect(Collectors.toSet());
        }
    }

    private void removeRows(Cell cell,int maxNumberPerRow){
        if(rowCount.get(cell.rowNumber) > maxNumberPerRow-1) {
            currentCells = currentCells.stream()
                    .filter(c -> c.rowNumber != cell.rowNumber)
                    .collect(Collectors.toSet());
        }
    }

    private Cell getRandomCellToAssign(){
        Random random = new Random();
        int randomInt = random.nextInt(currentCells.size())+1;
        int count=1;
        Cell result = null;
        for(Cell cell:currentCells){
            if (randomInt == count){
                result = cell;
                break;
            }
            count++;
        }
        return result;
    }

    private static class Cell{
        public Cell(int rowNumber,int columnNumber){
            this.columnNumber = columnNumber;
            this.rowNumber = rowNumber;
        }
        private int rowNumber;
        private int columnNumber;

        public int getRowNumber() {
            return rowNumber;
        }

        public void setRowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        public int getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(int columnNumber) {
            this.columnNumber = columnNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return rowNumber == cell.rowNumber &&
                    columnNumber == cell.columnNumber;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowNumber, columnNumber);
        }
    }
}
