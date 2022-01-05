@Test public void getToolsJar() throws Exception {
  URL jarUrl=JvmUtils.getToolsJarUrl();
  assertThat(jarUrl.toString(),endsWith(".jar"));
  assertThat(new File(jarUrl.toURI()).exists(),equalTo(true));
}
