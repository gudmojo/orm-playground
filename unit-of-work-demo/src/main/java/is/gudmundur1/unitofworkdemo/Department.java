package is.gudmundur1.unitofworkdemo;

public class Department extends DomainObject {

    private Long id;
    private String name;

    public Department() {
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Department create(Long id, String name) {
        Department department = new Department(id, name);
        department.markNew();
        return department;
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
}
