@Test public void getName() throws Exception {
  assertThat(this.jarFile.getName(),equalTo(this.rootJarFile.getPath()));
}
