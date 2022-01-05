@Test public void testDeployResourcesNoResources(){
  final Resource[] resources=new Resource[]{};
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
  verify(repositoryServiceMock,times(1)).createDeployment();
  verify(deploymentBuilderMock,times(1)).enableDuplicateFiltering();
  verify(deploymentBuilderMock,times(1)).name(deploymentNameHint);
  verify(deploymentBuilderMock,never()).addInputStream(isA(String.class),isA(InputStream.class));
  verify(deploymentBuilderMock,never()).addInputStream(eq(resourceName2),isA(InputStream.class));
  verify(deploymentBuilderMock,never()).addZipInputStream(isA(ZipInputStream.class));
  verify(deploymentBuilderMock,times(1)).deploy();
}
