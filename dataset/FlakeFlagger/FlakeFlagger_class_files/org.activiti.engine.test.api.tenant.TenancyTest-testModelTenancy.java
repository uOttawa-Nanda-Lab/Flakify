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

    public void testModelTenancy(){int nrOfModelsWithTenant=3;for (int i=0;i < nrOfModelsWithTenant;i++){Model model=repositoryService.newModel();model.setName(i + "");model.setTenantId(TEST_TENANT_ID);repositoryService.saveModel(model);}int nrOfModelsWithoutTenant=5;for (int i=0;i < nrOfModelsWithoutTenant;i++){Model model=repositoryService.newModel();model.setName(i + "");repositoryService.saveModel(model);}assertEquals(nrOfModelsWithoutTenant + nrOfModelsWithTenant,repositoryService.createModelQuery().list().size());assertEquals(nrOfModelsWithoutTenant,repositoryService.createModelQuery().modelWithoutTenantId().list().size());assertEquals(nrOfModelsWithTenant,repositoryService.createModelQuery().modelTenantId(TEST_TENANT_ID).list().size());assertEquals(nrOfModelsWithTenant,repositoryService.createModelQuery().modelTenantIdLike("my%").list().size());assertEquals(0,repositoryService.createModelQuery().modelTenantId("a%").list().size());for (Model model:repositoryService.createModelQuery().list()){repositoryService.deleteModel(model.getId());}}
}
