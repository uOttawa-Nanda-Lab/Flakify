@Test public void testDeployResources_Mixed(){
  final Resource[] resources=new Resource[]{resourceMock1,resourceMock2,resourceMock3};
  when(fileMock1.getParentFile()).thenReturn(parentFile1Mock);
  when(fileMock2.getParentFile()).thenReturn(parentFile2Mock);
  when(fileMock3.getParentFile()).thenReturn(parentFile1Mock);
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
  verify(repositoryServiceMock,times(2)).createDeployment();
  verify(deploymentBuilderMock,times(2)).enableDuplicateFiltering();
  verify(deploymentBuilderMock,times(1)).name(deploymentNameHint + "." + parentFilename1);
  verify(deploymentBuilderMock,times(1)).name(deploymentNameHint + "." + parentFilename2);
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName1),isA(Resource.class));
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName2),isA(Resource.class));
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName3),isA(Resource.class));
  verify(deploymentBuilderMock,times(2)).deploy();
}
