@Test public void overwriteDestination() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  Repackager repackager=new Repackager(this.testJarFile.getFile());
  File dest=this.temporaryFolder.newFile("dest.jar");
  dest.createNewFile();
  repackager.repackage(dest,NO_LIBRARIES);
  assertThat(hasLauncherClasses(dest),equalTo(true));
}
