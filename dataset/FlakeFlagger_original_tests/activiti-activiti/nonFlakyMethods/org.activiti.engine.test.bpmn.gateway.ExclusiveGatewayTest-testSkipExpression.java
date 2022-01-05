@Deployment public void testSkipExpression(){
  for (int i=1; i <= 3; i++) {
    Map<String,Object> variables=new HashMap<String,Object>();
    variables.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);
    variables.put("input",-i);
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("exclusiveGwDivergingSkipExpression",variables);
    assertEquals("Task " + i,taskService.createTaskQuery().singleResult().getName());
    runtimeService.deleteProcessInstance(pi.getId(),"testing deletion");
  }
}
