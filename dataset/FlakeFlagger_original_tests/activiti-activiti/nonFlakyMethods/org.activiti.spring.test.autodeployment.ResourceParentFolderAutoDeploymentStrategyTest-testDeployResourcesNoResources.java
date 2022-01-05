@Test public void testDeployResourcesNoResources(){
  final Resource[] resources=new Resource[]{};
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
  verify(repositoryServiceMock,never()).createDeployment();
  verify(deploymentBuilderMock,never()).enableDuplicateFiltering();
  verify(deploymentBuilderMock,never()).name(deploymentNameHint);
  verify(deploymentBuilderMock,never()).addInputStream(isA(String.class),isA(InputStream.class));
  verify(deploymentBuilderMock,never()).addInputStream(eq(resourceName2),isA(InputStream.class));
  verify(deploymentBuilderMock,never()).addZipInputStream(isA(ZipInputStream.class));
  verify(deploymentBuilderMock,never()).deploy();
}
