@Deployment(resources={"org/activiti/engine/test/api/runtime/concurrentExecution.bpmn20.xml"}) public void testHistoricVariableInstancesOnParallelExecution(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("rootValue","test");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("concurrent",vars);
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  for (  Task task : tasks) {
    Map<String,Object> variables=new HashMap<String,Object>();
    log.debug("setting variables on task {}, execution {}",task.getId(),task.getExecutionId());
    runtimeService.setVariableLocal(task.getExecutionId(),"parallelValue1",task.getName());
    runtimeService.setVariableLocal(task.getExecutionId(),"parallelValue2","test");
    taskService.complete(task.getId(),variables);
  }
  taskService.complete(taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult().getId());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().variableValueEquals("rootValue","test").count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().variableValueEquals("parallelValue1","Receive Payment").count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().variableValueEquals("parallelValue1","Ship Order").count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().variableValueEquals("parallelValue2","test").count());
}
