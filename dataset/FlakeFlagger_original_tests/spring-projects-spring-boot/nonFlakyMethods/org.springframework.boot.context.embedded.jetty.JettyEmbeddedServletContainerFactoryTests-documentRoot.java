@Test public void documentRoot() throws Exception {
  FileCopyUtils.copy("test",new FileWriter(this.temporaryFolder.newFile("test.txt")));
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.setDocumentRoot(this.temporaryFolder.getRoot());
  this.container=factory.getEmbeddedServletContainer();
  this.container.start();
  assertThat(getResponse(getLocalUrl("/test.txt")),equalTo("test"));
}
