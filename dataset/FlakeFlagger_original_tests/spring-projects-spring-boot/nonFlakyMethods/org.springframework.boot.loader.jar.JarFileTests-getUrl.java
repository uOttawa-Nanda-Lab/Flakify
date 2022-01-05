@Test public void getUrl() throws Exception {
  URL url=this.jarFile.getUrl();
  assertThat(url.toString(),equalTo("jar:" + this.rootJarFile.toURI() + "!/"));
  JarURLConnection jarURLConnection=(JarURLConnection)url.openConnection();
  assertThat(jarURLConnection.getJarFile(),sameInstance(this.jarFile));
  assertThat(jarURLConnection.getJarEntry(),nullValue());
  assertThat(jarURLConnection.getContentLength(),greaterThan(1));
  assertThat(jarURLConnection.getContent(),sameInstance((Object)this.jarFile));
  assertThat(jarURLConnection.getContentType(),equalTo("x-java/jar"));
  assertThat(jarURLConnection.getJarFileURL().toURI(),equalTo(this.rootJarFile.toURI()));
}
