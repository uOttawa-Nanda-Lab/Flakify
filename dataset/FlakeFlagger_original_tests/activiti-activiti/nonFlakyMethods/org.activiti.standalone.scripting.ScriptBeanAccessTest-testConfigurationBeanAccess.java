@Deployment public void testConfigurationBeanAccess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("ScriptBeanAccess");
  assertEquals("myValue",runtimeService.getVariable(pi.getId(),"myVariable"));
}
