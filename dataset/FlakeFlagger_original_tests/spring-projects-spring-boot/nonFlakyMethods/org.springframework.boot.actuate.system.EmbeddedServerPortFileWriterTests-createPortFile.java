@Test public void createPortFile() throws Exception {
  File file=this.temporaryFolder.newFile();
  EmbeddedServerPortFileWriter listener=new EmbeddedServerPortFileWriter(file);
  listener.onApplicationEvent(mockEvent("",8080));
  assertThat(FileCopyUtils.copyToString(new FileReader(file)),equalTo("8080"));
}
