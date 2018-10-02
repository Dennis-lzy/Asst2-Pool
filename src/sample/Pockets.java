package sample;


import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Pockets extends ArrayList<Circle> {

    private double r = 10;

    public ArrayList<Circle> pockets;


    public void setPockets(Table table) {
        double tWidth = table.bounds.getWidth();
        double tHeight = table.bounds.getHeight();
        double top = table.bounds.getMinY();
        double right = table.bounds.getMaxX();
        double bot = table.bounds.getMaxY();
        double left = table.bounds.getMinX();

        Circle topLeft = new Circle(left,top,r);
        Circle topMid = new Circle(left+0.5*tWidth,top,r);
        Circle topRight = new Circle(right,top,r);
        Circle botLeft = new Circle(left,bot,r);
        Circle botMid = new Circle(left+0.5*tWidth,bot,r);
        Circle botRight = new Circle(right,bot,r);

        pockets.add(topLeft);
        pockets.add(topMid);
        pockets.add(topRight);
        pockets.add(botLeft);
        pockets.add(botMid);
        pockets.add(botRight);

    }

    public ArrayList<Circle> getPockets() {
        return pockets;
    }
}
