@Test public void testDeployResources(){
  final Resource[] resources=new Resource[]{resourceMock1,resourceMock2,resourceMock3,resourceMock4,resourceMock5};
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
  verify(repositoryServiceMock,times(5)).createDeployment();
  verify(deploymentBuilderMock,times(5)).enableDuplicateFiltering();
  verify(deploymentBuilderMock,times(1)).name(resourceName1);
  verify(deploymentBuilderMock,times(1)).name(resourceName2);
  verify(deploymentBuilderMock,times(1)).name(resourceName3);
  verify(deploymentBuilderMock,times(1)).name(resourceName4);
  verify(deploymentBuilderMock,times(1)).name(resourceName5);
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName1),isA(Resource.class));
  verify(deploymentBuilderMock,times(1)).addInputStream(eq(resourceName2),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName3),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName4),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName5),isA(Resource.class));
  verify(deploymentBuilderMock,times(5)).deploy();
}
