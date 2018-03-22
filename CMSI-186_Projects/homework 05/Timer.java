public class Timer {

    public double totalSeconds;
    public double hours, minutes, seconds;
    public double timeslice;

    public Timer( double t ){
        totalSeconds = 0.0;
        timeslice = t;
    }

    //methods start there

    public double secondsElapsed() {
        totalSeconds += timeslice;
        return totalSeconds;
    }

    public String toString() {
        hours = Math.floor(totalSeconds / 3600);
        minutes = Math.floor((totalSeconds % 3600) /60);
        seconds = totalSeconds - ((minutes * 60) + (hours * 3600));
        DecimalFormat df = new DecimalFormat("#.00");
        return (int) hours + " : " + (int) minutes + " : " + df.format(seconds);
    }
}
