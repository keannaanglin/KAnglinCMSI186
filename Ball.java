import java.text.DecimalFormat;

public class Ball {

    private final static double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
    private double xLocation;
    private double yLocation;
    private double xVel;
    private double yVel;
    private double timeSlice;

    public Ball() {
        timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
    }

    public Ball(double x, double y, double z, double w, double t) {
        xLocation = x;
        yLocation = y;
        xVel = z;
        yVel = w;
        timeSlice = t;
    }

    //methods start here!
    public double[] getLocation() {
        double [] location = new double[2];
        location[0] = xLocation;
        location[1] = yLocation;
        return location;
    }

    public double[] getVelocity () {
        double [] velocity = new double[2];
        velocity[0] = xVel;
        velocity[1] = yVel;
        return velocity;
    }

    public void friction() {
        this.xVel *= Math.pow(0.99, timeSlice);
        this.yVel *= Math.pow(0.99, timeSlice);
    }

    public double [] move (){
        double [] moving = new double [2];
        moving[0] = xLocation + xVel;
        moving[1] = yLocation + yVel;
        friction();
        return moving;
    }

    public boolean isMoving() {
        return (Math.sqrt((Math.pow((xVel * 12), 2))+(Math.pow((yVel * 12), 2)))) > 1.0;
    }

    public boolean isOnField(double fieldX, double fieldY) {
        if((Math.abs(xLocation) <= (fieldX/2)) && (Math.abs(yLocation) <= (fieldY/2))){
            return true;
        }
        return false;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.000");
        String ball= "<" + df.format(xLocation) + "," + df.format(yLocation) + ">" + "<" + df.format(xVel) + "," + df.format(yVel) + ">";
        return ball;
    }

}
