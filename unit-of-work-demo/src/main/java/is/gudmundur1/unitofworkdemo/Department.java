package is.gudmundur1.unitofworkdemo;

public class Department extends DomainObject {

    private Long id;
    private final String name;

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
}
