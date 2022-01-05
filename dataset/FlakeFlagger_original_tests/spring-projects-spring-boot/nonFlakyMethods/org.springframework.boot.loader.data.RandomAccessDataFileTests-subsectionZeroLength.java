@Test public void subsectionZeroLength() throws Exception {
  RandomAccessData subsection=this.file.getSubsection(0,0);
  assertThat(subsection.getInputStream(ResourceAccess.PER_READ).read(),equalTo(-1));
}
