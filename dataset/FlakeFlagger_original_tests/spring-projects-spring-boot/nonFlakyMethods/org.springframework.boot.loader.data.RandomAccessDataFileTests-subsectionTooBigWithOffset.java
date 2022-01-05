@Test public void subsectionTooBigWithOffset() throws Exception {
  this.file.getSubsection(1,255);
  this.thrown.expect(IndexOutOfBoundsException.class);
  this.file.getSubsection(1,256);
}
