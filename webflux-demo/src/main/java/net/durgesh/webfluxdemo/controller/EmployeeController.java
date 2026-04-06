package net.durgesh.webfluxdemo.controller;

import net.durgesh.webfluxdemo.model.Employee;
import net.durgesh.webfluxdemo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Returns Mono<Employee> - single element, reactive
    // Mono is non-blocking - thread is freed immediately 
    // Spring subscrbes and sendd response when data is ready
    @GetMapping("/{id}")
    public Mono<Employee> getEmployee(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    // returns Flux<Employee> - multiple emlemets, reactive stream
    // Flux emits each Employee one by one as a stream
    // Client can start processing before all items arrive
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
     public Mono<Employee> saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
     }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }
}