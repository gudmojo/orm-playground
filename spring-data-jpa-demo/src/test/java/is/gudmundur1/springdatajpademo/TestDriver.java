package is.gudmundur1.springdatajpademo;

import is.gudmundur1.springdatajpademo.core.Department;
import is.gudmundur1.springdatajpademo.core.DepartmentRepository;
import is.gudmundur1.springdatajpademo.core.Employee;
import is.gudmundur1.springdatajpademo.core.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class TestDriver {

    public static final String SALES_NAME = "Sales";
    public static final String SALES_NAME_2 = "Sales2";
    private static final String TOO_LONG_DEPT_NAME = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void createDepartment(long id) {
        departmentRepository.save(new Department(id, SALES_NAME));
    }

    public void createDepartmentWithEmployees(long id, long id2, long id3) {
        Department department = new Department(id, SALES_NAME);
        departmentRepository.save(department);
        employeeRepository.save(new Employee(id2, department, "Bonnie"));
        employeeRepository.save(new Employee(id3, department, "Clyde"));
    }

    public void updateDepartment(long id) {
        Department department = departmentRepository.findOne(id);
        department.setName(SALES_NAME_2);
        departmentRepository.save(department);
    }

    public void deleteDepartment(long id) {
        Department department = departmentRepository.findOne(id);
        departmentRepository.delete(department);
    }

    public void cleanUpDepartment(long id) {
        Department department = departmentRepository.findOne(id);
        if (department != null) {
            departmentRepository.delete(department);
        }
    }

    public void cleanUpEmployee(long id) {
        Employee employee = employeeRepository.findOne(id);
        if (employee != null) {
            employeeRepository.delete(employee);
        }
    }

    public void testRollback(long deptId1, long deptId2) {
        departmentRepository.save(new Department(deptId1, SALES_NAME));
        departmentRepository.save(new Department(deptId2, TOO_LONG_DEPT_NAME));

    }
}
