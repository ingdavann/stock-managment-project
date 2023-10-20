package com.utils;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class Utils {
    public static void displayTitle(){
        System.out.println(
                """
                         ██████╗███████ ████████╗ █████╗  ██████╗         ██╗ █████╗ ██╗   ██╗ █████╗\s
                        ██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗
                        ██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║
                        ██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║
                        ╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║
                        ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝
                        """
        );
        System.out.println("Stock Management System".toUpperCase());
    }

    public static void displayMainMenu(){
        System.out.println();
        Table table = new Table( 7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        table.addCell( " ".repeat(5) + "*)Display" + " ".repeat(5) );
        table.addCell(" ".repeat(5) + "W)rite" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "R)ead" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "U)pdate" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "D)elete" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "F)irst" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "P)revios" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "N)ext" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "L)ast" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "S)earch" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "G)oto" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "Se)t row" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "H)elp" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "E)xit" + " ".repeat(5));
        System.out.println(table.render());
        System.out.println();
    }

    public static void displayHelp(){
        System.out.println();
        Table table = new Table( 1, BorderStyle.DESIGN_FORMAL_INVERSE, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        table.addCell( "=".repeat(25) + " ShortCut Help " + "=".repeat(25));
        table.addCell("1.\tPress\t* : Display all record of products");
        table.addCell("2.\tPress\tw : Add new product");
        table.addCell("\tPress\tw -->#ProductName-UnitPrice-Qty : shortcut for add new product");
        table.addCell("3.\tPress\tr : read Content any content");
        table.addCell("\tPress\tr#ProductID : Shortcut for read product by Id" );
        table.addCell("4.\tPress\tU : Update Data");
        table.addCell("5.\tPress\tD : Delete Data");
        table.addCell("\tPress d#ProductID : shortcut for delete product by Id");
        table.addCell("6.\tPress\tF : Display First page");
        table.addCell("7.\tPress\tL : Display Last page");
        table.addCell("8.\tPress\tP : Display Previous page");
        table.addCell("9.\tPress\tN : Display Next page");
        table.addCell("10.\tPress\tS : Search product by name");
        table.addCell("11.\tPress\tH : Help");
        table.addCell("12\tPress\tSe : Set Row");
        table.addCell("13\tPress\tGo : Go to one page");
        System.out.println(table.render());
        System.out.println();
    }
    public static void displayMessage( String message ){
        System.out.println();
        Table table = new Table(1, BorderStyle.DOTS);
        table.addCell( " ".repeat(5) + message + " ".repeat(5)  );
        System.out.println(table.render());
        System.out.println();
    }

}
