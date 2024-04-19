import java.util.List;
import java.util.stream.Collectors;

class WorkerProcessor {
    static List<Worker> sortBySalaryDescending(List<Worker> workers) {
        return workers.stream()
                .sorted((w1, w2) -> Double.compare(w2.calculateSalary(), w1.calculateSalary()))
                .collect(Collectors.toList());
    }

    static List<Worker> sortBySalaryCoefficientAscending(List<Worker> workers) {
        return workers.stream()
                .sorted((w1, w2) -> Double.compare(w1.calculateSalary() / w1.positionCoefficient,
                        w2.calculateSalary() / w2.positionCoefficient))
                .collect(Collectors.toList());
    }

    static List<String> getOrganizationsStartingWithB(List<Worker> workers) {
        return workers.stream()
                .map(worker -> worker.organization)
                .distinct()
                .filter(org -> org.startsWith("B"))
                .collect(Collectors.toList());
    }

    static double calculateAverageSalaryForOrganization(List<Worker> workers, String organization) {
        List<Worker> organizationWorkers = workers.stream()
                .filter(worker -> worker.organization.equals(organization))
                .collect(Collectors.toList());

        if (organizationWorkers.isEmpty()) {
            throw new IllegalArgumentException("Organization not found");
        }

        double totalSalary = organizationWorkers.stream()
                .mapToDouble(Worker::calculateSalary)
                .sum();

        return totalSalary / organizationWorkers.size();
    }
}
