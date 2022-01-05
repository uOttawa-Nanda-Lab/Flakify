@Deployment public void testNoSequenceFlowSelected(){
  try {
    runtimeService.startProcessInstanceByKey("exclusiveGwNoSeqFlowSelected",CollectionUtil.singletonMap("input",4));
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresent("No outgoing sequence flow of the exclusive gateway " + "'exclusiveGw' could be selected for continuing the process",e.getMessage());
  }
}
