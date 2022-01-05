@Deployment(resources={"org/activiti/engine/test/api/runtime/concurrentExecution.bpmn20.xml"}) public void testExecutionQueryWithProcessVariable(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("x","parent");
  variables.put("xIgnoreCase","PaReNt");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("concurrent",variables);
  List<Execution> concurrentExecutions=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).list();
  assertEquals(3,concurrentExecutions.size());
  for (  Execution execution : concurrentExecutions) {
    if (!((ExecutionEntity)execution).isProcessInstanceType()) {
      runtimeService.setVariableLocal(execution.getId(),"x","child");
      runtimeService.setVariableLocal(execution.getId(),"xIgnoreCase","ChILD");
    }
  }
  assertEquals(2,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).variableValueEquals("x","child").count());
  assertEquals(1,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).variableValueEquals("x","parent").count());
  assertEquals(3,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).processVariableValueEquals("x","parent").count());
  assertEquals(3,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).processVariableValueNotEquals("x","xxx").count());
  assertEquals(0,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).processVariableValueEquals("child").count());
  assertEquals(3,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).processVariableValueEquals("parent").count());
  assertEquals(0,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).processVariableValueEqualsIgnoreCase("xIgnoreCase","CHILD").count());
  assertEquals(3,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).processVariableValueEqualsIgnoreCase("xIgnoreCase","PARENT").count());
  assertEquals(0,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).processVariableValueNotEqualsIgnoreCase("xIgnoreCase","paRent").count());
  assertEquals(3,runtimeService.createExecutionQuery().processInstanceId(pi.getId()).processVariableValueNotEqualsIgnoreCase("xIgnoreCase","chilD").count());
}
