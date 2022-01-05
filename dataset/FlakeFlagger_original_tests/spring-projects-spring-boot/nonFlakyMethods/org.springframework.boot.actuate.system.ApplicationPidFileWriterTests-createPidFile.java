@Test public void createPidFile() throws Exception {
  File file=this.temporaryFolder.newFile();
  ApplicationPidFileWriter listener=new ApplicationPidFileWriter(file);
  listener.onApplicationEvent(EVENT);
  assertThat(FileCopyUtils.copyToString(new FileReader(file)),not(isEmptyString()));
}
