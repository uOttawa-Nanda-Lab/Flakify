@Test public void sameSourceAndDestinationWithoutBackup() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  repackager.setBackupSource(false);
  repackager.repackage(NO_LIBRARIES);
  assertThat(new File(file.getParent(),file.getName() + ".original").exists(),equalTo(false));
  assertThat(hasLauncherClasses(file),equalTo(true));
}
