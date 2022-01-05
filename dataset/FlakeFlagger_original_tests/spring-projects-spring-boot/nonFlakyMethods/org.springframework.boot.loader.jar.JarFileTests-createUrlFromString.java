@Test public void createUrlFromString() throws Exception {
  JarFile.registerUrlProtocolHandler();
  String spec="jar:" + this.rootJarFile.toURI() + "!/nested.jar!/3.dat";
  URL url=new URL(spec);
  assertThat(url.toString(),equalTo(spec));
  InputStream inputStream=url.openStream();
  assertThat(inputStream,notNullValue());
  assertThat(inputStream.read(),equalTo(3));
  JarURLConnection connection=(JarURLConnection)url.openConnection();
  assertThat(connection.getURL().toString(),equalTo(spec));
  assertThat(connection.getJarFileURL().toString(),equalTo("jar:" + this.rootJarFile.toURI() + "!/nested.jar"));
  assertThat(connection.getEntryName(),equalTo("3.dat"));
}
