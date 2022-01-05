@Test public void testDeployResources_AllInOne(){
  final Resource[] resources=new Resource[]{resourceMock1,resourceMock2,resourceMock3,resourceMock4,resourceMock5};
  when(fileMock1.getParentFile()).thenReturn(parentFile1Mock);
  when(fileMock2.getParentFile()).thenReturn(parentFile1Mock);
  when(fileMock3.getParentFile()).thenReturn(parentFile1Mock);
  when(fileMock4.getParentFile()).thenReturn(parentFile1Mock);
  when(fileMock5.getParentFile()).thenReturn(parentFile1Mock);
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
  verify(repositoryServiceMock).createDeployment();
  verify(deploymentBuilderMock).enableDuplicateFiltering();
  verify(deploymentBuilderMock).name(deploymentNameHint + "." + parentFilename1);
  verify(deploymentBuilderMock).addInputStream(eq(resourceName1),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName2),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName3),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName4),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName5),isA(Resource.class));
  verify(deploymentBuilderMock).deploy();
}
