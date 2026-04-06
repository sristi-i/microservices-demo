package net.durgesh.webfluxdemo.service;

import net.durgesh.webfluxdemo.model.Employee;
import net.durgesh.webfluxdemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Mono<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Mono<Employee> updateEmployee(int id, Employee employee) {
        return employeeRepository.update(id, employee);
    }

    public Mono<Void> deleteEmployee(int id) {
        return employeeRepository.deleteById(id);
    }

}