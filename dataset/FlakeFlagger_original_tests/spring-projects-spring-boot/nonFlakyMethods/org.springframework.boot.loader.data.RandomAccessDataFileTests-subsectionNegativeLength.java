@Test public void subsectionNegativeLength() throws Exception {
  this.thrown.expect(IndexOutOfBoundsException.class);
  this.file.getSubsection(0,-1);
}
