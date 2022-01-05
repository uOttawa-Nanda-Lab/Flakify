@Test public void mainClassFromManifest() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithoutMainMethod.class);
  Manifest manifest=new Manifest();
  manifest=new Manifest();
  manifest.getMainAttributes().putValue("Manifest-Version","1.0");
  manifest.getMainAttributes().putValue("Main-Class","a.b.C");
  this.testJarFile.addManifest(manifest);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  repackager.repackage(NO_LIBRARIES);
  Manifest actualManifest=getManifest(file);
  assertThat(actualManifest.getMainAttributes().getValue("Main-Class"),equalTo("org.springframework.boot.loader.JarLauncher"));
  assertThat(actualManifest.getMainAttributes().getValue("Start-Class"),equalTo("a.b.C"));
  assertThat(hasLauncherClasses(file),equalTo(true));
}
