@Deployment public void testUnstructuredConcurrencyTwoJoins(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("unstructuredConcurrencyTwoJoins");
  assertTrue(processInstance.isEnded());
}
