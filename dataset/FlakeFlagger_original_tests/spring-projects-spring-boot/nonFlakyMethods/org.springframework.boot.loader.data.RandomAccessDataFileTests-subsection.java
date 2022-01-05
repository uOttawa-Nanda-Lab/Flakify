@Test public void subsection() throws Exception {
  RandomAccessData subsection=this.file.getSubsection(1,1);
  assertThat(subsection.getInputStream(ResourceAccess.PER_READ).read(),equalTo(1));
}
