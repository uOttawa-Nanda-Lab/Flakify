public void testDeployTwoProcessesWithDuplicateIdAtTheSameTime(){
  try {
    String bpmnResourceName="org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testGetBpmnXmlFileThroughService.bpmn20.xml";
    String bpmnResourceName2="org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testGetBpmnXmlFileThroughService2.bpmn20.xml";
    repositoryService.createDeployment().enableDuplicateFiltering().addClasspathResource(bpmnResourceName).addClasspathResource(bpmnResourceName2).name("duplicateAtTheSameTime").deploy();
    fail();
  }
 catch (  Exception e) {
    assertEquals(0,repositoryService.createDeploymentQuery().count());
  }
}
