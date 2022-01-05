@Deployment public void testModeledVariableScope(){
  Map<String,Object> varMap=new HashMap<String,Object>();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("nestedSubProcess",varMap);
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task in subprocess1",subProcessTask.getName());
  assertEquals("test2",runtimeService.getVariable(subProcessTask.getExecutionId(),"test"));
  assertEquals("test2",runtimeService.getVariables(subProcessTask.getExecutionId()).get("test"));
  assertEquals("test",runtimeService.getVariable(pi.getId(),"test"));
  assertEquals("test",runtimeService.getVariables(pi.getId()).get("test"));
  runtimeService.setVariableLocal(subProcessTask.getExecutionId(),"test","testX");
  assertEquals("testX",runtimeService.getVariable(subProcessTask.getExecutionId(),"test"));
  assertEquals("testX",runtimeService.getVariables(subProcessTask.getExecutionId()).get("test"));
  assertEquals("test",runtimeService.getVariable(pi.getId(),"test"));
  assertEquals("test",runtimeService.getVariables(pi.getId()).get("test"));
  runtimeService.setVariable(pi.getId(),"test","testY");
  assertEquals("testX",runtimeService.getVariable(subProcessTask.getExecutionId(),"test"));
  assertEquals("testX",runtimeService.getVariables(subProcessTask.getExecutionId()).get("test"));
  assertEquals("testY",runtimeService.getVariable(pi.getId(),"test"));
  assertEquals("testY",runtimeService.getVariables(pi.getId()).get("test"));
  taskService.complete(subProcessTask.getId());
  List<Task> subProcessTasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  for (  Task subProcTask : subProcessTasks) {
    if (subProcTask.getName().equals("Task in subprocess2")) {
      assertEquals("test3",runtimeService.getVariable(subProcTask.getExecutionId(),"test"));
      assertEquals("test3",runtimeService.getVariables(subProcTask.getExecutionId()).get("test"));
      assertEquals("testY",runtimeService.getVariable(pi.getId(),"test"));
      assertEquals("testY",runtimeService.getVariables(pi.getId()).get("test"));
    }
 else     if (subProcTask.getName().equals("Task in subprocess3")) {
      assertEquals("test4",runtimeService.getVariable(subProcTask.getExecutionId(),"test"));
      assertEquals("test4",runtimeService.getVariables(subProcTask.getExecutionId()).get("test"));
      assertEquals("testY",runtimeService.getVariable(pi.getId(),"test"));
      assertEquals("testY",runtimeService.getVariables(pi.getId()).get("test"));
    }
 else {
      fail("Unexpected subProcessTask: " + subProcTask);
    }
  }
  for (  Task subProcTask : subProcessTasks) {
    taskService.complete(subProcTask.getId());
  }
}
