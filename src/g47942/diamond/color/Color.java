package g47942.diamond.color;

/**
* This class allows writing a colored text
* 4/11/2104 - MCD
*/

public class Color {

    /**
     * Methode to color default of BASH.
     * @return the color default.
     */
    private static String toDefault() {
        return "\033[0m";
    }

    /**
     * Color a string to black.
     * @param a The string who we want to color.
     * @return The string in black.
     */
    public static String toBlack(String a) {
        return "\033[30m"+a+toDefault();
    }

     /**
     * Color a string to red.
     * @param a The string who we want to color.
     * @return The string in red.
     */
    public static String toRed(String a) {
        return "\033[31m"+a+toDefault();
    }

     /**
     * Color a string to green.
     * @param a The string who we want to color.
     * @return The string in green.
     */
    public static String toGreen(String a) {
        return "\033[32m"+a+toDefault();
    }

     /**
     * Color a string to yellow.
     * @param a The string who we want to color.
     * @return The string in yellow.
     */
    public static String toYellow(String a) {
        return "\033[33m"+a+toDefault();
    }

     /**
     * Color a string to blue.
     * @param a The string who we want to color.
     * @return The string in blue.
     */
    public static String toBlue(String a) {
        return "\033[34m"+a+toDefault();
    }

    /**
     * Color a string to purple.
     * @param a The string who we want to color.
     * @return The string in purple.
     */
    public static String toPurple(String a) {
        return "\033[35m"+a+toDefault();
    }

    /**
     * Color a string to cyan.
     * @param a The string who we want to color.
     * @return The string in cyan.
     */
    public static String toCyan(String a) {
        return "\033[36m"+a+toDefault();
    }

     /**
     * Color a string to white.
     * @param a The string who we want to color.
     * @return The string in white.
     */
    public static String toWhite(String a) {
        return "\033[37m"+a+toDefault();
    }
}
