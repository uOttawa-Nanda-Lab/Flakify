@Test public void overridePidFile() throws Exception {
  File file=this.temporaryFolder.newFile();
  System.setProperty("PIDFILE",this.temporaryFolder.newFile().getAbsolutePath());
  ApplicationPidFileWriter listener=new ApplicationPidFileWriter(file);
  listener.onApplicationEvent(EVENT);
  assertThat(FileCopyUtils.copyToString(new FileReader(System.getProperty("PIDFILE"))),not(isEmptyString()));
}
