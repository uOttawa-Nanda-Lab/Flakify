@Deployment public void testSkipExpression(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("input","right");
  variables.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);
  variables.put("skipLeft",true);
  variables.put("skipRight",false);
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("testSkipExpression",variables);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("task left",task.getName());
}
