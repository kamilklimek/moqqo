package dao;

import api.Dao;
import model.Project;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author maniaq
 * Class stands for operations on database (binary file)
 */

public class ProjectDao implements Dao {
    /**
     * name of file where contain projects informations
     */
    private final String DB_NAME = "projects";

    /**
     * Map for projects
     * key: userID
     * value: project
     */
    private Map<Integer, Project> projects;

    public ProjectDao(){
        projects = new HashMap<>();
    }

    @Override
    public void insert(Object value) {
        Project project = (Project)value;
        projects.put(project.getUserId(), project);
    }

    @Override
    public Optional<Object> findById(Integer id) {
        if(existsById(id) != -1)
            return Optional.of(projects.get(id));
        return Optional.empty();
    }

    @Override
    public Map<Integer, Object> selectAll() {
        return (Map)projects;
    }

    @Override
    public Integer existsById(Integer id) {
        if(!projects.containsKey(id))
            return -1;
        return id;
    }

    @Override
    public void removeById(Integer id) {
        if(existsById(id)!=-1){
            projects.remove(id);
            saveToDb();
        }
    }

    @Override
    public void loadFromDb() {
        try {
            RandomAccessFile file = new RandomAccessFile(DB_NAME, "r");
            byte sizeOfRecord;
            byte[] bytes;
            try{
                while((sizeOfRecord = file.readByte()) != BYTE_END_OF_FILE){
                    if(sizeOfRecord>0){
                        bytes = new byte[sizeOfRecord];
                        file.read(bytes);
                        Project project = (Project)parseRecord(new String(bytes));
                        projects.put(project.getUserId(), project);
                    }
                }
            }catch(EOFException e){

            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveToDb() {
        try {
            byte[] bytes;
            RandomAccessFile file = new RandomAccessFile(DB_NAME, "rw");
            for (Map.Entry<Integer, Project> project: projects.entrySet()) {
                bytes = project.getValue().toBytes();
                file.write(bytes.length);
                file.write(project.getValue().toBytes());
            }
            file.write(BYTE_END_OF_FILE);
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object parseRecord(String record) {
        String [] projectInformations = record.split("~");
        Project project = new Project.ProjectBuilder()
                .setProjectId(Integer.parseInt(projectInformations[0]))
                .setProjectName(projectInformations[1])
                .setProjectDescription(projectInformations[2])
                .setUserId(Integer.parseInt(projectInformations[3]))
                .buildProject();
        return (Object) project;
    }
}
