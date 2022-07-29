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

    public void testGetLatestProcessDefinitionVersionForSameProcessDefinitionKey(){String tenant1="tenant1";String tenant2="tenant2";for (int i=0;i < 4;i++){deployTestProcessWithTestTenant(tenant1);}for (int i=0;i < 2;i++){deployTestProcessWithTestTenant(tenant2);}for (int i=0;i < 3;i++){deployTestProcessWithTwoTasksNoTenant();}ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(tenant1).latestVersion().singleResult();assertEquals(4,processDefinition.getVersion());processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(tenant2).latestVersion().singleResult();assertEquals(2,processDefinition.getVersion());processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionWithoutTenantId().latestVersion().singleResult();assertEquals(3,processDefinition.getVersion());List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().latestVersion().list();assertEquals(3,processDefinitions.size());int tenant1Count=0,tenant2Count=0,noTenantCount=0;for (ProcessDefinition p:processDefinitions){if (p.getTenantId() == null || p.getTenantId().equals(ProcessEngineConfiguration.NO_TENANT_ID)){noTenantCount++;} else if (p.getTenantId().equals(tenant1)){tenant1Count++;} else if (p.getTenantId().equals(tenant2)){tenant2Count++;}}assertEquals(1,tenant1Count);assertEquals(1,tenant2Count);assertEquals(1,noTenantCount);}
}
