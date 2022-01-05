@Test public void createNonNestedUrlFromString() throws Exception {
  JarFile.registerUrlProtocolHandler();
  String spec="jar:" + this.rootJarFile.toURI() + "!/2.dat";
  URL url=new URL(spec);
  assertThat(url.toString(),equalTo(spec));
  InputStream inputStream=url.openStream();
  assertThat(inputStream,notNullValue());
  assertThat(inputStream.read(),equalTo(2));
  JarURLConnection connection=(JarURLConnection)url.openConnection();
  assertThat(connection.getURL().toString(),equalTo(spec));
  assertThat(connection.getJarFileURL().toURI(),equalTo(this.rootJarFile.toURI()));
  assertThat(connection.getEntryName(),equalTo("2.dat"));
}
