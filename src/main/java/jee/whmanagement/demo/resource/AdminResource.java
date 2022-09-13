//package jee.whmanagement.demo.resource;
//
//import jee.whmanagement.demo.entity.User;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin")
//public class AdminResource {
//
//
//        // Save operation
//        @PostMapping("/create-user")
//        public Department saveUser(@RequestBody List<User> department)
//        {
//            return departmentService.saveDepartment(department);
//        }
//
//        // Read operation
//        @GetMapping("/departments")
//
//        public List<Department> fetchDepartmentList()
//        {
//            return departmentService.fetchDepartmentList();
//        }
//
//        // Update operation
//        @PutMapping("/departments/{id}")
//
//        public Department
//        updateDepartment(@RequestBody Department department,
//                         @PathVariable("id") Long departmentId)
//        {
//            return departmentService.updateDepartment(
//                    department, departmentId);
//        }
//
//        // Delete operation
//        @DeleteMapping("/departments/{id}")
//
//        public String deleteDepartmentById(@PathVariable("id")
//                                                   Long departmentId)
//        {
//            departmentService.deleteDepartmentById(
//                    departmentId);
//            return "Deleted Successfully";
//        }
//    }
//
//}
