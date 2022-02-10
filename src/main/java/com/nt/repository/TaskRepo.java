package com.nt.repository;

import com.nt.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TaskRepo extends CrudRepository<Task, Long> {
}
