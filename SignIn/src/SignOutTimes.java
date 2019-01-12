
import java.util.*;
import java.text.SimpleDateFormat;

public class SignOutTimes
{
  
  HashMap<Integer, Date> outTime = new HashMap<Integer, Date>();
  
  public SignOutTimes (int[] osis, Date[] time)
  {
    for (int i = 0; i < osis.length; i++) 
    {
      outTime.put(osis[i], time[i]);
    }
  }
  
  public Date getTime(int osis)
  {
    return outTime.get(osis);
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
    return outTime.toString();
  }
}