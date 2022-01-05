@Test public void createFromFile() throws Exception {
  JarFile jarFile=new JarFile(this.rootJarFile);
  assertThat(jarFile.getName(),notNullValue(String.class));
  jarFile.close();
}
