@Test public void getMissingEntryUrl() throws Exception {
  URL url=new URL(this.jarFile.getUrl(),"missing.dat");
  assertThat(url.toString(),equalTo("jar:" + this.rootJarFile.toURI() + "!/missing.dat"));
  this.thrown.expect(FileNotFoundException.class);
  ((JarURLConnection)url.openConnection()).getJarEntry();
}
