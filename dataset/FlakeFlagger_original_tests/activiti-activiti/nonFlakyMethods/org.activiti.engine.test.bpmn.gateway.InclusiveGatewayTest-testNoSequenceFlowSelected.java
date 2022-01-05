@Deployment public void testNoSequenceFlowSelected(){
  try {
    runtimeService.startProcessInstanceByKey("inclusiveGwNoSeqFlowSelected",CollectionUtil.singletonMap("input",4));
    fail();
  }
 catch (  ActivitiException e) {
  }
}
