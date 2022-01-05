@Test public void writePid() throws Exception {
  ApplicationPid pid=new ApplicationPid("123");
  File file=this.temporaryFolder.newFile();
  pid.write(file);
  String actual=FileCopyUtils.copyToString(new FileReader(file));
  assertThat(actual,equalTo("123"));
}
