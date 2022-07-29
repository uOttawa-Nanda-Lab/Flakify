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

    public void testExecutionTenancy() {
		String processDefinitionId = deployTestProcessWithTwoTasksWithTestTenant();
		int nrOfProcessInstancesWithTenant = 4;
		for (int i = 0; i < nrOfProcessInstancesWithTenant; i++) {
			runtimeService.startProcessInstanceById(processDefinitionId);
		}
		String processDefinitionIdNoTenant = deployTwoTasksTestProcess();
		int nrOfProcessInstancesNoTenant = 2;
		for (int i = 0; i < nrOfProcessInstancesNoTenant; i++) {
			runtimeService.startProcessInstanceById(processDefinitionIdNoTenant);
		}
		assertEquals(TEST_TENANT_ID, runtimeService.createExecutionQuery().processDefinitionId(processDefinitionId)
				.list().get(0).getTenantId());
		assertEquals("", runtimeService.createExecutionQuery().processDefinitionId(processDefinitionIdNoTenant).list()
				.get(0).getTenantId());
		assertEquals(3 * (nrOfProcessInstancesNoTenant + nrOfProcessInstancesWithTenant),
				runtimeService.createExecutionQuery().list().size());
		assertEquals(3 * nrOfProcessInstancesNoTenant,
				runtimeService.createExecutionQuery().executionWithoutTenantId().list().size());
		assertEquals(3 * nrOfProcessInstancesWithTenant,
				runtimeService.createExecutionQuery().executionTenantId(TEST_TENANT_ID).list().size());
		assertEquals(3 * nrOfProcessInstancesWithTenant,
				runtimeService.createExecutionQuery().executionTenantIdLike("%en%").list().size());
		assertEquals(TEST_TENANT_ID, runtimeService.createProcessInstanceQuery()
				.processDefinitionId(processDefinitionId).list().get(0).getTenantId());
		assertEquals(nrOfProcessInstancesNoTenant + nrOfProcessInstancesWithTenant,
				runtimeService.createProcessInstanceQuery().list().size());
		assertEquals(nrOfProcessInstancesNoTenant,
				runtimeService.createProcessInstanceQuery().processInstanceWithoutTenantId().list().size());
		assertEquals(nrOfProcessInstancesWithTenant,
				runtimeService.createProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).list().size());
		assertEquals(nrOfProcessInstancesWithTenant,
				runtimeService.createProcessInstanceQuery().processInstanceTenantIdLike("%en%").list().size());
	}
}
