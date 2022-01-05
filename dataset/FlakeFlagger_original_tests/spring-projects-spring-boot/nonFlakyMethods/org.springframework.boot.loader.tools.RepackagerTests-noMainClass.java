@Test public void noMainClass() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithoutMainMethod.class);
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("Unable to find main class");
  new Repackager(this.testJarFile.getFile()).repackage(NO_LIBRARIES);
}
