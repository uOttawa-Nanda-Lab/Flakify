@Test public void addInputStreamShouldAddZipInputStreamWhenItsAZipLike(){
  doReturn(deploymentBuilder).when(deploymentBuilder).addZipInputStream(any());
  deploymentBuilder.addInputStream("my.bar",resource);
  verify(deploymentBuilder).addZipInputStream(any());
}
