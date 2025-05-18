import java.util.ArrayList;
import java.util.Scanner;

//in this class we are overriding toString() method 
//it is javas inbuilt method which gets invoked wheneevr system.out.println is executed
//that is why whenever we print employee we are getting in that format
//in this class we are creating super class of PartTimeEmployee ,FullTimeEmployee and PayRollSystem classes
//we are declaring alculateSalary as abstract because we want the children classes to override it
//getId and getName are examples of Encapsulation
//getId-it is used in PayRollSsytem class , in remove employee method 
abstract class Employee{
    private String name;
    private int id;

    public Employee(String name,int id){
        this.name=name;
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    
    public abstract double calculateSalary();
    
    @Override
    public String toString(){
        return "Employee [name="+name+",id="+id+",salary="+calculateSalary()+"]";
    }
}


//in this class we are taking name, id ,monthly salary as parameters
//name , id are instantiated using super keyword
//super keyword can invoke super class's constructor-by using super keyword , we are resuing Employee class
//calculate salary just returns monthly salary
class FullTimeEmployee extends Employee{
    private double monthlySalary;

    public FullTimeEmployee(String name,int id,double monthlySalary){
        super(name,id);
        this.monthlySalary=monthlySalary;
    }

    @Override
    public double calculateSalary(){
        return monthlySalary;
    }
}


//same as before class, here we are calculating salary by using hourlyRate*hoursWorked; formula
class PartTimeEmployee extends Employee{
    private int hoursWorked;
    private double hourlyRate;
    public PartTimeEmployee(String name,int id,int hoursWorked,double hourlyRate){
        super(name,id);
        this.hoursWorked=hoursWorked;
        this.hourlyRate=hourlyRate;
    
    }
    @Override
    public double calculateSalary(){
        return hourlyRate*hoursWorked;
    }
}



//employee list has list of all employees
//methods-addEmployee(),rempveEmployee()
//addEmployee uses Employee paramter
//removeEmployee uses id paramter
//displayEmployee uses for each loop
class PayRollSystem{
    private ArrayList<Employee> employeeList;
    public PayRollSystem(){
        employeeList=new ArrayList<>();
    }
    public void addEmployee(Employee employee){
        employeeList.add(employee);
        System.out.println("Employee added successfully");
        System.out.println();
    }
    public void removeEmployee(int id){
        Employee employeeToRemove=null;
        for(Employee employee : employeeList){
            if(employee.getId()==id){
                employeeToRemove=employee;
                break;
            }
        }
        if(employeeToRemove!=null){
            employeeList.remove(employeeToRemove);
            System.out.println();
            System.out.println("Employee removed successfully");
            System.out.println();
        }
        else{
            System.out.println();
            System.out.println("There is no employee with this id");
            System.out.println();
        }
    }
    public void displayEmployees(){
        if (employeeList.isEmpty()) {
            System.out.println();
            System.out.println("No employees to display.");
            System.out.println();
        } else {
            System.out.println();
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
            System.out.println();
        }
    }
}



public class Main {
    static Scanner sc=new Scanner(System.in);
     public static void main(String[] args) {
        PayRollSystem payrollsystem=new PayRollSystem();
        while(true){
            System.out.println();
            System.out.println();
            System.out.println("What do you want to do ...?");
            System.out.println("1 : Add Full Time Employee");
            System.out.println("2 : Add Part Time Employee");
            System.out.println("3 : Remove Employee");
            System.out.println("4 : Display All Employees");
            System.out.println("5 : Exit");
            System.out.println();
            System.out.println();
            int select=sc.nextInt();
            
            switch(select){
                case 1:
                    addFTE(payrollsystem);
                    break;
                case 2:
                    addPTE(payrollsystem);
                    break;
                case 3:
                    removeEmp(payrollsystem);
                    break;
                case 4:
                    displayEmp(payrollsystem);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option try again with a valid number given above");
            }
        }
        
    }

    //creating FullTimeEmployee object and adding it to arraylist(employeeList) using addEmployee(emp) methos
    public static void addFTE(PayRollSystem payrollsystem){
        System.out.println("What is name of the employee");
        sc.nextLine();
        String name=sc.nextLine();
        System.out.println("What is id of the employee");
        int id=sc.nextInt();
        System.out.println("What is salary of the employee");
        double salary=sc.nextDouble();

        FullTimeEmployee emp=new FullTimeEmployee(name, id, salary);
        payrollsystem.addEmployee(emp);
    }

    //creating PartTimeEmployee object and adding it to arraylist(employeeList) using addEmployee(emp) methos
    public static void addPTE(PayRollSystem payrollsystem){
        System.out.println("What is name of the employee");
        sc.nextLine();
        String name=sc.nextLine();
        System.out.println("What is id of the employee");
        int id=sc.nextInt();
        System.out.println("What is hourly rate of the employee");
        double hourlyRate=sc.nextDouble();
        System.out.println("How many hours did employee worl");
        int hoursWorked=sc.nextInt();

        PartTimeEmployee emp=new PartTimeEmployee(name, id, hoursWorked,hourlyRate); 
        payrollsystem.addEmployee(emp);      
    }


    public static void removeEmp(PayRollSystem payrollsystem){
        System.out.println("What is id of the employee");
        int id=sc.nextInt();
        payrollsystem.removeEmployee(id);
    }

    
    public static void displayEmp(PayRollSystem payrollsystem){
        payrollsystem.displayEmployees();
    }

}