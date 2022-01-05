@Deployment public void testUnstructuredConcurrencyTwoForks(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("unstructuredConcurrencyTwoForks");
  assertTrue(processInstance.isEnded());
}
