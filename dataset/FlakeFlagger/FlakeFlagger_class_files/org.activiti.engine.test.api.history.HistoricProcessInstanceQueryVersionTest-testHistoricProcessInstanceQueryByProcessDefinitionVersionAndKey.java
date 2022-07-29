package org.activiti.engine.test.api.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;

public class HistoricProcessInstanceQueryVersionTest extends PluggableActivitiTestCase{

  private static final String PROCESS_DEFINITION_KEY = "oneTaskProcess";
  private static final String DEPLOYMENT_FILE_PATH = "org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml";

  private org.activiti.engine.repository.Deployment oldDeployment;
  private org.activiti.engine.repository.Deployment newDeployment;
  private List<String> processInstanceIds;
  
  protected void setUp() throws Exception {
    super.setUp();
    oldDeployment = repositoryService.createDeployment()
      .addClasspathResource(DEPLOYMENT_FILE_PATH)
      .deploy();
    
    processInstanceIds = new ArrayList<String>();
    
    Map<String, Object> startMap = new HashMap<String, Object>();
    startMap.put("test", 123);
    processInstanceIds.add(runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, startMap).getId());
    
    newDeployment = repositoryService.createDeployment()
          .addClasspathResource(DEPLOYMENT_FILE_PATH)
          .deploy();
    
    startMap.clear();
    startMap.put("anothertest", 456);
    processInstanceIds.add(runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, startMap).getId());
  }
  
  protected void tearDown() throws Exception {
    repositoryService.deleteDeployment(oldDeployment.getId(), true);
    repositoryService.deleteDeployment(newDeployment.getId(), true);
  }
  
  public void testHistoricProcessInstanceQueryByProcessDefinitionVersionAndKey() {
	assertEquals(1, historyService.createHistoricProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY)
			.processDefinitionVersion(1).count());
	assertEquals(1, historyService.createHistoricProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY)
			.processDefinitionVersion(2).count());
	assertEquals(0, historyService.createHistoricProcessInstanceQuery().processDefinitionKey("undefined")
			.processDefinitionVersion(1).count());
	assertEquals(0, historyService.createHistoricProcessInstanceQuery().processDefinitionKey("undefined")
			.processDefinitionVersion(2).count());
	assertEquals(1, historyService.createHistoricProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY)
			.processDefinitionVersion(1).list().size());
	assertEquals(1, historyService.createHistoricProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY)
			.processDefinitionVersion(2).list().size());
	assertEquals(0, historyService.createHistoricProcessInstanceQuery().processDefinitionKey("undefined")
			.processDefinitionVersion(1).list().size());
	assertEquals(0, historyService.createHistoricProcessInstanceQuery().processDefinitionKey("undefined")
			.processDefinitionVersion(2).list().size());
}
}