public void testTimerShouldNotBeRemovedWhenUndeployingOldVersion() throws Exception {
  String processXml=new String(IoUtil.readInputStream(getClass().getResourceAsStream("StartTimerEventTest.testTimerShouldNotBeRemovedWhenUndeployingOldVersion.bpmn20.xml"),""));
  String firstDeploymentId=repositoryService.createDeployment().addInputStream("StartTimerEventTest.testVersionUpgradeShouldCancelJobs.bpmn20.xml",new ByteArrayInputStream(processXml.getBytes())).deploy().getId();
  TimerJobQuery jobQuery=managementService.createTimerJobQuery();
  assertEquals(1,jobQuery.count());
  String processChanged=processXml.replaceAll("beforeChange","changed");
  String secondDeploymentId=repositoryService.createDeployment().addInputStream("StartTimerEventTest.testVersionUpgradeShouldCancelJobs.bpmn20.xml",new ByteArrayInputStream(processChanged.getBytes())).deploy().getId();
  assertEquals(1,jobQuery.count());
  repositoryService.deleteDeployment(firstDeploymentId,true);
  assertEquals(1,jobQuery.count());
  cleanDB();
  repositoryService.deleteDeployment(secondDeploymentId,true);
}
