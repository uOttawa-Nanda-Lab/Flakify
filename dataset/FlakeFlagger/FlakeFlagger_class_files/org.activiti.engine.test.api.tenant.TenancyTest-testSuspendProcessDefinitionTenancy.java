package org.activiti.engine.test.api.tenant;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;

/**
 * A test case for the various implications of the tenancy support (tenant id column to entities + query support)
 */
public class TenancyTest extends PluggableActivitiTestCase {

    private static final String TEST_TENANT_ID = "myTenantId";

    private List<String> autoCleanedUpDeploymentIds = new ArrayList<String>();

    /**
     * Deploys the one task process with the test tenant id.
     * @return The process definition id of the deployed process definition.
     */
    private String deployTestProcessWithTestTenant() {
        return deployTestProcessWithTestTenant(TEST_TENANT_ID);
    }

    private String deployTestProcessWithTestTenant(String tenantId) {
        String id = repositoryService.createDeployment().addBpmnModel("testProcess.bpmn20.xml",
                                                                      createOneTaskTestProcess()).tenantId(tenantId).deploy().getId();

        autoCleanedUpDeploymentIds.add(id);

        return repositoryService.createProcessDefinitionQuery().deploymentId(id).singleResult().getId();
    }

    private String deployTestProcessWithTwoTasksWithTestTenant() {
        String id = repositoryService.createDeployment().addBpmnModel("testProcess.bpmn20.xml",
                                                                      createTwoTasksTestProcess()).tenantId(TEST_TENANT_ID).deploy().getId();

        autoCleanedUpDeploymentIds.add(id);

        return repositoryService.createProcessDefinitionQuery().deploymentId(id).singleResult().getId();
    }

    private String deployTestProcessWithTwoTasksNoTenant() {
        String id = repositoryService.createDeployment().addBpmnModel("testProcess.bpmn20.xml",
                                                                      createTwoTasksTestProcess()).deploy().getId();

        autoCleanedUpDeploymentIds.add(id);

        return repositoryService.createProcessDefinitionQuery().deploymentId(id).singleResult().getId();
    }

    public void testSuspendProcessDefinitionTenancy() {
		String tentanA = "tenantA";
		String tenantB = "tenantB";
		String procDefIdA = deployTestProcessWithTestTenant(tentanA);
		String procDefIdB = deployTestProcessWithTestTenant(tenantB);
		String procDefIdB2 = deployTestProcessWithTestTenant(tenantB);
		repositoryService.suspendProcessDefinitionByKey("oneTaskProcess", tenantB);
		try {
			runtimeService.startProcessInstanceById(procDefIdB);
		} catch (ActivitiException e) {
		}
		try {
			runtimeService.startProcessInstanceById(procDefIdB2);
		} catch (ActivitiException e) {
		}
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(procDefIdA);
		Assert.assertNotNull(processInstance);
		repositoryService.activateProcessDefinitionByKey("oneTaskProcess", tenantB);
		processInstance = runtimeService.startProcessInstanceById(procDefIdB);
		Assert.assertNotNull(processInstance);
		processInstance = runtimeService.startProcessInstanceById(procDefIdB2);
		Assert.assertNotNull(processInstance);
		processInstance = runtimeService.startProcessInstanceById(procDefIdA);
		Assert.assertNotNull(processInstance);
		try {
			repositoryService.suspendProcessDefinitionByKey("oneTaskProcess");
		} catch (ActivitiException e) {
		}
	}
}
