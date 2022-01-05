@Test public void jdkJarFile() throws Exception {
  java.util.jar.JarFile jarFile=new java.util.jar.JarFile(this.rootJarFile);
  Enumeration<java.util.jar.JarEntry> entries=jarFile.entries();
  assertThat(entries.nextElement().getName(),equalTo("META-INF/"));
  assertThat(entries.nextElement().getName(),equalTo("META-INF/MANIFEST.MF"));
  assertThat(entries.nextElement().getName(),equalTo("1.dat"));
  assertThat(entries.nextElement().getName(),equalTo("2.dat"));
  assertThat(entries.nextElement().getName(),equalTo("d/"));
  assertThat(entries.nextElement().getName(),equalTo("d/9.dat"));
  assertThat(entries.nextElement().getName(),equalTo("special/"));
  assertThat(entries.nextElement().getName(),equalTo("special/\u00EB.dat"));
  assertThat(entries.nextElement().getName(),equalTo("nested.jar"));
  assertThat(entries.hasMoreElements(),equalTo(false));
  URL jarUrl=new URL("jar:" + this.rootJarFile.toURI() + "!/");
  URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{jarUrl});
  assertThat(urlClassLoader.getResource("special/\u00EB.dat"),notNullValue());
  assertThat(urlClassLoader.getResource("d/9.dat"),notNullValue());
  jarFile.close();
  urlClassLoader.close();
}
