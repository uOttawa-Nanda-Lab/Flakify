package org.activiti.spring.boot.tasks;

import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.runtime.shared.security.SecurityManager;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskAdminRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.spring.boot.RuntimeTestConfiguration;
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
public class TaskRuntimeStandaloneTaskTest {

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private TaskAdminRuntime taskAdminRuntime;

    @Autowired
    private SecurityManager securityManager;


    @Test @WithUserDetails(value="salaboy", userDetailsServiceBeanName="myUserDetailsService") public void aCreateStandaloneTaskForSalaboy(){String authenticatedUserId=securityManager.getAuthenticatedUserId();Task standAloneTask=taskRuntime.create(TaskPayloadBuilder.create().withName("cure Skipper").withAssignee(authenticatedUserId).build());assertThat(RuntimeTestConfiguration.createdTasks).contains(standAloneTask.getId());Page<Task> tasks=taskRuntime.tasks(Pageable.of(0,50));assertThat(tasks.getContent()).hasSize(1);Task task=tasks.getContent().get(0);assertThat(task.getAssignee()).isEqualTo(authenticatedUserId);assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.ASSIGNED);Task deletedTask=taskRuntime.delete(TaskPayloadBuilder.delete().withTaskId(task.getId()).withReason("test clean up").build());assertThat(deletedTask).isNotNull();assertThat(deletedTask.getStatus()).isEqualTo(Task.TaskStatus.DELETED);tasks=taskRuntime.tasks(Pageable.of(0,50));assertThat(tasks.getContent()).hasSize(0);}

}
