@Test public void getSize() throws Exception {
  assertThat(this.jarFile.size(),equalTo((int)this.rootJarFile.length()));
}
