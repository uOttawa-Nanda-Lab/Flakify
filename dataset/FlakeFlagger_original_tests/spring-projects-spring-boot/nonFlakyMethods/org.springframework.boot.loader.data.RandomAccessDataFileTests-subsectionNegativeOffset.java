@Test public void subsectionNegativeOffset() throws Exception {
  this.thrown.expect(IndexOutOfBoundsException.class);
  this.file.getSubsection(-1,1);
}
