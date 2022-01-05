@Test public void inputStreamReadPastSubsection() throws Exception {
  RandomAccessData subsection=this.file.getSubsection(1,2);
  InputStream inputStream=subsection.getInputStream(ResourceAccess.PER_READ);
  assertThat(inputStream.read(),equalTo(1));
  assertThat(inputStream.read(),equalTo(2));
  assertThat(inputStream.read(),equalTo(-1));
}
