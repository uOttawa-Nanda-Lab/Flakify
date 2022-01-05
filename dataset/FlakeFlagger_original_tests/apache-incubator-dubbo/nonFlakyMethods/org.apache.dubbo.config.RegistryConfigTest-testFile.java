@Test public void testFile() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setFile("file");
  assertThat(registry.getFile(),equalTo("file"));
}
