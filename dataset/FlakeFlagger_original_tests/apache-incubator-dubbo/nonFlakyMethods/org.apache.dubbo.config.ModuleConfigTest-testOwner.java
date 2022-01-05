@Test public void testOwner() throws Exception {
  ModuleConfig module=new ModuleConfig();
  module.setOwner("owner");
  assertThat(module.getOwner(),equalTo("owner"));
}
