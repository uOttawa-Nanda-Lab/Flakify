public void testDeploymentPersistence(){
  Deployment deployment=repositoryService.createDeployment().name("strings").addString("org/activiti/test/HelloWorld.string","hello world").addString("org/activiti/test/TheAnswer.string","42").deploy();
  List<Deployment> deployments=repositoryService.createDeploymentQuery().list();
  assertEquals(1,deployments.size());
  deployment=deployments.get(0);
  assertEquals("strings",deployment.getName());
  assertNotNull(deployment.getDeploymentTime());
  String deploymentId=deployment.getId();
  List<String> resourceNames=repositoryService.getDeploymentResourceNames(deploymentId);
  Set<String> expectedResourceNames=new HashSet<String>();
  expectedResourceNames.add("org/activiti/test/HelloWorld.string");
  expectedResourceNames.add("org/activiti/test/TheAnswer.string");
  assertEquals(expectedResourceNames,new HashSet<String>(resourceNames));
  InputStream resourceStream=repositoryService.getResourceAsStream(deploymentId,"org/activiti/test/HelloWorld.string");
  assertTrue(Arrays.equals("hello world".getBytes(),IoUtil.readInputStream(resourceStream,"test")));
  resourceStream=repositoryService.getResourceAsStream(deploymentId,"org/activiti/test/TheAnswer.string");
  assertTrue(Arrays.equals("42".getBytes(),IoUtil.readInputStream(resourceStream,"test")));
  repositoryService.deleteDeployment(deploymentId);
}
