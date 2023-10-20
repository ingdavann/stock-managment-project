package com.stock;

import com.utils.Utils;
import jdk.jshell.execution.Util;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockManage {
    private Integer numRow= 3;
    private Integer numPage = 1;
    private List<Products> products = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    public void initialize(){
        products.add( new Products( 1, "Coca", 2000.00, 2, LocalDate.now() ) );
        products.add( new Products( 2, "Wurk", 2500.00, 3, LocalDate.now() ) );
        products.add( new Products( 3, "Pen", 1000.00, 5, LocalDate.now() ) );
        products.add( new Products( 4, "Note Book", 2000.00, 5, LocalDate.now() ) );
        products.add( new Products( 5, "Phone Case", 4000.00, 1, LocalDate.now() ) );
        products.add( new Products( 5, "Head Set", 5000.00, 2, LocalDate.now() ) );
        Utils.displayTitle();
        option_loop: while(true){
            Utils.displayMainMenu();
            System.out.print("Command > ");
            String option = input.next();
            switch (option){
                case "*" -> displayItem();
                case "W", "w" -> writeItem();
                case "r", "R" -> readItemById();
                case "s", "S" -> searchByName();
                case "d", "D" -> deleteItemById();
                case "U", "u" -> updateItemOption();
                case "h", "H"->Utils.displayHelp();
                case "Se", "se", "SE", "sE" -> setRow();
                case "F", "f" -> goFirstPage();
                case "L", "l" -> goLastPage();
                case "N", "n" -> nextPage();
                case "P", "p" -> previousPage();
                case "go", "GO", "Go", "gO" -> goSpecialPage();
                case "E", "e" ->{
//                    System.out.println("=".repeat(10) + "Thank You!" + "=".repeat(10));
                    System.out.println("\n" +
                            " +-++-++-++-++-+ +-++-++-++-+ +-++-++-++-+ +-++-++-+\n" +
                            " |T||h||a||n||k| |y||o||u||,| |G||o||o||d| |B||y||e|\n" +
                            " +-++-++-++-++-+ +-++-++-++-+ +-++-++-++-+ +-++-++-+\n");
                    break option_loop;
                }
                default -> {
                    String[] shortCutKey = option.split("#");
                    if(shortCutKey.length < 2){
                        Utils.displayMessage("Invalided Option");
                    }
                    else{
                        switch (shortCutKey[0]){
                            case "w", "W" ->{
                                if( shortCutKey[1].split("-").length < 3 ){
                                    Utils.displayMessage("Invalided Option");
                                }
                                else{
                                    try{
                                        Products productShortCut = new Products();
                                        String[] productDataShortCut = shortCutKey[1].split("-");
                                        productShortCut.setId(products.get(products.size()-1).getId() + 1);
                                        productShortCut.setName( productDataShortCut[0] );
                                        productShortCut.setPrice(Double.parseDouble( productDataShortCut[1]));
                                        productShortCut.setQty(Integer.parseInt( productDataShortCut[2] ));
                                        productShortCut.setTimeDate(LocalDate.now());
                                        writeItemByShortCutKey(productShortCut);
                                    }catch (Exception ex){
                                        Utils.displayMessage("Wrong ShortCut, Please check in shortcut help!");
                                    }
                                }
                            }
                            case "r", "R" ->{
                                try{
                                    int idShortCut = Integer.parseInt(shortCutKey[1]);
                                    readItemByIdShortCutKey(idShortCut);
                                }catch (Exception e){
                                    Utils.displayMessage("ID must be number");
                                }
                            }
                            case "d", "D" -> {
                                try{
                                    int idDelete = Integer.parseInt( shortCutKey[1] );
                                    deleteItemByIdShortCutKey(idDelete);
                                }catch (Exception ex){
                                    Utils.displayMessage("Wrong ShortCut, Please check in shortcut help!");
                                }
                            }
                            default -> {
                                Utils.displayMessage("Wrong ShortCut, Please check in shortcut help!");
                            }
                        }
                    }

                }
            }

        }
    }
    private void displayItem(){
        if( products.isEmpty() ) {
            Utils.displayMessage("No product");
            System.out.println();
            Table tableStyle = new Table(1, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
            tableStyle.addCell( String.format("Page : %s of %s", numPage, ((int )Math.ceil( (float) products.size() / numRow ) == 0 ) ? 1 : (int )Math.ceil( (float) products.size() / numRow ) ) );
            tableStyle.addCell( String.format("Display number record : %s", numRow ));
            System.out.println( tableStyle.render() );
        }
        else {
            Table table = new Table(5, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Unit Price");
            table.addCell("Qty");
            table.addCell("Local Date");

//            products.forEach(products -> {
//                table.addCell(products.getId().toString());
//                table.addCell(products.getName());
//                table.addCell(products.getPrice().toString());
//                table.addCell(products.getQty().toString());
//                table.addCell(products.getTimeDate().toString());
//            });
//            System.out.println(table.render());
            int startIndex = (numPage - 1) * numRow;
            int endIndex = (numRow * numPage) < products.size() ? numRow * numPage : products.size();
            products.subList( startIndex, endIndex ).forEach(products -> {
                table.addCell(products.getId().toString());
                table.addCell(products.getName());
                table.addCell(products.getPrice().toString());
                table.addCell(products.getQty().toString());
                table.addCell(products.getTimeDate().toString());
            });
//            int endIndex = Math.min(numPage * numRow, products.size());
//
//            for (int i = startIndex; i < endIndex; i++) {
//                Product product = products.get(i);
//
//            }

            Table tableStyle = new Table(1, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
            tableStyle.addCell(String.format("Page : %s of %s", numPage, (int) Math.ceil((float) products.size() / numRow)));
            tableStyle.addCell(String.format("Display number record : %s", numRow));
            System.out.println(table.render());
            System.out.println();
            System.out.println(tableStyle.render());
        }
    }
    private void writeItem(){
        try {
            Products item = new Products();
            System.out.print("Product ID : ");
            item.setId( Integer.parseInt( input.next() ) );
            System.out.print("Product's Name : ");
            item.setName( input.next() );
            System.out.print("Product's price : ");
            item.setPrice( Double.parseDouble( input.next() ) );
            System.out.print("Product Qty : ");
            item.setQty( Integer.parseInt( input.next() ) );
            item.setTimeDate( LocalDate.now() );
            Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
            table.addCell( "ID              : " + item.getId() );
            table.addCell( "Name            : " + item.getName() );
            table.addCell( "Unit Price      : " + item.getPrice() );
            table.addCell( "Qty             : " + item.getQty() );
            table.addCell( "Imported Date   : " + item.getTimeDate() );
            System.out.println( table.render() );
            System.out.print("Are you sure to add this product Yes(y,Y) | No(n, N) : ");
            char comfirm = input.next().charAt(0);
            if( comfirm == 'Y' || comfirm == 'y' ) {
                products.add(item);
                Utils.displayMessage("Added Successfully!");
            }
            else Utils.displayMessage("Canceled to add product");
        }catch (Exception e){
            Utils.displayMessage("Something gone wrong!");
        }
    }
    private void writeItemByShortCutKey( Products product ){
        Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
        table.addCell( "ID              : " + product.getId() );
        table.addCell( "Name            : " + product.getName() );
        table.addCell( "Unit Price      : " + product.getPrice() );
        table.addCell( "Qty             : " + product.getQty() );
        table.addCell( "Local Date   : " + product.getTimeDate() );
        System.out.println( table.render() );
        System.out.print("Are you sure to add this product Yes(y,Y) | No(n, N) : ");
        char cof = input.next().charAt(0);
        if( cof == 'Y' || cof == 'y' ) {
            products.add(product);
            Utils.displayMessage("Added Successfully!");
        }
    }
    private void searchByName(){
        System.out.print("Enter name to search  : ");
        List<Products> searchResults = new ArrayList<>();
        String searchName = input.next().toLowerCase();
        products.forEach( product -> {
            if( product.getName().toLowerCase().contains( searchName ) ){
                searchResults.add( product );
            }
        } );

        if( searchResults.isEmpty() ){
            Utils.displayMessage("Product not found!");
        }
        else{
            Table table = new Table( 5, BorderStyle.UNICODE_BOX );
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Unit Price");
            table.addCell("Qty");
            table.addCell("Imported Date");
            searchResults.forEach(product -> {
                table.addCell( product.getId().toString() );
                table.addCell( product.getName() );
                table.addCell( product.getPrice().toString() );
                table.addCell( product.getQty().toString() );
                table.addCell( product.getTimeDate().toString() );
            });
            System.out.println("\t\tSearch results");
            System.out.println( table.render() );
            System.out.println();
        }

    }
    private void deleteItemById() {
        System.out.print("Enter product ID : ");
        Integer searchId = Integer.parseInt(input.next());
        boolean isFound = false;
        for (Products product : products) {
            if (product.getId().equals(searchId)) {
                Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                table.addCell("ID              : " + product.getId());
                table.addCell("Name            : " + product.getName());
                table.addCell("Unit Price      : " + product.getPrice());
                table.addCell("Qty             : " + product.getQty());
                table.addCell("Imported Date   : " + product.getTimeDate());
                System.out.println(table.render());
                isFound = true;
                System.out.print("Are you sure to delete this product Yes(y,Y) | No(n, N) : ");
                char comfirm = input.next().charAt(0);
                if( comfirm == 'Y' || comfirm == 'y' ) {
                    products.remove( product );
                    Utils.displayMessage("Product deleted successfully!");
                }
                else Utils.displayMessage("Canceled to delete product");
                break;
            } else isFound = false;
        }

        if (!isFound){
            Utils.displayMessage("Product not found!");
        }
    }
    private void deleteItemByIdShortCutKey(int searchIdToDelete){
        boolean isFound = false;
        outer_loop:
        for (Products product : products) {
            if (product.getId().equals(searchIdToDelete)) {
                Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                table.addCell("ID              : " + product.getId());
                table.addCell("Name            : " + product.getName());
                table.addCell("Unit Price      : " + product.getPrice());
                table.addCell("Qty             : " + product.getQty());
                table.addCell("Local Date   : " + product.getTimeDate());
                System.out.println(table.render());
                isFound = true;
                System.out.print("Are you sure to delete this product Yes(y,Y) | No(n, N) : ");
                char o = input.next().charAt(0);
                if( o == 'Y' || o == 'y' ) {
                    products.remove( product );
                    Utils.displayMessage("Product deleted successfully!");
                }
                else Utils.displayMessage("Canceled to delete product");
                break;
            } else isFound = false;
        }
        if (!isFound) Utils.displayMessage("Product not found!");
    }
    private void updateItemOption(){
        System.out.print("Enter product ID : ");
        Integer delId = Integer.parseInt(input.next());
        Products newProduct = new Products();
        boolean isFound = false;
        for( Products product : products ){
            if( product.getId().equals(delId) ){
                Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                table.addCell( "ID              : " + product.getId() );
                table.addCell( "Name            : " + product.getName() );
                table.addCell( "Unit Price      : " + product.getPrice() );
                table.addCell( "Qty             : " + product.getQty() );
                table.addCell( "Local Date      : " + product.getTimeDate() );
                System.out.println( table.render());
                isFound = true;
                newProduct = product;
                break;
            }else isFound = false;
        }

        if( !isFound ){
            Utils.displayMessage("Product not found");
        }
        else{
            displayUpdateMenu();
            int index = products.indexOf( newProduct );
            main_loop : while ( true ) {
                try{
                    System.out.print("Choose option update : ");
                    Integer optionUpdate = Integer.parseInt(input.next());
                    switch (optionUpdate) {
                        case 1 -> {

                            System.out.print("Enter new product name : ");
                            String updateProductName = input.next();
                            newProduct.setName(updateProductName);

                            System.out.print("Enter new product qty : ");
                            Integer updateProductQty = Integer.parseInt(input.next());
                            newProduct.setQty(updateProductQty);

                            System.out.print("Enter new product unit price : ");
                            Double updateProductUnitPrice = Double.parseDouble(input.next());
                            newProduct.setPrice(updateProductUnitPrice);
                            updateItem(index, newProduct);
                            break main_loop;
                        }
                        case 2 -> {
                            System.out.print("Enter new product name : ");
                            String updateProductName = input.next();
                            newProduct.setName(updateProductName);
                            updateItem(index, newProduct);
                            break main_loop;
                        }
                        case 3 -> {
                            System.out.print("Enter new product Qty : ");
                            Integer UpdateProductQty = Integer.parseInt(input.next());
                            newProduct.setQty(UpdateProductQty);
                            updateItem(index, newProduct);
                            break main_loop;
                        }
                        case 4 -> {
                            System.out.print("Enter new product unit price : ");
                            Double updateProductUnitPrice = Double.parseDouble(input.next());
                            newProduct.setPrice(updateProductUnitPrice);
                            updateItem(index, newProduct);
                            break main_loop;
                        }
                        default -> Utils.displayMessage("Please choose number match in lists!");
                    }
                }catch (Exception e){
                    Utils.displayMessage("Please enter option by number only");
                }
            }

        }

    }
    private void updateItem( int index, Products newProduct ){
        products.set(index, newProduct );
        Utils.displayMessage("Updated successfully");
    }
    public static void displayUpdateMenu(){
        System.out.println();
        Table table = new Table( 4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        table.addCell( " ".repeat(5) + "1. ALL" + " ".repeat(5) );
        table.addCell( " ".repeat(5) + "2. Name" + " ".repeat(5) );
        table.addCell( " ".repeat(5) + "3. Qty" + " ".repeat(5) );
        table.addCell( " ".repeat(5) + "4. Price" + " ".repeat(5) );
        System.out.println(table.render());
        System.out.println();
    }
    private void readItemById(){
        while (true){
            try{
                System.out.print("Enter product ID : ");
                int searchId = Integer.parseInt(input.next());
                Products searchItem = null;
                for ( Products product : products ){
                    if( product.getId().equals(searchId) ){
                        searchItem = product;
                    }
                }
                if( searchItem == null ){
                    Utils.displayMessage("Product not found!");
                }
                else{
                    Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                    table.addCell( "ID              : " + searchItem.getId() );
                    table.addCell( "Name            : " + searchItem.getName() );
                    table.addCell( "Unit Price      : " + searchItem.getPrice() );
                    table.addCell( "Qty             : " + searchItem.getQty() );
                    table.addCell( "Local Date   : " + searchItem.getTimeDate() );
                    System.out.println( table.render() );
                }
                break;
            }catch (Exception e){
                Utils.displayMessage("Please enter only number");
            }
        }

    }
    private void readItemByIdShortCutKey( int idShortCut ){
        boolean isFound = false;
        label_loop: for( Products product : products ){
            if( product.getId().equals(idShortCut) ){
                Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                table.addCell( "ID              : " + product.getId() );
                table.addCell( "Name            : " + product.getName() );
                table.addCell( "Unit Price      : " + product.getPrice() );
                table.addCell( "Qty             : " + product.getQty() );
                table.addCell( "Local Date      : " + product.getTimeDate() );
                System.out.println( table.render() );
                isFound = true;
                break label_loop;
            }
        }
        if( !isFound )  Utils.displayMessage("Product not found!");
    }
    private void setRow(){
        while (true){
            try{
                System.out.print("Enter number of rows you want to Display : ");
                int numRowNew = Integer.parseInt( input.next());
                numRow = numRowNew;
                numPage = 1;
                Utils.displayMessage("Updated row successfully!");
                break;
            }catch ( Exception e){
                Utils.displayMessage("Please enter only number!");
            }
        }
    }
    private int getNumPage(){
        if(( ( (int )Math.ceil( (float) products.size() / numRow )  ) == 0 )){
            return 1;
        }else{
            return ( (int )Math.ceil( (float) products.size() / numRow )  );
        }
    }
    private void goFirstPage(){
        numPage = 1;
        displayItem();
    }
    private void goLastPage(){
        numPage = getNumPage();
       displayItem();
    }
    private void nextPage(){
        if( numPage < getNumPage() ){
            numPage ++;
            displayItem();
        }else{
            displayItem();
            Utils.displayMessage("======= You stand on the last page! =======");
        }
    }
    private void previousPage(){
        if( numPage > 1 ){
            numPage --;
            displayItem();
        }else{
            displayItem();
            Utils.displayMessage("======= You stand on the first page! =======");
        }
    }
    private void goSpecialPage(){
        while( true ){
            try{
                System.out.print("Enter page you want to go : ");
                int goPage = Integer.parseInt( input.next());
                if( goPage <= getNumPage() ){
                    numPage = goPage;
                    displayItem();
                }
                else{
                    Utils.displayMessage( String.format("Page not found! Page has %s", getNumPage() ) );
                }
                break;
            }catch (Exception e){
                Utils.displayMessage("====== Please enter only number! ======");
            }
        }
    }
}
