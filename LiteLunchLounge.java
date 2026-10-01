import javax.swing.*;

public class LiteLunchLounge
{
    public static void main (String args[])
    {
        // Variables for counting ordered food items and free drinks.
        int dessert = 0, starter = 0, mainCourse = 0, validChoice = 0, item1 = 0, item2 = 0, item3 = 0, item4 = 0, item5 = 0, item6 = 0, item7 = 0, item8 = 0,
        freeSoftDrinks = 0, freeCoffees = 0;

        // Variables for storing the staff input and order summaries.
        String choice = "", nextOrderInput = "", drinks = "", food = "", summary = "";
        char nextOrder = 'N';

        // Repeat the ordering process until the staff chooses N.
        do
        {
            validChoice = 0;
            
            // Call displayMenu.
            choice = displayMenu(); 

            // End the program if the staff cancels the menu.
            if (choice == null) 
            {
                return;
            }

            // Process the selected menu item.
            switch (choice)
            {
                case "1":
                    item1++;
                    JOptionPane.showMessageDialog (null, "Coffee ordered.\nPrice: RM 1.80", "Order", JOptionPane.PLAIN_MESSAGE);
                    break;

                case "2":
                    item2++;
                    JOptionPane.showMessageDialog (null, "Soft Drink ordered.\nPrice: RM 2.00", "Order", JOptionPane.PLAIN_MESSAGE);
                    break;

                case "3":
                    item3++;
                    JOptionPane.showMessageDialog (null, "Dessert ordered.\nPrice: RM 3.50", "Order", JOptionPane.PLAIN_MESSAGE);
                    dessert++;
                    break;

                case "4":
                    item4++;
                    JOptionPane.showMessageDialog (null, "Starter ordered.\nPrice: RM 4.00", "Order", JOptionPane.PLAIN_MESSAGE);
                    starter++;
                    break;

                case "5":
                    item5++;
                    JOptionPane.showMessageDialog (null, "Main Course ordered.\nPrice: RM 8.00", "Order", JOptionPane.PLAIN_MESSAGE);
                    mainCourse++;
                    break;

                case "6":
                    item6++;
                    JOptionPane.showMessageDialog (null, "Main Course + Dessert ordered.\nPrice: RM 11.00", "Order", JOptionPane.PLAIN_MESSAGE);
                    mainCourse++;
                    dessert++;
                    break;

                case "7":
                    item7++;
                    JOptionPane.showMessageDialog (null, "Main Course + Starter ordered.\nPrice: RM 11.50", "Order", JOptionPane.PLAIN_MESSAGE);
                    mainCourse++;
                    starter++;
                    break;

                case "8":
                    item8++;
                    JOptionPane.showMessageDialog (null, "Combo (Main Course + Starter + Dessert) ordered.\nPrice: RM 15.00", "Order", JOptionPane.PLAIN_MESSAGE);
                    mainCourse++;
                    starter++;
                    dessert++;
                    break;

                 // Display an error message if the staff enter an invalid menu choice.
                default:
                    JOptionPane.showMessageDialog (null, "Invalid choice. Please choose between 1 to 8.", "Error", JOptionPane.ERROR_MESSAGE);
                    validChoice = 1;
                    break;
            }

            // Return to the menu if the menu choice was invalid.
            if (validChoice == 1)
            {
                nextOrder = 'Y';
                continue;
            }

            // Ask the staff whether the customer wants to order another item.
            nextOrderInput = JOptionPane.showInputDialog (null, "Do you want to order next item [Y/N]?", "Input", JOptionPane.PLAIN_MESSAGE);

            // Handle cancel, empty input and normal input.
            if (nextOrderInput == null)
            {
                nextOrder = 'N'; 
            }
            else if (nextOrderInput.length() == 0)
            {
                nextOrder = 'X'; 
            }
            else
            {
                nextOrder = nextOrderInput.toUpperCase().charAt(0);
            }

             // Validate the staff's Y/N input.
            while (nextOrder != 'Y' && nextOrder != 'N') 
            {
                JOptionPane.showMessageDialog (null, "Invalid input. Please enter Y for yes or N for no.", "Error", JOptionPane.ERROR_MESSAGE);
                nextOrderInput = JOptionPane.showInputDialog (null, "Do you want to order next item [Y/N]?", "Input", JOptionPane.PLAIN_MESSAGE);
                
                if (nextOrderInput == null)
                {
                    nextOrder = 'N';
                }
                else if (nextOrderInput.length() == 0)
                {
                    nextOrder = 'X'; 
                }
                else
                {
                    nextOrder = nextOrderInput.toUpperCase().charAt(0);
                }
            }

        } while (nextOrder == 'Y');

        // Call buildFoodSummary.
        food = buildFoodSummary (item3, item4, item5, item6, item7, item8); 

        // Calculate the free drinks based on the food combinations ordered.
        while (mainCourse > 0 && starter > 0 && dessert > 0) // Combo gives one free soft drink and one free coffee.
        {
            freeSoftDrinks++;
            freeCoffees++;
            mainCourse--;
            starter--;
            dessert--;
        }

        while (mainCourse > 0 && starter > 0) // Main course with starter gives one free soft drink.
        {
            freeSoftDrinks++;
            mainCourse--;
            starter--;
        }

        while (mainCourse > 0 && dessert > 0) // Main course with dessert gives one free coffee.
        {

            freeCoffees++;
            mainCourse--;
            dessert--;
        }

        // Call buildDrinkSummary.
        drinks = buildDrinkSummary (item1, item2, freeSoftDrinks, freeCoffees);

        // Create the final order summary.
        summary = " ~~~~~~~~~~~~ Order Summary ~~~~~~~~~~~~ \n";

        if (food.length() > 0 && drinks.length() > 0) 
        {
            summary += "Items to provide:\n[Food]\n" + food + "\n[Drinks]\n" + drinks + "\n";
        }
        else if (food.length() > 0 && drinks.length() == 0) 
        {
            summary += "Items to provide:\n[Food]\n" + food + "\n";
        }
        else if (food.length() == 0 && drinks.length() > 0) 
        {
            summary += "Items to provide:\n[Drinks]\n" + drinks + "\n";
        }
        else
        {
            summary += "No items or drinks ordered.";
        }

        // Display the final order summary for the staff.
        JOptionPane.showMessageDialog (null, summary, "Summary", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays the menu and asks the staff to enter the customer's choice.
     * @return the customer's selected menu item.
     */
    public static String displayMenu()
    {
        String menu = "_______________________________________________\n"
            + "| ~~~~~~~~~~~~~~   Lite Lunch Lounge   ~~~~~~~~~~~~~~ |\n"
            + "|______________________________________________|\n" 
            + "| [Item]                                                                           | [Price]      |\n" 
            + "|______________________________________________|\n"
            + "| 1. Coffee                                                                     | RM 1.80    |\n"    
            + "|______________________________________________|\n"
            + "| 2. Soft Drink                                                              | RM 2.00    |\n" 
            + "|______________________________________________|\n"
            + "| 3. Dessert                                                                  | RM 3.50    |\n" 
            + "|______________________________________________|\n"
            + "| 4. Starter                                                                   | RM 4.00    |\n" 
            + "|______________________________________________|\n"
            + "| 5. Main Course                                                         | RM 8.00    |\n" 
            + "|______________________________________________|\n"
            + "| 6. Main Course + Dessert                                      | RM 11.00 |\n" 
            + "|______________________________________________|\n"
            + "| 7. Main Course + Starter                                       | RM 11.50  |\n" 
            + "|______________________________________________|\n"
            + "| 8. Combo (Main Course + Starter + Dessert)   | RM 15.00  |\n" 
            + "|______________________________________________|\n\n"
            + "Enter customer's choice of item (1-8): ";

        return JOptionPane.showInputDialog (null, menu, "Menu", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Builds a summary of the food items ordered by the customer.
     * @param item3 number of desserts ordered.
     * @param item4 number of starters ordered.
     * @param item5 number of main courses ordered.
     * @param item6 number of main course and dessert combinations ordered.
     * @param item7 number of main course and starter combinations ordered.
     * @param item8 number of combo meals ordered.
     * @return a summary of the food items to be provided.
     */
    public static String buildFoodSummary (int item3, int item4, int item5, int item6, int item7, int item8)
    {
        String food = "";

        if (item3 > 0) 
        {
            food += item3 + "x Dessert\n";
        }

        if (item4 > 0) 
        {
            food += item4 + "x Starter\n";
        }

        if (item5 > 0) 
        {    
            food += item5 + "x Main Course\n";
        }

        if (item6 > 0) 
        {
            food += item6 + "x Main Course + Dessert\n";
        }

        if (item7 > 0) 
        {
            food += item7 + "x Main Course + Starter\n";
        }

        if (item8 > 0) 
        {
            food += item8 + "x Combo (Main Course + Starter + Dessert)\n";
        }

        return food;
    }

    /**
     * Builds a summary of the ordered drinks and free drinks.
     * @param item1 number of coffees ordered.
     * @param item2 number of soft drinks ordered.
     * @param freeSoftDrinks number of free soft drinks.
     * @param freeCoffees number of free coffees.
     * @return a summary of the drinks to be provided.
     */
    public static String buildDrinkSummary (int item1, int item2, int freeSoftDrinks, int freeCoffees)
    {
        String drinks = "";

        if (item1 > 0)
        {
            drinks += item1 + "x Coffee\n";
        }

        if (item2 > 0)
        {
            drinks += item2 + "x Soft Drink\n";
        }

        if (freeSoftDrinks > 0)
        {
            drinks += freeSoftDrinks + "x Free Soft Drink\n";
        }

        if (freeCoffees > 0)
        {
            drinks += freeCoffees + "x Free Coffee\n";
        }

        return drinks;
    }
}