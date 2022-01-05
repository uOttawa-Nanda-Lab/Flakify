@Test public void createEntryUrl() throws Exception {
  URL url=new URL(this.jarFile.getUrl(),"1.dat");
  assertThat(url.toString(),equalTo("jar:" + this.rootJarFile.toURI() + "!/1.dat"));
  JarURLConnection jarURLConnection=(JarURLConnection)url.openConnection();
  assertThat(jarURLConnection.getJarFile(),sameInstance(this.jarFile));
  assertThat(jarURLConnection.getJarEntry(),sameInstance(this.jarFile.getJarEntry("1.dat")));
  assertThat(jarURLConnection.getContentLength(),equalTo(1));
  assertThat(jarURLConnection.getContent(),instanceOf(InputStream.class));
  assertThat(jarURLConnection.getContentType(),equalTo("content/unknown"));
}
