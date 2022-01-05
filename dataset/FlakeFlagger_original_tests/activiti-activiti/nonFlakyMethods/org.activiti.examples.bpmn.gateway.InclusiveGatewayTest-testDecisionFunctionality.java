/** 
 * The test process has an OR gateway where, the 'input' variable is used to select the expected outgoing sequence flow.
 */
@Deployment public void testDecisionFunctionality(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("input",1);
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("inclusiveGateway",variables);
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertEquals(3,tasks.size());
  Map<String,String> expectedMessages=new HashMap<String,String>();
  expectedMessages.put(TASK1_NAME,TASK1_NAME);
  expectedMessages.put(TASK2_NAME,TASK2_NAME);
  expectedMessages.put(TASK3_NAME,TASK3_NAME);
  for (  Task task : tasks) {
    expectedMessages.remove(task.getName());
  }
  assertEquals(0,expectedMessages.size());
  variables.put("input",2);
  pi=runtimeService.startProcessInstanceByKey("inclusiveGateway",variables);
  tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertEquals(2,tasks.size());
  expectedMessages=new HashMap<String,String>();
  expectedMessages.put(TASK2_NAME,TASK2_NAME);
  expectedMessages.put(TASK3_NAME,TASK3_NAME);
  for (  Task task : tasks) {
    expectedMessages.remove(task.getName());
  }
  assertEquals(0,expectedMessages.size());
  variables.put("input",3);
  pi=runtimeService.startProcessInstanceByKey("inclusiveGateway",variables);
  tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertEquals(1,tasks.size());
  expectedMessages=new HashMap<String,String>();
  expectedMessages.put(TASK3_NAME,TASK3_NAME);
  for (  Task task : tasks) {
    expectedMessages.remove(task.getName());
  }
  assertEquals(0,expectedMessages.size());
  variables.put("input",4);
  try {
    runtimeService.startProcessInstanceByKey("inclusiveGateway",variables);
    fail();
  }
 catch (  ActivitiException e) {
  }
}
