/** 
 * The test process has an XOR gateway where, the 'input' variable is used to select one of the outgoing sequence flow. Every one of those sequence flow goes to another task, allowing us to test the decision very easily.
 */
@Deployment public void testDecisionFunctionality(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("input",1);
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("exclusiveGateway",variables);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Send e-mail for more information",task.getName());
  variables.put("input",2);
  pi=runtimeService.startProcessInstanceByKey("exclusiveGateway",variables);
  task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Check account balance",task.getName());
  variables.put("input",3);
  pi=runtimeService.startProcessInstanceByKey("exclusiveGateway",variables);
  task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Call customer",task.getName());
  variables.put("input",4);
  try {
    runtimeService.startProcessInstanceByKey("exclusiveGateway",variables);
    fail();
  }
 catch (  ActivitiException e) {
  }
}
