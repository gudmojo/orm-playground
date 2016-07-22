package is.gudmundur1.unitofworkdemo.core;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DEPARTMENTS")
public class DepartmentAggregate extends DomainObject {

    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Employee> employeeList;

    public DepartmentAggregate() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
