package org.activiti.spring.boot.process;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessAdminRuntime;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.process.runtime.conf.ProcessRuntimeConfiguration;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.spring.boot.RuntimeTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration
public class ProcessRuntimeTest {

    private static final String CATEGORIZE_PROCESS = "categorizeProcess";
    private static final String CATEGORIZE_HUMAN_PROCESS = "categorizeHumanProcess";

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private ProcessAdminRuntime processAdminRuntime;

    @Autowired
    private UserDetailsService userDetailsService;

    @Before
    public void init() {

        //Reset test variables
        RuntimeTestConfiguration.processImageConnectorExecuted = false;
        RuntimeTestConfiguration.tagImageConnectorExecuted = false;
        RuntimeTestConfiguration.discardImageConnectorExecuted = false;

    }

    @Test @WithUserDetails(value="salaboy", userDetailsServiceBeanName="myUserDetailsService") public void getProcessInstances(){Page<ProcessInstance> processInstancePage=processRuntime.processInstances(Pageable.of(0,50));assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(0);processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey(CATEGORIZE_HUMAN_PROCESS).withVariable("expectedKey",true).withBusinessKey("my business key").build());processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(1);processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().withBusinessKey("other key").build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(0);processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().withBusinessKey("my business key").build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(1);processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().suspended().build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(0);processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().active().build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(1);processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().active().suspended().build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(1);ProcessInstance processInstance=processInstancePage.getContent().get(0);ProcessInstance suspendedProcessInstance=processRuntime.suspend(ProcessPayloadBuilder.suspend(processInstance));assertThat(suspendedProcessInstance).isNotNull();assertThat(suspendedProcessInstance.getStatus()).isEqualTo(ProcessInstance.ProcessInstanceStatus.SUSPENDED);processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().active().build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(0);processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().suspended().build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(1);processRuntime.resume(ProcessPayloadBuilder.resume(processInstance));processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().suspended().build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(0);processInstancePage=processRuntime.processInstances(Pageable.of(0,50),ProcessPayloadBuilder.processInstances().active().build());assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(1);ProcessInstance getSingleProcessInstance=processRuntime.processInstance(processInstance.getId());assertThat(getSingleProcessInstance).isNotNull();assertThat(getSingleProcessInstance.getStatus()).isEqualTo(ProcessInstance.ProcessInstanceStatus.RUNNING);ProcessInstance deletedProcessInstance=processRuntime.delete(ProcessPayloadBuilder.delete(getSingleProcessInstance));assertThat(deletedProcessInstance).isNotNull();processInstancePage=processRuntime.processInstances(Pageable.of(0,50));assertThat(processInstancePage).isNotNull();assertThat(processInstancePage.getContent()).hasSize(0);}


}
