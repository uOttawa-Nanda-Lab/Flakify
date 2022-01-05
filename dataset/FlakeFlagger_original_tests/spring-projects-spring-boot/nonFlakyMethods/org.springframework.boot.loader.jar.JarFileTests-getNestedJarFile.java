@Test public void getNestedJarFile() throws Exception {
  JarFile nestedJarFile=this.jarFile.getNestedJarFile(this.jarFile.getEntry("nested.jar"));
  Enumeration<java.util.jar.JarEntry> entries=nestedJarFile.entries();
  assertThat(entries.nextElement().getName(),equalTo("META-INF/"));
  assertThat(entries.nextElement().getName(),equalTo("META-INF/MANIFEST.MF"));
  assertThat(entries.nextElement().getName(),equalTo("3.dat"));
  assertThat(entries.nextElement().getName(),equalTo("4.dat"));
  assertThat(entries.nextElement().getName(),equalTo("\u00E4.dat"));
  assertThat(entries.hasMoreElements(),equalTo(false));
  InputStream inputStream=nestedJarFile.getInputStream(nestedJarFile.getEntry("3.dat"));
  assertThat(inputStream.read(),equalTo(3));
  assertThat(inputStream.read(),equalTo(-1));
  URL url=nestedJarFile.getUrl();
  assertThat(url.toString(),equalTo("jar:" + this.rootJarFile.toURI() + "!/nested.jar!/"));
  JarURLConnection conn=(JarURLConnection)url.openConnection();
  assertThat(conn.getJarFile(),sameInstance(nestedJarFile));
  assertThat(conn.getJarFileURL().toString(),equalTo("jar:" + this.rootJarFile.toURI() + "!/nested.jar"));
}
