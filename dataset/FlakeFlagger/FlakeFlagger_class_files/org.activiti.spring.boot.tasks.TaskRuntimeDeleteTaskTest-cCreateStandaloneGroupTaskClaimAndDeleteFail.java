package org.activiti.spring.boot.tasks;

import org.activiti.api.runtime.shared.NotFoundException;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.runtime.shared.security.SecurityManager;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskAdminRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskRuntimeDeleteTaskTest {

    private static String currentTaskId;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private TaskAdminRuntime taskAdminRuntime;
    @Autowired
    private SecurityManager securityManager;


    @Test @WithUserDetails(value="garth", userDetailsServiceBeanName="myUserDetailsService") public void cCreateStandaloneGroupTaskClaimAndDeleteFail(){Task standAloneTask=taskRuntime.create(TaskPayloadBuilder.create().withName("simple task").withGroup("activitiTeam").build());Page<Task> tasks=taskRuntime.tasks(Pageable.of(0,50));assertThat(tasks.getContent()).hasSize(1);Task task=tasks.getContent().get(0);assertThat(task.getAssignee()).isNull();assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.CREATED);currentTaskId=task.getId();}



}
