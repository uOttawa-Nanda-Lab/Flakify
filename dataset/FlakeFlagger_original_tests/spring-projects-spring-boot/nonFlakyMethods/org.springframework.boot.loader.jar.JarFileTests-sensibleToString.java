@Test public void sensibleToString() throws Exception {
  assertThat(this.jarFile.toString(),equalTo(this.rootJarFile.getPath()));
  assertThat(this.jarFile.getNestedJarFile(this.jarFile.getEntry("nested.jar")).toString(),equalTo(this.rootJarFile.getPath() + "!/nested.jar"));
}
