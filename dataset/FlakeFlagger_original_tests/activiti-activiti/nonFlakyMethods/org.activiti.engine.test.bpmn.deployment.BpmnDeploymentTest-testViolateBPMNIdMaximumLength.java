public void testViolateBPMNIdMaximumLength(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/deployment/definitionWithLongTargetNamespace.bpmn20.xml").deploy();
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresent(Problems.BPMN_MODEL_TARGET_NAMESPACE_TOO_LONG,e.getMessage());
  }
  assertEquals(0,repositoryService.createDeploymentQuery().count());
}
