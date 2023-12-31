package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) throws UserNotFoundException {
        return employeeRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek day) {
        List<Employee> availableEmployees = employeeRepository.findByDaysAvailable(day);
        return availableEmployees.stream().filter(e -> e.getSkills().containsAll(skills)).collect(Collectors.toList());
    }

    public List<Employee> findAllByIds(List<Long> employeeIds) {
        return employeeRepository.findAllById(employeeIds);
    }
}
