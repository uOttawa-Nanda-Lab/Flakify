@Test public void nullLibraries() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Libraries must not be null");
  repackager.repackage(file,null);
}
