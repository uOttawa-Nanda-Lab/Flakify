/** 
 * A testcase to produce and fix issue ACT-862.
 */
@Deployment public void testVariableNamesScope(){
  Map<String,Object> varMap=new HashMap<String,Object>();
  varMap.put("test","test");
  varMap.put("helloWorld","helloWorld");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleSubProcess",varMap);
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  runtimeService.setVariableLocal(pi.getProcessInstanceId(),"mainProcessLocalVariable","Hello World");
  assertEquals("Task in subprocess",subProcessTask.getName());
  runtimeService.setVariableLocal(subProcessTask.getExecutionId(),"subProcessLocalVariable","Hello SubProcess");
  List<String> result=processEngineConfiguration.getCommandExecutor().execute(new GetVariableNamesCommand(pi.getProcessInstanceId(),true));
  assertTrue(result.contains("test"));
  assertTrue(result.contains("helloWorld"));
  assertTrue(result.contains("mainProcessLocalVariable"));
  assertFalse(result.contains("subProcessLocalVariable"));
  result=processEngineConfiguration.getCommandExecutor().execute(new GetVariableNamesCommand(pi.getProcessInstanceId(),false));
  assertTrue(result.contains("test"));
  assertTrue(result.contains("mainProcessLocalVariable"));
  assertTrue(result.contains("helloWorld"));
  assertFalse(result.contains("subProcessLocalVariable"));
  result=processEngineConfiguration.getCommandExecutor().execute(new GetVariableNamesCommand(subProcessTask.getExecutionId(),true));
  assertTrue(result.contains("test"));
  assertTrue(result.contains("subProcessLocalVariable"));
  assertFalse(result.contains("helloWorld"));
  assertFalse(result.contains("mainProcessLocalVariable"));
  result=processEngineConfiguration.getCommandExecutor().execute(new GetVariableNamesCommand(subProcessTask.getExecutionId(),false));
  assertTrue(result.contains("test"));
  assertTrue(result.contains("subProcessLocalVariable"));
  assertTrue(result.contains("helloWorld"));
  assertTrue(result.contains("mainProcessLocalVariable"));
  taskService.complete(subProcessTask.getId());
}
