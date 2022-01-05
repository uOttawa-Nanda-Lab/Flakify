@Test public void sameSourceAndDestinationWithBackup() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  repackager.repackage(NO_LIBRARIES);
  assertThat(new File(file.getParent(),file.getName() + ".original").exists(),equalTo(true));
  assertThat(hasLauncherClasses(file),equalTo(true));
}
