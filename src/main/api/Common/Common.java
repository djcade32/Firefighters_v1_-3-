package main.api.Common;

// This class contains all commonly used methods.

public class Common {

    // Calculates how far a city nodes are between each other
    public static int calculateDistance(int cityNode1X, int cityNode1Y, int cityNode2X, int cityNode2Y) {
        return Math.abs(cityNode1X - cityNode2X) + Math.abs(cityNode1Y - cityNode2Y);
    }
}
