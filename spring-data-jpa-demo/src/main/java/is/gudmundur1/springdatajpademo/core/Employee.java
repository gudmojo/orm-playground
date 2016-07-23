package is.gudmundur1.springdatajpademo.core;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEES")
public class Employee extends DomainObject {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String name;

    public Employee() {
    }

    public Employee(Long id, Department department, String name) {
        this.id = id;
        this.department = department;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
