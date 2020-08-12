package scala_demo

import java.text.SimpleDateFormat;
import java.util.Calendar;

object CalendarDemo extends App {

  val calender = Calendar.getInstance();
  calender.roll(Calendar.DATE, -11);
  val date = calender.getTime();

  val fmt = new SimpleDateFormat("yyyy-MM-dd");
  println(fmt.format(date));


}
