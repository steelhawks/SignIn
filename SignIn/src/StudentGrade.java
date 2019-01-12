import java.util.*;

public class StudentGrade
{
  
  HashMap<Integer, String> studentGrade = new HashMap<Integer, String>();
  
  public StudentGrade (int[] osis, String[] grade)
  {
    for (int i = 0; i < osis.length; i++) 
    {
      studentGrade.put(osis[i], grade[i]);
    }
  }
  
  public String getStudent(int osis)
  {
    return studentGrade.get(osis);
  }
  
  public String toString()
  {
    return studentGrade.toString();
  }
}

