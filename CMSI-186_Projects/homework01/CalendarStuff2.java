    /**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  <your name here>
 *  Date          :  <date of writing here>
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */
public class CalendarStuff2 {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY  = 0;
   private static final int MONDAY  = SUNDAY    + 1;
   private static final int TUESDAY = SUNDAY + 2;
   private static final int WEDNESDAY = SUNDAY + 3;
   private static final int THURSDAY = SUNDAY + 4;
   private static final int FRIDAY = SUNDAY + 5;
   private static final int SATURDAY = SUNDAY + 6;


  // you can finish the rest on your own

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH = JANUARY + 2;
   private static final int APRIL = JANUARY + 3;
   private static final int MAY = JANUARY + 4;
   private static final int JUNE = JANUARY + 5;
   private static final int JULY = JANUARY + 6;
   private static final int AUGUST = JANUARY + 7;
   private static final int September = JANUARY + 8;
   private static final int October = JANUARY + 9;
   private static final int November = JANUARY + 10;
   private static final int December = JANUARY + 11;
  // you can finish these on your own, too

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };



       public static boolean isLeapYear (long year) {
           if ((year % 4) !=0) {
               return false;
           } else if (year % 100 != 0) {
               return true;
           } else if (year % 400 != 0) {
               return false;
           }else {
               return true;
           }
       }


   public static long daysInMonth (long month, long year) {
       if (month == 4 || month == 6 || month == 9 || month == 12) {
           return 30;
       } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
           return 31;
       } else if (isLeapYear (year) == true && month == 2) {
           return 29;
       } else if (isLeapYear(year) == false && month == 2){
           return 28;
       }else {
           return -1;
       }
   }

   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return month1 == month2 && day1 == day2 && year1 == year2;
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if (dateEquals (month1, day1, year1, month2, day2, year2)) {
          return 0;
      } else if (year1 < year2) {
          return -1;
      } else if (day1 < day2) {
          return -1;
      } else if (month1 < month2) {
          return -1;
      } else if (year1 > year2) {
          return 1;
      } else if (day1 > day2) {
          return 1;
      } else if (month1 > month2) {
          return 1;
      } else {
          return 4;
      }

   }

   public static boolean isValidDate (long month, long day, long year) {
       if (month < 1 || month > 12) {
           return false;
       } else if (day < 1 || day < 31) {
           return false;
       } else if (year < 0) {
           return false;
       } else {
           return true;
       }
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      switch(month) {
          case 1: return "January";
          case 2: return "February";
          case 3: return "March";
          case 4: return "April";
          case 5: return "May";
          case 6: return "June";
          case 7: return "July";
          case 8: return "August";
          case 9: return "September";
         case 10: return "October";
         case 11: return "November";
         case 12: return "December";
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch(day) {
          case 1: return "Sunday";
          case 2: return "Monday";
          case 3: return "Tuesday";
          case 4: return "Wednesday";
          case 5: return "Thursday";
          case 6: return "Friday";
          case 7: return "Saturday";
         default       : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
       long dayCount = 0;
       if (year1 > year2 || (year1 == year2 && month1 > month2) || (year1 == year2 && month1 == month2 && day1 > day2)) {
           return daysBetween(day2, month2, year2, day1, month1, year1);
       }
       while (year1 != year2 || month1 != month2 || day1 != day2) {
           day1++;
           dayCount++;
           if (day1 > daysInMonth(month1, year1)) {
               month1++;
               day1 = 1;
           }
           if(month1 > 12) {
               year1++;
               month1 = 1;
           }
       }
       return dayCount;
   }
}
