//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.training.springboot.test0100.repository;

import com.training.springboot.test0100.entity.StudentModel;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<StudentModel, Long> {
    List<StudentModel> findByName(String var1);
}
