package seedu.address.model.person.employee;

import java.util.Comparator;

public class EmployeeComparator {

    /**
     * Generates a default name comparator in ascending order.
     * @return A name comparator that sorts in ascending order.
     */
    public static Comparator<Employee> getDefaultComparator() {
        return (s1, s2) -> s1.getName().fullName.toLowerCase().compareToIgnoreCase(s2.getName().fullName);
    }

    /**
     * Generates a name comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A name comparator with type employee.
     */
    public static Comparator<Employee> getNameComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getName().fullName.toLowerCase().compareToIgnoreCase(s2.getName().fullName);
            } else {
                return s2.getName().fullName.toLowerCase().compareToIgnoreCase(s1.getName().fullName);
            }
        };
    }

    /**
     * Generates an address comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return An address comparator with type employee.
     */
    public static Comparator<Employee> getAddressComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getAddress().value.toLowerCase().compareToIgnoreCase(s2.getAddress().value);
            } else {
                return s2.getAddress().value.toLowerCase().compareToIgnoreCase(s1.getAddress().value);
            }
        };
    }

    /**
     * Generates an email comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return An email comparator with type employee.
     */
    public static Comparator<Employee> getEmailComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getEmail().value.toLowerCase().compareToIgnoreCase(s2.getEmail().value);
            } else {
                return s2.getEmail().value.toLowerCase().compareToIgnoreCase(s1.getEmail().value);
            }
        };
    }

    /**
     * Generates a phone comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A phone comparator with type employee.
     */
    public static Comparator<Employee> getPhoneComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getPhone().value.compareToIgnoreCase(s2.getPhone().value);
            } else {
                return s2.getPhone().value.compareToIgnoreCase(s1.getPhone().value);
            }
        };
    }

    /**
     * Generates a Salary comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A Salary comparator with type employee.
     */
    public static Comparator<Employee> getSalaryComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return Integer.parseInt(s1.getSalary().currentSalary) - Integer.parseInt(s2.getSalary().currentSalary);
            } else {
                return Integer.parseInt(s2.getSalary().currentSalary) - Integer.parseInt(s1.getSalary().currentSalary);
            }
        };
    }

    /**
     * Generates a leaves comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A leaves comparator with type employee.
     */
    public static Comparator<Employee> getLeavesComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return Integer.parseInt(s1.getLeaves().currentLeaves) - Integer.parseInt(s2.getLeaves().currentLeaves);
            } else {
                return Integer.parseInt(s2.getLeaves().currentLeaves) - Integer.parseInt(s1.getLeaves().currentLeaves);
            }
        };
    }

    /**
     * Generates a job title comparator based on the provided order of sorting.
     * @param isAscending True if sorting is to be done in ascending order.
     * @return A job title comparator with type employee.
     */
    public static Comparator<Employee> getJobTitleComparator(boolean isAscending) {
        return (s1, s2) -> {
            if (isAscending) {
                return s1.getJobTitle().jobTitle.compareToIgnoreCase(s2.getJobTitle().jobTitle);
            } else {
                return s2.getJobTitle().jobTitle.compareToIgnoreCase(s1.getJobTitle().jobTitle);
            }
        };
    }

}
