@Test public void differentDestination() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  File source=this.testJarFile.getFile();
  File dest=this.temporaryFolder.newFile("different.jar");
  Repackager repackager=new Repackager(source);
  repackager.repackage(dest,NO_LIBRARIES);
  assertThat(new File(source.getParent(),source.getName() + ".original").exists(),equalTo(false));
  assertThat(hasLauncherClasses(source),equalTo(false));
  assertThat(hasLauncherClasses(dest),equalTo(true));
}
