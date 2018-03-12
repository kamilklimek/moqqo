package dao;

import api.Dao;
import api.DaoRelation;
import model.Task;

import java.util.*;

/**
 * @author maniaq
 * Class stands for operations on tasks' database (binary file)
 */

public class TaskDao implements Dao, DaoRelation{

    /**
     * name of file where contain tasks informations
     */
    private final String DB_NAME = "tasks";

    /**
     * Map for holding tasks
     * key: projectId
     * value: List of tasks which are in the same project
     */
    private Map<Integer, List<Task>> tasks;

    public TaskDao(){
        tasks = new HashMap<>();
    }

    @Override
    public void insert(Object value) {
        List<Task> taskList;
        Task task = (Task) value;
        boolean ifTaskExist = tasks.containsKey(task.getProjectId());
        if(ifTaskExist){
            taskList = tasks.get(task.getProjectId());
            taskList.add(task);

            tasks.remove(task.getProjectId());
            tasks.put(task.getProjectId(), taskList);
        }else{
            taskList = new LinkedList<>();
            taskList.add(task);
            tasks.put(task.getProjectId(), taskList);
        }
    }

    @Override
    public Optional<Object> findById(Integer id) {
        if(existsById(id)!= -1){
            return Optional.of(tasks.get(id));
        }
        return Optional.empty();
    }



    @Override
    public Map<Integer, Object> selectAll() {
        return null;
    }

    @Override
    public Integer existsById(Integer id) {
        if(tasks.containsKey(id))
            return id;
        return -1;
    }

    @Override
    public void removeById(Integer id) {
        if(existsById(id) == -1)
            return;


    }

    @Override
    public void loadFromDb() {

    }

    @Override
    public void saveToDb() {

    }

    @Override
    public Object parseRecord(String record) {
        String [] projectInformations = record.split("~");
        Task task = new Task.TaskBuilder()
                .setId(Integer.parseInt(projectInformations[0]))
                .setProjectId(Integer.parseInt(projectInformations[1]))
                .setTaskName(projectInformations[2])
                .setTaskDescription(projectInformations[3])
                .buildTask();
        return (Object) task;
    }

    public Optional<List<Task>> getTasksListByProjectId(Integer projectId){
        if(existsById(projectId)==-1)
            return Optional.empty();

        return Optional.of(tasks.get(projectId));

    }

    @Override
    public Optional<List<Object>> getObjectByKeyIdAndListId(Integer key, Integer objectId) {
        return Optional.empty();
    }
}
