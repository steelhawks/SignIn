import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
  public static CellStyle getHighlightStyle(Workbook workbook, short colorIndex )
  {
    CellStyle style = workbook.createCellStyle();
    style.setFillForegroundColor(colorIndex);     
    FillPatternType fillcolor = FillPatternType.SOLID_FOREGROUND;
    style.setFillPattern(fillcolor);
    return style;
  }
  
  public static void main(String[] args)
  {
    //Loading all objects
    Scanner readUserIn = new Scanner(System.in);
    
    System.out.println(java.time.LocalDate.now());
    
    //Getting main and output excel files
    File mainFile = new File("C:\\SignInOutForms/Main.xlsx");
    System.out.println("Main.xlsx exists.");
    File outputFile = new File("C:\\SignInOutForms/Output.xlsx");
    System.out.println("Output.xlsx exists.");
    
    //Getting user to enter the Sign In file name
    System.out.println("Enter the name of the SIGN-IN file under \"documents\" to process:");
    File signInFile = new File("C:\\SignInOutForms/" + readUserIn.next() + ".xlsx");
    System.out.println("Directory: " + signInFile);
    
    //Getting user to enter the Sign Out file name
    System.out.println("Enter the name of the SIGN-OUT file under \"documents\" to process:");
    File signOutFile = new File("C:\\SignInOutForms/" + readUserIn.next() + ".xlsx");
    System.out.println("Directory: " + signOutFile);
    
    try 
    {     
      FileInputStream main = new FileInputStream(mainFile);
      FileOutputStream output = new FileOutputStream(outputFile);
      FileInputStream signIn = new FileInputStream(signInFile);
      FileInputStream signOut = new FileInputStream(signOutFile);
      System.out.println("Files exist.");
      
      Workbook mainWorkbook = new XSSFWorkbook(main);
      Workbook outputWorkbook = new XSSFWorkbook();
      Workbook signInWorkbook = new XSSFWorkbook(signIn);
      Workbook signOutWorkbook = new XSSFWorkbook(signOut);
      
      Sheet sheetMain = mainWorkbook.getSheetAt(0);
      Sheet sheetOutput = outputWorkbook.createSheet(java.time.LocalDate.now().toString());
      Sheet sheetSignIn = signInWorkbook.getSheetAt(0);
      Sheet sheetSignOut = signOutWorkbook.getSheetAt(0);
      
      
      //Cycling     
      int[] osis = new int[100];
      String[] firstName = new String[100];
      String[] lastName = new String[100];
      String[] grade = new String[100];
      int counter = 0;
      
      osis = new int[100];
      Date[] time = new Date[100];
      for (Row row: sheetSignIn)
      {
        Cell tempOSIS = row.getCell(1);
        osis[counter] = (int)tempOSIS.getNumericCellValue();
        Cell tempTime = row.getCell(0);
        time[counter] = tempTime.getDateCellValue();
        counter++;
      }
        
      SignInTimes inTimes = new SignInTimes(osis, time);
      
      counter = 0;
      
      osis = new int[100];
      time = new Date[100];
      for (Row row: sheetSignOut)
      {
        Cell tempOSIS = row.getCell(1);
        osis[counter] = (int)tempOSIS.getNumericCellValue();
        Cell tempTime = row.getCell(0);
        time[counter] = tempTime.getDateCellValue();
        counter++;
      }
        
      SignOutTimes outTimes = new SignOutTimes(osis, time);
      
      counter = 0;
      
      for (Row row: sheetMain)
      {
        Cell tempOSIS = row.getCell(0);
        osis[counter] = (int)tempOSIS.getNumericCellValue();
        Cell tempFirstName = row.getCell(1);
        firstName[counter] = tempFirstName.getStringCellValue();
        Cell tempLastName = row.getCell(2);
        lastName[counter] = tempLastName.getStringCellValue();
        Cell tempGrade = row.getCell(3);
        grade[counter] = tempGrade.getStringCellValue();
        counter++;
      }
      
      StudentFirst studentFirst = new StudentFirst(osis, firstName);
      StudentLast studentLast = new StudentLast(osis, lastName);
      StudentGrade studentGrade = new StudentGrade(osis, grade);
      
      System.out.println(studentFirst);
      System.out.println(studentLast);
      System.out.println(studentGrade);
      System.out.println(inTimes);
      System.out.println(outTimes);

      
      CellStyle redBackgroundStyle = getHighlightStyle(outputWorkbook, IndexedColors.RED.getIndex());
      
      
      for (int i = 0; i < osis.length; i++)
      {
        int tempOSIS = osis[i];
        Row row = sheetOutput.createRow(i);
        
//        row.setRowStyle(style);

        row.createCell(0).setCellValue(studentFirst.getStudent(tempOSIS));

        row.createCell(1).setCellValue(studentLast.getStudent(tempOSIS));

        row.createCell(2).setCellValue(studentGrade.getStudent(tempOSIS));

        row.createCell(3).setCellValue(inTimes.convertStringToDate(inTimes.getTime(tempOSIS)));

        row.createCell(4).setCellValue(outTimes.convertStringToDate(outTimes.getTime(tempOSIS)));
        
        if(inTimes.getTime(tempOSIS)==null || outTimes.getTime(tempOSIS)==null)
        {
          row.getCell(0).setCellStyle(redBackgroundStyle);
          row.getCell(1).setCellStyle(redBackgroundStyle);
          row.getCell(2).setCellStyle(redBackgroundStyle);
          row.getCell(3).setCellStyle(redBackgroundStyle);
          row.getCell(4).setCellStyle(redBackgroundStyle);
        }
        
        //row.getCell(0).setCellStyle(style);
        
      }
      
      outputWorkbook.write(output);
      
      mainWorkbook.close();
      outputWorkbook.close();
      signInWorkbook.close();
      signOutWorkbook.close();
      
    }
    catch (Exception e)
    { 
      System.out.println(e.getMessage());
      System.out.println("One or more files do not exist.");
      System.exit(0);
    }
    readUserIn.close();
  }
}



