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

    public static Employee create(Long id, Long departmentId, String name) {
        Employee employee = new Employee(id, departmentId, name);
        employee.markNew();
        return employee;
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
        markDirty();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void delete() {
        markRemoved();
    }
}
