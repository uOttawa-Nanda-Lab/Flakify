@Deployment public void testBasicActivitiSpringIntegration(){
  userBean.hello();
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  assertEquals("Hello from Printer!",runtimeService.getVariable(processInstance.getId(),"myVar"));
}
