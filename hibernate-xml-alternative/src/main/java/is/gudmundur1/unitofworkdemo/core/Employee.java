package is.gudmundur1.unitofworkdemo.core;

public class Employee extends DomainObject {

    private Long id;
    private Long departmentId;
    private String name;

    public Employee() {
    }

    public Employee(Long id, Long departmentId, String name) {
        this.id = id;
        this.departmentId = departmentId;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
