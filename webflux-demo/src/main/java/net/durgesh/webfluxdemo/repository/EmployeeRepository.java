package net.durgesh.webfluxdemo.repository;

import net.durgesh.webfluxdemo.model.Employee;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {

    // In-memory store - simulates a ddatabase
    private static Map<Integer, Employee> employeeMap = new HashMap<>();

    static {
        employeeMap.put(1, new Employee(1, "Sristi", "I", "sristi@email.com"));
        employeeMap.put(2, new Employee(2, "Durgesh", "Tiwari", "durgesh@email.com"));
        employeeMap.put(3, new Employee(3, "John", "Doe", "john@email.com"));
    }

    // Mono.justOrEmpty - emits the value if present, completes empty if null
    // just() throws Null PointerException if value is null
    // justOrEmpty() handles null safely - emits empty Mono
    public Mono<Employee> findById(int id) {
        return Mono.justOrEmpty(employeeMap.get(id));
    }

    // Flux.fromIterable - converts any collection to Flux stream
    // creates a flux that emits each element of the collection one by one reactively
    public Flux<Employee> findAll() {
        return Flux.fromIterable(employeeMap.values());
    }

    public Mono<Employee> save(Employee employee) {
        employeeMap.put(employee.getId(), employee);
        return Mono.just(employee);
    }

    public Mono<Employee> update(int id, Employee employee) {
        employeeMap.put(id, employee);
        return Mono.just(employee);
    }

    // Mono<Void> standard return for delete - emits nothing, just completes
    public Mono<Void> deleteById(int id) {
        employeeMap.remove(id);
        return Mono.empty();
    }
}