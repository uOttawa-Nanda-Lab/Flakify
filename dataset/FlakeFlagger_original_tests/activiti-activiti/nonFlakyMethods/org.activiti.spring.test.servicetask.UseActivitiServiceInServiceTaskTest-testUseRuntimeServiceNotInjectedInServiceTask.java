/** 
 * This test will use the regular mechanism (delegateExecution.getProcessEngine().getRuntimeService()) to obtain the  {@link RuntimeService} to start a new process.
 */
@Deployment public void testUseRuntimeServiceNotInjectedInServiceTask(){
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
