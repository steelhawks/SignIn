
import java.util.*;

public class StudentLast
{
  
  HashMap<Integer, String> studentIds = new HashMap<Integer, String>();
  
  public StudentLast (int[] osis, String[] name)
  {
    for (int i = 0; i < osis.length; i++) 
    {
      studentIds.put(osis[i], name[i]);
    }
  }
  
  public String getStudent(int osis)
  {
    return studentIds.get(osis);
  }
  
  public String toString()
  {
    return studentIds.toString();
  }
}

