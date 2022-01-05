@Test public void testDeployResources_Joined(){
  final Resource[] resources=new Resource[]{resourceMock1,resourceMock2};
  when(fileMock1.getParentFile()).thenReturn(parentFile1Mock);
  when(fileMock2.getParentFile()).thenReturn(parentFile1Mock);
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
  verify(repositoryServiceMock,times(1)).createDeployment();
  verify(deploymentBuilderMock,times(1)).enableDuplicateFiltering();
  verify(deploymentBuilderMock,times(1)).name(deploymentNameHint + "." + parentFilename1);
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName1),isA(Resource.class));
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName2),isA(Resource.class));
  verify(deploymentBuilderMock,times(1)).deploy();
}
