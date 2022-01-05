@Test public void noMainClassAndLayoutIsNoneWithNoMain() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithoutMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  repackager.setLayout(new Layouts.None());
  repackager.repackage(file,NO_LIBRARIES);
  Manifest actualManifest=getManifest(file);
  assertThat(actualManifest.getMainAttributes().getValue("Main-Class"),equalTo(null));
  assertThat(hasLauncherClasses(file),equalTo(false));
}
