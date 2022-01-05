@Test public void testDetermineResourceNameWithExceptionFailsGracefully() throws Exception {
  when(resourceMock3.getFile()).thenThrow(new IOException());
  when(resourceMock3.getFilename()).thenReturn(resourceName3);
  final Resource[] resources=new Resource[]{resourceMock3};
  deploymentStrategy.deployResources(deploymentNameHint,resources,repositoryServiceMock);
}
