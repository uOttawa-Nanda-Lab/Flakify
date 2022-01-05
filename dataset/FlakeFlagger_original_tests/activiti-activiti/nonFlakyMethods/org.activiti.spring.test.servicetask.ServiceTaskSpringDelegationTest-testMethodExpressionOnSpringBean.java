@Deployment public void testMethodExpressionOnSpringBean(){
  ProcessInstance procInst=runtimeService.startProcessInstanceByKey("methodExpressionOnSpringBean");
  assertEquals("ACTIVITI BPMN 2.0 PROCESS ENGINE",runtimeService.getVariable(procInst.getId(),"myVar"));
}
