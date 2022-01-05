@Test public void cannotLoadMissingJar() throws Exception {
  JarFile nestedJarFile=this.jarFile.getNestedJarFile(this.jarFile.getEntry("nested.jar"));
  URL nestedUrl=nestedJarFile.getUrl();
  URL url=new URL(nestedUrl,nestedJarFile.getUrl() + "missing.jar!/3.dat");
  this.thrown.expect(FileNotFoundException.class);
  url.openConnection().getInputStream();
}
