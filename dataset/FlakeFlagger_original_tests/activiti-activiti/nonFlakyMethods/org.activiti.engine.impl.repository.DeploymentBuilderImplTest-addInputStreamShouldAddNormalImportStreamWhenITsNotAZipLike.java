@Test public void addInputStreamShouldAddNormalImportStreamWhenITsNotAZipLike() throws Exception {
  String resourceName="any.xml";
  InputStream inputStream=mock(InputStream.class);
  given(resource.getInputStream()).willReturn(inputStream);
  doReturn(deploymentBuilder).when(deploymentBuilder).addInputStream(resourceName,inputStream);
  deploymentBuilder.addInputStream(resourceName,resource);
  verify(deploymentBuilder).addInputStream(resourceName,inputStream);
}
