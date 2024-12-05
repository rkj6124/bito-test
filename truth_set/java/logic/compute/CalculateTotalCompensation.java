class Employee {
    private double baseSalary;
    private double bonusPercentage;
    private double taxPercentage;

    public Employee(double baseSalary, double bonusPercentage, double taxPercentage) {
        this.baseSalary = baseSalary;
        this.bonusPercentage = bonusPercentage;
        this.taxPercentage = taxPercentage;
    }

    public double getBaseSalary() { return baseSalary; }
    public double getBonusPercentage() { return bonusPercentage; }
    public double getTaxPercentage() { return taxPercentage; }
}

public class CalculateTotalCompensation {
    public static double calculateTotalCompensation(Employee employee) {
        double bonus = employee.getBaseSalary() * employee.getBonusPercentage();
        double tax = bonus * employee.getTaxPercentage();
        return employee.getBaseSalary() + bonus - tax;
    }

    public static void main(String[] args) {
        Employee employee = new Employee(50000, 0.10, 0.20);
        System.out.println(calculateTotalCompensation(employee));
    }
}