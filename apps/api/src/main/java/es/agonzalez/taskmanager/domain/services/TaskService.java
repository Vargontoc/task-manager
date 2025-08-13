package es.agonzalez.taskmanager.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.agonzalez.taskmanager.domain.models.Task;
import es.agonzalez.taskmanager.domain.repositories.TaskRepository;

@Service
public class TaskService {
  @Autowired
  private TaskRepository repository;

  public Page<Task> findAll(Pageable pageable) { return repository.findAll(pageable); }
  public Task create(Task t) { return repository.save(t); }
  public Task update(Long id, Task t) {
    Task current = repository.findById(id).orElseThrow();
    current.setTitle(t.getTitle());
    current.setDescription(t.getDescription());
    current.setStatus(t.getStatus());
    current.setDueDate(t.getDueDate());

    return repository.save(current);
  }
  public void delete(long id) { repository.deleteById(id);}
}
