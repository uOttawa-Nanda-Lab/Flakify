@Test public void inputStreamSkipPastSubsection() throws Exception {
  RandomAccessData subsection=this.file.getSubsection(1,2);
  InputStream inputStream=subsection.getInputStream(ResourceAccess.PER_READ);
  assertThat(inputStream.skip(3),equalTo(2L));
  assertThat(inputStream.read(),equalTo(-1));
}
