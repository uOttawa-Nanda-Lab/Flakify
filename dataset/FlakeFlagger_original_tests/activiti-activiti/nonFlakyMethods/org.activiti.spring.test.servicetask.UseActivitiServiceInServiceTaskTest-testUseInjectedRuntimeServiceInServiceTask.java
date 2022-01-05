/** 
 * This test will use the dependency injection of Spring to inject the runtime service in the Java delegate.
 */
@Deployment public void testUseInjectedRuntimeServiceInServiceTask(){
  runtimeService.startProcessInstanceByKey("startProcessFromDelegate");
  List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().list();
  assertEquals(2,processInstances.size());
  boolean startProcessFromDelegateFound=false;
  boolean oneTaskProcessFound=false;
  for (  ProcessInstance processInstance : processInstances) {
    ProcessDefinition processDefinition=repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
    if (processDefinition.getKey().equals("startProcessFromDelegate")) {
      startProcessFromDelegateFound=true;
    }
 else     if (processDefinition.getKey().equals("oneTaskProcess")) {
      oneTaskProcessFound=true;
    }
  }
  assertTrue(startProcessFromDelegateFound);
  assertTrue(oneTaskProcessFound);
}
