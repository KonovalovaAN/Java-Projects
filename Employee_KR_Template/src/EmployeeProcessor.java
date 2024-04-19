import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeProcessor {

    public static List<Employee> sortBySalaryDescending(ArrayList<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::calculateSalary).reversed())
                .collect(Collectors.toList());
    }

    public static List<Employee> sortBySalaryCoefficientRatioAscending(ArrayList<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(employee -> employee.calculateSalary() / employee.positionCoefficient))
                .collect(Collectors.toList());
    }

    public static List<String> getOrganizationsStartingWithB(ArrayList<Employee> employees) {
        return employees.stream()
                .map(employee -> employee.organization)
                .filter(org -> org.startsWith("B"))
                .distinct()
                .collect(Collectors.toList());
    }

    public static double calculateAverageSalaryForOrganization(ArrayList<Employee> employees, String organization) {
        List<Employee> filteredEmployees = employees.stream()
                .filter(employee -> employee.organization.equals(organization))
                .collect(Collectors.toList());

        if (filteredEmployees.isEmpty()) {
            throw new IllegalArgumentException("No employees found for the given organization");
        }

        double totalSalary = filteredEmployees.stream()
                .mapToDouble(Employee::calculateSalary)
                .sum();

        return totalSalary / filteredEmployees.size();
    }
}
