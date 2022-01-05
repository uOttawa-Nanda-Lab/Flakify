@Test public void overridePortFile() throws Exception {
  File file=this.temporaryFolder.newFile();
  System.setProperty("PORTFILE",this.temporaryFolder.newFile().getAbsolutePath());
  EmbeddedServerPortFileWriter listener=new EmbeddedServerPortFileWriter(file);
  listener.onApplicationEvent(mockEvent("",8080));
  assertThat(FileCopyUtils.copyToString(new FileReader(System.getProperty("PORTFILE"))),equalTo("8080"));
}
