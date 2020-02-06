import java.awt.Color;

import org.piccolo2d.activities.PActivity;
import org.piccolo2d.nodes.PText;

public class SortingAnimation extends AnimationScreen {
    private static final long serialVersionUID = 1L;

    private static PText header;
    private static TextBoxNode[] myTextBoxes = new TextBoxNode[7];

    @Override
    public void addInitialNodes() {
        // add background box
        addColouredBox(0, 0, 400, 400, Color.WHITE);

        // add header text
        header = addText(0, 0, "This is a quick sort!");
        header.setTextPaint(Color.DARK_GRAY);

        // put in a text box (part of an array or list?)
        myTextBoxes[0] = addTextBox(-50, -50, 30, 30, "13");
        myTextBoxes[0].setPaint(Color.LIGHT_GRAY);
        myTextBoxes[0].setTextPaint(Color.BLACK);

        myTextBoxes[1] = addTextBox(-50, -50, 30, 30, "42");
        myTextBoxes[1].setPaint(Color.LIGHT_GRAY);
        myTextBoxes[1].setTextPaint(Color.BLACK);

        myTextBoxes[2] = addTextBox(-50, -50, 30, 30, "69");
        myTextBoxes[2].setPaint(Color.LIGHT_GRAY);
        myTextBoxes[2].setTextPaint(Color.BLACK);

        myTextBoxes[3] = addTextBox(-50, -50, 30, 30, "96");
        myTextBoxes[3].setPaint(Color.LIGHT_GRAY);
        myTextBoxes[3].setTextPaint(Color.BLACK);

        myTextBoxes[4] = addTextBox(-50, -50, 30, 30, "7");
        myTextBoxes[4].setPaint(Color.LIGHT_GRAY);
        myTextBoxes[4].setTextPaint(Color.BLACK);

        myTextBoxes[5] = addTextBox(-50, -50, 30, 30, "83");
        myTextBoxes[5].setPaint(Color.LIGHT_GRAY);
        myTextBoxes[5].setTextPaint(Color.BLACK);

        myTextBoxes[6] = addTextBox(-50, -50, 30, 30, "24");
        myTextBoxes[6].setPaint(Color.LIGHT_GRAY);
        myTextBoxes[6].setTextPaint(Color.BLACK);
    }

