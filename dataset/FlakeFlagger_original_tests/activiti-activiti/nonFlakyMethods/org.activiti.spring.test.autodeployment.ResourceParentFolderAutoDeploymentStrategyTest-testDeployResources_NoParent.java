@Test public void testDeployResources_NoParent(){
  final Resource[] resources=new Resource[]{resourceMock1,resourceMock2,resourceMock3};
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
  when(fileMock1.getParentFile()).thenReturn(null);
  when(fileMock2.getParentFile()).thenReturn(parentFile2Mock);
  when(parentFile2Mock.isDirectory()).thenReturn(false);
  when(fileMock3.getParentFile()).thenReturn(null);
  verify(repositoryServiceMock,times(3)).createDeployment();
  verify(deploymentBuilderMock,times(3)).enableDuplicateFiltering();
  verify(deploymentBuilderMock,times(1)).name(deploymentNameHint + "." + resourceName1);
  verify(deploymentBuilderMock,times(1)).name(deploymentNameHint + "." + resourceName2);
  verify(deploymentBuilderMock,times(1)).name(deploymentNameHint + "." + resourceName3);
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName1),isA(Resource.class));
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName2),isA(Resource.class));
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName3),isA(Resource.class));
  verify(deploymentBuilderMock,times(3)).deploy();
}
