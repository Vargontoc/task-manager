package es.agonzalez.taskmanager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.agonzalez.taskmanager.domain.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {}
