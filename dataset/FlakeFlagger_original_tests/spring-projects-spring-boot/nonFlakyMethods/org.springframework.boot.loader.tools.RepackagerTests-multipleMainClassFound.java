@Test public void multipleMainClassFound() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  this.testJarFile.addClass("a/b/D.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("Unable to find a single main class " + "from the following candidates [a.b.C, a.b.D]");
  repackager.repackage(NO_LIBRARIES);
}
