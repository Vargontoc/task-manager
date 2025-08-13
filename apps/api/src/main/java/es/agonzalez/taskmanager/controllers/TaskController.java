package es.agonzalez.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.agonzalez.taskmanager.domain.models.Task;
import es.agonzalez.taskmanager.domain.services.TaskService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  @Autowired
  private TaskService service;

  @GetMapping
  public Page<Task> getTasks(Pageable pageable)  { return service.findAll(pageable); }
  @PostMapping
  public Task create(@Valid @RequestBody Task t) { return service.create(t); }
  @PutMapping("/id")
  public Task update(@PathVariable Long id, @Valid @RequestBody Task t) { return service.update(id, t); }
  @DeleteMapping("/id")
  public void delete(@PathVariable Long id) { service.delete(id); }
}
