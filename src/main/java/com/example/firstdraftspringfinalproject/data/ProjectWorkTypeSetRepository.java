package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Project;
import com.example.firstdraftspringfinalproject.models.ProjectWorkTypeSet;
import com.example.firstdraftspringfinalproject.models.WorkType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectWorkTypeSetRepository extends CrudRepository<ProjectWorkTypeSet, Integer> {

    Integer findIdByProjectAndWorkType(Project project, WorkType workType);

    ProjectWorkTypeSet findByProjectAndWorkType(Project project, WorkType workType);

    List<ProjectWorkTypeSet> findByProject(Project project);
}
