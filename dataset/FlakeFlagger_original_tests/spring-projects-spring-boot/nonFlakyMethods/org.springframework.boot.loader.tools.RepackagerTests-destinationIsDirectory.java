@Test public void destinationIsDirectory() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  Repackager repackager=new Repackager(this.testJarFile.getFile());
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Invalid destination");
  repackager.repackage(this.temporaryFolder.getRoot(),NO_LIBRARIES);
}
