/** 
 * Validation for https://jira.codehaus.org/browse/ACT-2182
 */
public void testNameAndTenantIdSetWhenFetchingVariables(){
  String tenantId="testTenantId";
  String processInstanceName="myProcessInstance";
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml").tenantId(tenantId).deploy().getId();
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("name","Kermit");
  vars.put("age",60);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKeyAndTenantId("oneTaskProcess",vars,tenantId);
  runtimeService.setProcessInstanceName(processInstance.getId(),processInstanceName);
  List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().includeProcessVariables().list();
  assertEquals(1,processInstances.size());
  processInstance=processInstances.get(0);
  assertEquals(processInstanceName,processInstance.getName());
  assertEquals(tenantId,processInstance.getTenantId());
  Map<String,Object> processInstanceVars=processInstance.getProcessVariables();
  assertEquals(2,processInstanceVars.size());
  assertEquals("Kermit",processInstanceVars.get("name"));
  assertEquals(60,processInstanceVars.get("age"));
  List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().list();
  assertEquals(1,historicProcessInstances.size());
  HistoricProcessInstance historicProcessInstance=historicProcessInstances.get(0);
  assertEquals(processInstanceName,historicProcessInstance.getName());
  assertEquals(tenantId,historicProcessInstance.getTenantId());
  Map<String,Object> historicProcessInstanceVars=historicProcessInstance.getProcessVariables();
  assertEquals(2,historicProcessInstanceVars.size());
  assertEquals("Kermit",historicProcessInstanceVars.get("name"));
  assertEquals(60,historicProcessInstanceVars.get("age"));
  repositoryService.deleteDeployment(deploymentId,true);
}
