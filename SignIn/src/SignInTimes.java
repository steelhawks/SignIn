import java.util.*;
import java.text.SimpleDateFormat;

public class SignInTimes
{
  
  HashMap<Integer, Date> inTime = new HashMap<Integer, Date>();
  
  public SignInTimes (int[] osis, Date[] time)
  {
    for (int i = 0; i < osis.length; i++) 
    {
      inTime.put(osis[i], time[i]);
    }
  }
  
  public Date getTime(int osis)
  {
    return inTime.get(osis);
  }
  
  public String convertStringToDate(Date indate)
  {
     String dateString = null;
     SimpleDateFormat sdfr = new SimpleDateFormat("hh:mm:ss a zzz");
     /*you can also use DateFormat reference instead of SimpleDateFormat 
      * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
      */
     try{
    dateString = sdfr.format( indate );
     }catch (Exception ex ){
    System.out.println(ex);
     }
     return dateString;
  }
  
  public String toString()
  {
    return inTime.toString();
  }
}

