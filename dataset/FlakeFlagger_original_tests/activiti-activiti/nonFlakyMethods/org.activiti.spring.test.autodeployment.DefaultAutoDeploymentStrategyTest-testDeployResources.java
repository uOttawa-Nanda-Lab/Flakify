@Test public void testDeployResources(){
  final Resource[] resources=new Resource[]{resourceMock1,resourceMock2,resourceMock3,resourceMock4,resourceMock5};
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
  verify(repositoryServiceMock).createDeployment();
  verify(deploymentBuilderMock).enableDuplicateFiltering();
  verify(deploymentBuilderMock).name(deploymentNameHint);
  verify(deploymentBuilderMock).addInputStream(eq(resourceName1),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName2),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName3),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName4),isA(Resource.class));
  verify(deploymentBuilderMock).addInputStream(eq(resourceName5),isA(Resource.class));
  verify(deploymentBuilderMock).deploy();
}
