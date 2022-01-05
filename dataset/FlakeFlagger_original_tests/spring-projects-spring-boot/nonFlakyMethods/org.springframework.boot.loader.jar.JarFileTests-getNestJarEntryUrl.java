@Test public void getNestJarEntryUrl() throws Exception {
  JarFile nestedJarFile=this.jarFile.getNestedJarFile(this.jarFile.getEntry("nested.jar"));
  URL url=nestedJarFile.getJarEntry("3.dat").getUrl();
  assertThat(url.toString(),equalTo("jar:" + this.rootJarFile.toURI() + "!/nested.jar!/3.dat"));
  InputStream inputStream=url.openStream();
  assertThat(inputStream,notNullValue());
  assertThat(inputStream.read(),equalTo(3));
}
