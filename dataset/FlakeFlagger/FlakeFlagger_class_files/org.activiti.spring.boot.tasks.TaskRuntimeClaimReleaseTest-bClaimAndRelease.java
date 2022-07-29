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
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskRuntimeClaimReleaseTest {

    private static String currentTaskId;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private TaskAdminRuntime taskAdminRuntime;
    @Autowired
    private SecurityManager securityManager;

    @Test @WithUserDetails(value="salaboy", userDetailsServiceBeanName="myUserDetailsService") public void bClaimAndRelease(){String authenticatedUserId=securityManager.getAuthenticatedUserId();Task claimedTask=taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(currentTaskId).build());assertThat(claimedTask.getAssignee()).isEqualTo(authenticatedUserId);assertThat(claimedTask.getStatus()).isEqualTo(Task.TaskStatus.ASSIGNED);Task releasedTask=taskRuntime.release(TaskPayloadBuilder.release().withTaskId(claimedTask.getId()).build());assertThat(releasedTask.getAssignee()).isNull();assertThat(releasedTask.getStatus()).isEqualTo(Task.TaskStatus.CREATED);}
}
