@Test public void mimeType() throws Exception {
  FileCopyUtils.copy("test",new FileWriter(this.temporaryFolder.newFile("test.xxcss")));
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.setDocumentRoot(this.temporaryFolder.getRoot());
  MimeMappings mimeMappings=new MimeMappings();
  mimeMappings.add("xxcss","text/css");
  factory.setMimeMappings(mimeMappings);
  this.container=factory.getEmbeddedServletContainer();
  this.container.start();
  ClientHttpResponse response=getClientResponse(getLocalUrl("/test.xxcss"));
  assertThat(response.getHeaders().getContentType().toString(),equalTo("text/css"));
  response.close();
}
