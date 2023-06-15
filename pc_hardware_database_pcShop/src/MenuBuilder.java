import java.util.ArrayList;

/**
 * To build menus for the Shop programme with the specified title, header, menu items, and prompt.
 * @author Sven Mayer
 * @version 1.0
 * @since 14.06.23
 */
public class MenuBuilder {

    /**
     * Builds and displays a menu with the specified title, header, menu items, and prompt.
     * @param menuTitle   The title of the menu.
     * @param menuHeader  The header text of the menu (optional).
     * @param menuItems   The list of menu items.
     * @param menuPrompt  The prompt text for user input.
     */
    public static void menuBuilder(String menuTitle, String menuHeader, ArrayList<String> menuItems, String menuPrompt){
        System.out.println(
                "-------------------------------------------------------\n" +
                "PC-Shop" + menuTitle + "\t\t\t" + "von: " + "Sven Mayer" +
                "\n-------------------------------------------------------");
        if(!menuHeader.isEmpty()){
            System.out.println(menuHeader);
        }
        for (int i = 0; i < menuItems.size(); i++){
            if(menuItems.size() > 4){
                if (i == menuItems.size() - 1) {
                    System.out.println(" 0) " + menuItems.get(i));
                } else {
                    System.out.println(" " + (i + 1) + ") " + menuItems.get(i));
                }
            }else{
                System.out.println(" " + (i + 1) + ") " + menuItems.get(i));
            }
        }
        System.out.println(menuPrompt);
    }
}