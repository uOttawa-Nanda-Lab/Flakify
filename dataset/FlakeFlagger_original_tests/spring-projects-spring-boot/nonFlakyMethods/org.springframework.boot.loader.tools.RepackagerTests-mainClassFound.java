@Test public void mainClassFound() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  repackager.repackage(NO_LIBRARIES);
  Manifest actualManifest=getManifest(file);
  assertThat(actualManifest.getMainAttributes().getValue("Main-Class"),equalTo("org.springframework.boot.loader.JarLauncher"));
  assertThat(actualManifest.getMainAttributes().getValue("Start-Class"),equalTo("a.b.C"));
  assertThat(hasLauncherClasses(file),equalTo(true));
}
