@Test public void testDeployResourcesIOExceptionWhenCreatingMapFallsBackToResourceName() throws Exception {
  when(resourceMock3.getFile()).thenThrow(new IOException());
  when(resourceMock3.getFilename()).thenReturn(resourceName3);
  final Resource[] resources=new Resource[]{resourceMock3};
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
  verify(repositoryServiceMock).createDeployment();
  verify(deploymentBuilderMock).enableDuplicateFiltering();
  verify(deploymentBuilderMock).name(deploymentNameHint + "." + resourceName3);
  verify(deploymentBuilderMock).addInputStream(eq(resourceName3),any(Resource.class));
  verify(deploymentBuilderMock).deploy();
}
