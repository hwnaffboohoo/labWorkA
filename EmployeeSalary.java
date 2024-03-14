/**
 * Program Description : Construst a program based on given class diagram related to the employee salary data from an input file called "employeeSalaries.txt".
 *
 * Programmer : HANA
 * Date : 14 March 2024
 */
import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class EmployeeSalary
{
   public static void main(String[]args) throws IOException
    {
        DecimalFormat dF = new DecimalFormat("0.00");
        try
        {
            //input file
            BufferedReader inputFile = new BufferedReader(new FileReader("employeeSalaries.txt"));
            //2 output files
            PrintWriter fileoutput1 = new PrintWriter(new FileWriter("employeeData.txt"));
            
            
            //Declare the variables
            String inputData = null;
            String employname = "";
            double employSalary = 0.00;
            int employWorkingYear = 0;
            
            //variable for top performing employee
            String top_employname = "";
            double top_employSalary = 0.00;
            int top_employWorkingYear = 0;
            
            //variable for newest employee
            String latest_employname = "";
            double latest_employSalary = 0.00;
            int latest_employWorkingYear = 0;
            
            //Write the title header
            fileoutput1.println("****************************************************************");
            fileoutput1.println("*---------------- List of all Employees -----------------------*");
            fileoutput1.println("****************************************************************");
            
            while((inputData = inputFile.readLine()) != null)
            {
                StringTokenizer strT = new StringTokenizer(inputData,"|");
                employname= strT.nextToken();
                employSalary = Double.parseDouble(strT.nextToken());
                employWorkingYear = Integer.parseInt(strT.nextToken());
                
                
                double annualSalary = employSalary + (employSalary * 0.05);
                
                //to test for the negative number
                if(employSalary < 0 || employWorkingYear <0)
                  throw new IllegalArgumentException();
                
                //find top performing employee
                if(annualSalary > top_employSalary){
                    top_employname = employname;
                    top_employSalary = annualSalary;
                    top_employWorkingYear = employWorkingYear;
                }
                //find the employee with the least years of service
                if(latest_employWorkingYear == 0 || employWorkingYear < latest_employWorkingYear){
                    latest_employname = employname;
                    latest_employSalary = annualSalary;
                    latest_employWorkingYear = employWorkingYear;
                    
                }
                
                //store list of employees
                String employData = employname+"\t\t RM "+annualSalary+"\t\t "+employWorkingYear+" years";
                fileoutput1.println(employData);
    
            }
            //top performing employee
            fileoutput1.println("\n\n ***************************************************************");
            fileoutput1.println(" *-------------- Top Performing Employee Details --------------*");
            fileoutput1.println(" ***************************************************************");
            String top_employData = top_employname+"\t\t RM "+top_employSalary+"\t\t "+top_employWorkingYear+" years";
            fileoutput1.println(top_employData);
            //display top performing employee
            JOptionPane.showMessageDialog(null,"*-*-*-*-*Top performing Employee Details *-*-*-*-*\n"+top_employData);
            
            //latest employee
            fileoutput1.println("\n\n *********************************************************************");
            fileoutput1.println(" *****--- Details of Employee with the least years of service ---*****");
            fileoutput1.println(" *********************************************************************");
            String latest_employData = latest_employname+"\t\t RM "+latest_employSalary+"\t\t "+latest_employWorkingYear+" years";
            fileoutput1.println(latest_employData);
            JOptionPane.showMessageDialog(null,"*-*-*-*-* Details of Employee with the least years of service *-*-*-*-*\n"+latest_employData);

            //close all files
            inputFile.close();
            fileoutput1.close();
            
        }
        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input!";
            JOptionPane.showMessageDialog(null, output);
        }
    }
}