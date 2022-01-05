@Deployment public void testVariableScope(){
  Map<String,Object> varMap=new HashMap<String,Object>();
  varMap.put("test","test");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleSubProcess",varMap);
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  assertEquals("test2",runtimeService.getVariable(subProcessTask.getExecutionId(),"test"));
  assertEquals("test2",runtimeService.getVariables(subProcessTask.getExecutionId()).get("test"));
  assertEquals("test",runtimeService.getVariable(pi.getId(),"test"));
  assertEquals("test",runtimeService.getVariables(pi.getId()).get("test"));
  runtimeService.setVariableLocal(subProcessTask.getExecutionId(),"test","test3");
  assertEquals("test3",runtimeService.getVariable(subProcessTask.getExecutionId(),"test"));
  assertEquals("test3",runtimeService.getVariables(subProcessTask.getExecutionId()).get("test"));
  assertEquals("test",runtimeService.getVariable(pi.getId(),"test"));
  assertEquals("test",runtimeService.getVariables(pi.getId()).get("test"));
  runtimeService.setVariable(pi.getId(),"test","test4");
  assertEquals("test3",runtimeService.getVariable(subProcessTask.getExecutionId(),"test"));
  assertEquals("test3",runtimeService.getVariables(subProcessTask.getExecutionId()).get("test"));
  assertEquals("test4",runtimeService.getVariable(pi.getId(),"test"));
  assertEquals("test4",runtimeService.getVariables(pi.getId()).get("test"));
  taskService.complete(subProcessTask.getId());
}
