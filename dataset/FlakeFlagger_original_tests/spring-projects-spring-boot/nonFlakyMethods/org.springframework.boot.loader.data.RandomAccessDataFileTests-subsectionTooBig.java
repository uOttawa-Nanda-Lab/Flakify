@Test public void subsectionTooBig() throws Exception {
  this.file.getSubsection(0,256);
  this.thrown.expect(IndexOutOfBoundsException.class);
  this.file.getSubsection(0,257);
}
