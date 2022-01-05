@Test public void nullCustomLayout() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithoutMainMethod.class);
  Repackager repackager=new Repackager(this.testJarFile.getFile());
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Layout must not be null");
  repackager.setLayout(null);
}
