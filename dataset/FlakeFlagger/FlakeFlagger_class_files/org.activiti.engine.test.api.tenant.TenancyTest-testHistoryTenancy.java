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

    public void testHistoryTenancy(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){String processDefinitionIdWithTenant=deployTestProcessWithTestTenant();int nrOfProcessInstancesWithTenant=3;for (int i=0;i < nrOfProcessInstancesWithTenant;i++){runtimeService.startProcessInstanceById(processDefinitionIdWithTenant);}String processDefinitionIdNoTenant=deployOneTaskTestProcess();int nrOfProcessInstancesNoTenant=2;for (int i=0;i < nrOfProcessInstancesNoTenant;i++){runtimeService.startProcessInstanceById(processDefinitionIdNoTenant);}for (Task task:taskService.createTaskQuery().list()){taskService.complete(task.getId());}assertEquals(TEST_TENANT_ID,historyService.createHistoricProcessInstanceQuery().processDefinitionId(processDefinitionIdWithTenant).list().get(0).getTenantId());assertEquals("",historyService.createHistoricProcessInstanceQuery().processDefinitionId(processDefinitionIdNoTenant).list().get(0).getTenantId());assertEquals(nrOfProcessInstancesWithTenant + nrOfProcessInstancesNoTenant,historyService.createHistoricProcessInstanceQuery().list().size());assertEquals(nrOfProcessInstancesWithTenant,historyService.createHistoricProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).list().size());assertEquals(nrOfProcessInstancesWithTenant,historyService.createHistoricProcessInstanceQuery().processInstanceTenantIdLike("%e%").list().size());assertEquals(nrOfProcessInstancesNoTenant,historyService.createHistoricProcessInstanceQuery().processInstanceWithoutTenantId().list().size());assertEquals(TEST_TENANT_ID,historyService.createHistoricTaskInstanceQuery().processDefinitionId(processDefinitionIdWithTenant).list().get(0).getTenantId());assertEquals("",historyService.createHistoricTaskInstanceQuery().processDefinitionId(processDefinitionIdNoTenant).list().get(0).getTenantId());assertEquals(nrOfProcessInstancesWithTenant + nrOfProcessInstancesNoTenant,historyService.createHistoricTaskInstanceQuery().list().size());assertEquals(nrOfProcessInstancesWithTenant,historyService.createHistoricTaskInstanceQuery().taskTenantId(TEST_TENANT_ID).list().size());assertEquals(nrOfProcessInstancesWithTenant,historyService.createHistoricTaskInstanceQuery().taskTenantIdLike("my%").list().size());assertEquals(nrOfProcessInstancesNoTenant,historyService.createHistoricTaskInstanceQuery().taskWithoutTenantId().list().size());List<HistoricActivityInstance> activityInstances=historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionIdWithTenant).list();for (HistoricActivityInstance historicActivityInstance:activityInstances){assertEquals(TEST_TENANT_ID,historicActivityInstance.getTenantId());}assertEquals("",historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionIdNoTenant).list().get(0).getTenantId());assertEquals(3 * (nrOfProcessInstancesWithTenant + nrOfProcessInstancesNoTenant),historyService.createHistoricActivityInstanceQuery().list().size());assertEquals(3 * nrOfProcessInstancesWithTenant,historyService.createHistoricActivityInstanceQuery().activityTenantId(TEST_TENANT_ID).list().size());assertEquals(3 * nrOfProcessInstancesWithTenant,historyService.createHistoricActivityInstanceQuery().activityTenantIdLike("my%").list().size());assertEquals(3 * nrOfProcessInstancesNoTenant,historyService.createHistoricActivityInstanceQuery().activityWithoutTenantId().list().size());}}
}
