@Test public void getJarEntry() throws Exception {
  java.util.jar.JarEntry entry=this.jarFile.getJarEntry("1.dat");
  assertThat(entry,notNullValue(ZipEntry.class));
  assertThat(entry.getName(),equalTo("1.dat"));
}
