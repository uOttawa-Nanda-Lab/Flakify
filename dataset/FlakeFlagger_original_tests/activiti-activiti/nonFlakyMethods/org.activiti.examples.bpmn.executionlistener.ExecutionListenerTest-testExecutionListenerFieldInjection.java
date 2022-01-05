@Deployment(resources={"org/activiti/examples/bpmn/executionlistener/ExecutionListenersFieldInjectionProcess.bpmn20.xml"}) public void testExecutionListenerFieldInjection(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("myVar","listening!");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionListenersProcess",variables);
  Object varSetByListener=runtimeService.getVariable(processInstance.getId(),"var");
  assertNotNull(varSetByListener);
  assertTrue(varSetByListener instanceof String);
  assertEquals("Yes, I am listening!",varSetByListener);
}