    public static void main(String[] args) {
        SortingAnimation screen = new SortingAnimation();

        // wait for initialization before animating
        screen.waitForInitialization();

        // perform animation steps...
        TextBoxNode temp;

        // Initial array is presented
        myTextBoxes[0].animateToPositionScaleRotation(100, 150, 1.5, 0, 3000);
        myTextBoxes[1].animateToPositionScaleRotation(145, 150, 1.5, 0, 3000);
        myTextBoxes[2].animateToPositionScaleRotation(190, 150, 1.5, 0, 3000);
        myTextBoxes[3].animateToPositionScaleRotation(235, 150, 1.5, 0, 3000);
        myTextBoxes[4].animateToPositionScaleRotation(280, 150, 1.5, 0, 3000);
        myTextBoxes[5].animateToPositionScaleRotation(325, 150, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[6].animateToPositionScaleRotation(370, 150, 1.5, 0, 3000));

        // Show Pivot, lo and hi
        waitForActivity(myTextBoxes[6].animateToColor(Color.RED, 4000));
        waitForActivity(myTextBoxes[5].animateToColor(Color.CYAN, 4000));
        waitForActivity(myTextBoxes[0].animateToColor(Color.YELLOW, 4000));

        // Find correct lo
        myTextBoxes[0].animateToColor(Color.LIGHT_GRAY, 4000);
        waitForActivity(myTextBoxes[1].animateToColor(Color.YELLOW, 4000));

        // Find correct hi
        myTextBoxes[5].animateToColor(Color.LIGHT_GRAY, 4000);
        waitForActivity(myTextBoxes[4].animateToColor(Color.CYAN, 4000));

        // drop lo and hi
        myTextBoxes[1].animateToPositionScaleRotation(145, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[4].animateToPositionScaleRotation(280, 200, 1.5, 0, 3000));

        // swap lo and hi
        myTextBoxes[1].animateToColor(Color.CYAN, 3000);
        myTextBoxes[4].animateToColor(Color.YELLOW, 3000);
        myTextBoxes[1].animateToPositionScaleRotation(280, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[4].animateToPositionScaleRotation(145, 200, 1.5, 0, 3000));

        // swap values
        temp = myTextBoxes[1];
        myTextBoxes[1] = myTextBoxes[4];
        myTextBoxes[4] = temp;

        // raise lo and hi
        myTextBoxes[4].animateToPositionScaleRotation(280, 150, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[1].animateToPositionScaleRotation(145, 150, 1.5, 0, 3000));

        // Find correct lo
        myTextBoxes[1].animateToColor(Color.LIGHT_GRAY, 4000);
        waitForActivity(myTextBoxes[2].animateToColor(Color.YELLOW, 4000));

        // Find correct hi
        myTextBoxes[4].animateToColor(Color.LIGHT_GRAY, 4000);
        waitForActivity(myTextBoxes[3].animateToColor(Color.CYAN, 4000));
        waitForActivity(myTextBoxes[3].animateToColor(Color.LIGHT_GRAY, 4000));

        // drop pivot and lo
        myTextBoxes[6].animateToPositionScaleRotation(370, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[2].animateToPositionScaleRotation(190, 200, 1.5, 0, 3000));

        // swap lo and pivot
        myTextBoxes[6].animateToColor(Color.GREEN, 3000);
        myTextBoxes[2].animateToColor(Color.LIGHT_GRAY, 3000);
        myTextBoxes[6].animateToPositionScaleRotation(190, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[2].animateToPositionScaleRotation(370, 200, 1.5, 0, 3000));

        // swap values
        temp = myTextBoxes[2];
        myTextBoxes[2] = myTextBoxes[6];
        myTextBoxes[6] = temp;

        // raise lo and pivot
        myTextBoxes[2].animateToPositionScaleRotation(190, 150, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[6].animateToPositionScaleRotation(370, 150, 1.5, 0, 3000));

        //
        // 1 element sorted
        //

        // Show Pivot, lo and hi
        waitForActivity(myTextBoxes[1].animateToColor(Color.RED, 4000));
        waitForActivity(myTextBoxes[0].animateToColor(Color.CYAN, 4000));
        waitForActivity(myTextBoxes[0].animateToColor(Color.YELLOW, 4000));

        // drop pivot and lo
        myTextBoxes[0].animateToPositionScaleRotation(100, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[1].animateToPositionScaleRotation(145, 200, 1.5, 0, 3000));

        // swap lo and pivot
        myTextBoxes[1].animateToColor(Color.GREEN, 3000);
        myTextBoxes[0].animateToColor(Color.LIGHT_GRAY, 3000);
        myTextBoxes[1].animateToPositionScaleRotation(100, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[0].animateToPositionScaleRotation(145, 200, 1.5, 0, 3000));

        // swap values
        temp = myTextBoxes[0];
        myTextBoxes[0] = myTextBoxes[1];
        myTextBoxes[1] = temp;

        // raise lo and pivot
        myTextBoxes[0].animateToPositionScaleRotation(100, 150, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[1].animateToPositionScaleRotation(145, 150, 1.5, 0, 3000));

        //
        // 2 elements sorted
        //

        waitForActivity(myTextBoxes[1].animateToColor(Color.RED, 4000));
        waitForActivity(myTextBoxes[1].animateToColor(Color.GREEN, 4000));

        //
        // 3 elements sorted
        //

        // Show Pivot, lo and hi
        waitForActivity(myTextBoxes[6].animateToColor(Color.RED, 4000));
        waitForActivity(myTextBoxes[5].animateToColor(Color.CYAN, 4000));
        waitForActivity(myTextBoxes[3].animateToColor(Color.YELLOW, 4000));

        // Find correct hi
        myTextBoxes[5].animateToColor(Color.LIGHT_GRAY, 4000);
        waitForActivity(myTextBoxes[4].animateToColor(Color.CYAN, 4000));

        // drop lo and hi
        myTextBoxes[3].animateToPositionScaleRotation(235, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[4].animateToPositionScaleRotation(280, 200, 1.5, 0, 3000));

        // swap lo and hi
        myTextBoxes[3].animateToColor(Color.CYAN, 3000);
        myTextBoxes[4].animateToColor(Color.YELLOW, 3000);
        myTextBoxes[3].animateToPositionScaleRotation(280, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[4].animateToPositionScaleRotation(235, 200, 1.5, 0, 3000));

        // swap values
        temp = myTextBoxes[3];
        myTextBoxes[3] = myTextBoxes[4];
        myTextBoxes[4] = temp;

        // raise lo and hi
        myTextBoxes[4].animateToPositionScaleRotation(280, 150, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[3].animateToPositionScaleRotation(235, 150, 1.5, 0, 3000));

        // Find correct lo
        myTextBoxes[3].animateToColor(Color.LIGHT_GRAY, 4000);
        waitForActivity(myTextBoxes[4].animateToColor(Color.YELLOW, 4000));

        // drop pivot and lo
        myTextBoxes[4].animateToPositionScaleRotation(280, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[6].animateToPositionScaleRotation(370, 200, 1.5, 0, 3000));

        // swap lo and pivot
        myTextBoxes[6].animateToColor(Color.GREEN, 3000);
        myTextBoxes[4].animateToColor(Color.LIGHT_GRAY, 3000);
        myTextBoxes[6].animateToPositionScaleRotation(280, 200, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[4].animateToPositionScaleRotation(370, 200, 1.5, 0, 3000));

        // swap values
        temp = myTextBoxes[4];
        myTextBoxes[4] = myTextBoxes[6];
        myTextBoxes[6] = temp;

        // raise lo and pivot
        myTextBoxes[4].animateToPositionScaleRotation(280, 150, 1.5, 0, 3000);
        waitForActivity(myTextBoxes[6].animateToPositionScaleRotation(370, 150, 1.5, 0, 3000));

        //
        // 4 elements sorted
        //

        waitForActivity(myTextBoxes[3].animateToColor(Color.RED, 4000));
        waitForActivity(myTextBoxes[3].animateToColor(Color.GREEN, 4000));

        //
        // 5 elements sorted
        //

        // Show pivot, lo and hi
        waitForActivity(myTextBoxes[6].animateToColor(Color.RED, 4000));
        waitForActivity(myTextBoxes[5].animateToColor(Color.CYAN, 4000));
        waitForActivity(myTextBoxes[5].animateToColor(Color.YELLOW, 4000));

        // Find correct lo
        waitForActivity(myTextBoxes[5].animateToColor(Color.LIGHT_GRAY, 4000));

        waitForActivity(myTextBoxes[6].animateToColor(Color.GREEN, 4000));

        //
        // 6 elements sorted
        //

        waitForActivity(myTextBoxes[5].animateToColor(Color.RED, 4000));
        waitForActivity(myTextBoxes[5].animateToColor(Color.GREEN, 4000));

        //
        // FULLY SORTED!!!!!!!!!!
        //


        // parameters are x, y, scale, rotation (in radians), and time (in ms)

        // Two animations can go at the same time. You just need to give them the
        // same duration and then wait for the last one (or the slowest one)
        header.animateToPositionScaleRotation(0, 0, 2.0, 0, 3000);
    }

    private static void waitForActivity(PActivity activity) {
        long endTime = activity.getStartTime() + activity.getDuration();
        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // Whatever, I do what I want.
            }
        }
    }
}