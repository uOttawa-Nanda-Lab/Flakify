@Test public void getEntries() throws Exception {
  Enumeration<java.util.jar.JarEntry> entries=this.jarFile.entries();
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
}
