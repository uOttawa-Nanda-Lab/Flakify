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

    public void testProcessDefinitionTenancy() {
		String processDefinitionIdWithTenant = deployTestProcessWithTestTenant();
		assertEquals(TEST_TENANT_ID, repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionIdWithTenant).singleResult().getTenantId());
		assertEquals(1, repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(TEST_TENANT_ID)
				.list().size());
		assertEquals(1,
				repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("m%").list().size());
		assertEquals(0, repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("somethingElse%")
				.list().size());
		assertEquals(0,
				repositoryService.createProcessDefinitionQuery().processDefinitionWithoutTenantId().list().size());
		String processDefinitionIdWithoutTenant = deployOneTaskTestProcess();
		assertEquals(2, repositoryService.createProcessDefinitionQuery().list().size());
		assertEquals(1, repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(TEST_TENANT_ID)
				.list().size());
		assertEquals(1,
				repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("m%").list().size());
		assertEquals(0, repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("somethingElse%")
				.list().size());
		assertEquals(1,
				repositoryService.createProcessDefinitionQuery().processDefinitionWithoutTenantId().list().size());
		String processDefinitionIdWithTenant2 = deployTestProcessWithTestTenant();
		assertEquals(3, repositoryService.createProcessDefinitionQuery().list().size());
		assertEquals(2, repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(TEST_TENANT_ID)
				.list().size());
		assertEquals(2,
				repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("m%").list().size());
		assertEquals(0, repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("somethingElse%")
				.list().size());
		assertEquals(1,
				repositoryService.createProcessDefinitionQuery().processDefinitionWithoutTenantId().list().size());
		assertEquals(processDefinitionIdWithTenant2,
				repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess")
						.processDefinitionTenantId(TEST_TENANT_ID).latestVersion().singleResult().getId());
		assertEquals(0, repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess")
				.processDefinitionTenantId("Not a tenant").latestVersion().count());
		assertEquals(processDefinitionIdWithoutTenant,
				repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess")
						.processDefinitionWithoutTenantId().latestVersion().singleResult().getId());
	}
}
