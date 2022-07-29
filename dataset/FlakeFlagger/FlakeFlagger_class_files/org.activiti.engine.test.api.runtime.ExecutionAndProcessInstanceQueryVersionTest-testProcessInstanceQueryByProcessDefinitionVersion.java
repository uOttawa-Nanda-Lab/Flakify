package org.activiti.engine.test.api.runtime;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;

public class ExecutionAndProcessInstanceQueryVersionTest extends PluggableActivitiTestCase {

  private static final String PROCESS_DEFINITION_KEY = "oneTaskProcess";
  private static final String DEPLOYMENT_FILE_PATH = "org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml";

  private org.activiti.engine.repository.Deployment oldDeployment;
  private org.activiti.engine.repository.Deployment newDeployment;

  protected void setUp() throws Exception {
    super.setUp();
    oldDeployment = repositoryService.createDeployment()
      .addClasspathResource(DEPLOYMENT_FILE_PATH)
      .deploy();
    
    runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY).getId();
    
    newDeployment = repositoryService.createDeployment()
          .addClasspathResource(DEPLOYMENT_FILE_PATH)
          .deploy();
        
    runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY).getId();
  }

  protected void tearDown() throws Exception {
    repositoryService.deleteDeployment(oldDeployment.getId(), true);
    repositoryService.deleteDeployment(newDeployment.getId(), true);
  }

  public void testProcessInstanceQueryByProcessDefinitionVersion() {
	assertEquals(1, runtimeService.createProcessInstanceQuery().processDefinitionVersion(1).count());
	assertEquals(1, runtimeService.createProcessInstanceQuery().processDefinitionVersion(2).count());
	assertEquals(0, runtimeService.createProcessInstanceQuery().processDefinitionVersion(3).count());
	assertEquals(1, runtimeService.createProcessInstanceQuery().processDefinitionVersion(1).count());
	assertEquals(1, runtimeService.createProcessInstanceQuery().processDefinitionVersion(2).list().size());
	assertEquals(0, runtimeService.createProcessInstanceQuery().processDefinitionVersion(3).list().size());
}
}