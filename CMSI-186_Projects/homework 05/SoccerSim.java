public class SoccerSim {

    public SoccerSim() {
        super;
    }

    public void handleInitialArguments( String args[] ) {
        System.out.println("\n   Hello world, from the SoccerSim program!!\n\n")
        if( 0 == args.length ) {
            System.out.println(" Sorry you must enter at least 8 arguments\n" +
            " Usage: java SoccerSim <xLocation1> <yLocation1> <xVelocity1> <yVelocity1>  ")
                               " Usage: java SoccerSim <xLocation1> <yLocation1> <xVelocity1> <yVelocity1> <xLocation2> <yLocation2> <xVelocity2> <yVelocity2> [timeSlice]\n" +
                               " Please try again...");
            System.exit(1);
        }
        balls = new Ball[];
        int j = 0;
        if( (args.length % 4) == 1) {
            for (int i = 0; i < args.length; i+=4) {
                balls[j] = new Ball(
                Double.parseDouble(args[i+0]);
                Double.parseDouble(args[i+1]);
                Double.parseDouble(args[i+2]);
                Double.parseDouble(args[i+3]);
                );

                 xPosition = Double.parseDouble(args[i]);
                 yPostition = Double.parseDouble(args[i+1]);
                 xVel = Double.parseDouble(args[i+2]);
                 yVel = Double.parseDouble(args[i+3]);
            }
            timeSlice = Double.parseDouble(args[args.length - 1]);
        }

    }
}
