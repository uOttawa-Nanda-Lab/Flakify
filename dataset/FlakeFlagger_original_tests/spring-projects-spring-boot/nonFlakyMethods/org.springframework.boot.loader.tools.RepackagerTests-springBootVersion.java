@Test public void springBootVersion() throws Exception {
  this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);
  File file=this.testJarFile.getFile();
  Repackager repackager=new Repackager(file);
  repackager.repackage(NO_LIBRARIES);
  Manifest actualManifest=getManifest(file);
  assertThat(actualManifest.getMainAttributes().containsKey(new Attributes.Name("Spring-Boot-Version")),equalTo(true));
}
