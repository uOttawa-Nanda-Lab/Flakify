@Test public void getNestedJarDirectory() throws Exception {
  JarFile nestedJarFile=this.jarFile.getNestedJarFile(this.jarFile.getEntry("d/"));
  Enumeration<java.util.jar.JarEntry> entries=nestedJarFile.entries();
  assertThat(entries.nextElement().getName(),equalTo("9.dat"));
  assertThat(entries.hasMoreElements(),equalTo(false));
  InputStream inputStream=nestedJarFile.getInputStream(nestedJarFile.getEntry("9.dat"));
  assertThat(inputStream.read(),equalTo(9));
  assertThat(inputStream.read(),equalTo(-1));
  URL url=nestedJarFile.getUrl();
  assertThat(url.toString(),equalTo("jar:" + this.rootJarFile.toURI() + "!/d!/"));
  assertThat(((JarURLConnection)url.openConnection()).getJarFile(),sameInstance(nestedJarFile));
}
