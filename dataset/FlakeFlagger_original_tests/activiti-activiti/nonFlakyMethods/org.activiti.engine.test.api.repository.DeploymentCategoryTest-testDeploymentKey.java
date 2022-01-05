public void testDeploymentKey(){
  String noKeyDeploymentId=null;
  String deploymentOneId=null;
  String deploymentTwoV1Id=null;
  String deploymentTwoV2Id=null;
  String deploymentTwoNoKey=null;
  try {
    noKeyDeploymentId=repositoryService.createDeployment().name("0").addClasspathResource("org/activiti/engine/test/service/oneTaskProcess.bpmn20.xml").deploy().getId();
    deploymentOneId=repositoryService.createDeployment().name("1").key("one").addClasspathResource("org/activiti/engine/test/repository/one.bpmn20.xml").deploy().getId();
    deploymentTwoV1Id=repositoryService.createDeployment().name("2v1").key("two").addClasspathResource("org/activiti/engine/test/repository/two.bpmn20.xml").deploy().getId();
    deploymentTwoV2Id=repositoryService.createDeployment().name("2v2").key("two").addClasspathResource("org/activiti/engine/test/repository/two.bpmn20.xml").deploy().getId();
    DeploymentQuery query=repositoryService.createDeploymentQuery();
    assertEquals(4,query.list().size());
    Set<String> deploymentNames=getDeploymentNames(repositoryService.createDeploymentQuery().deploymentKey("one").list());
    Set<String> expectedDeploymentNames=new HashSet<String>();
    expectedDeploymentNames.add("1");
    assertEquals(expectedDeploymentNames,deploymentNames);
    deploymentTwoNoKey=repositoryService.createDeployment().name("noCategory").addClasspathResource("org/activiti/engine/test/repository/two.bpmn20.xml").deploy().getId();
    Deployment deploymentNoCategory=repositoryService.createDeploymentQuery().deploymentId(deploymentTwoNoKey).singleResult();
    assertNull(deploymentNoCategory.getCategory());
    repositoryService.setDeploymentKey(deploymentTwoNoKey,"newKey");
    deploymentNoCategory=repositoryService.createDeploymentQuery().deploymentId(deploymentTwoNoKey).singleResult();
    assertEquals("newKey",deploymentNoCategory.getKey());
  }
  finally {
    if (noKeyDeploymentId != null)     undeploy(noKeyDeploymentId);
    if (deploymentOneId != null)     undeploy(deploymentOneId);
    if (deploymentTwoV1Id != null)     undeploy(deploymentTwoV1Id);
    if (deploymentTwoV2Id != null)     undeploy(deploymentTwoV2Id);
    if (deploymentTwoNoKey != null)     undeploy(deploymentTwoNoKey);
  }
}
