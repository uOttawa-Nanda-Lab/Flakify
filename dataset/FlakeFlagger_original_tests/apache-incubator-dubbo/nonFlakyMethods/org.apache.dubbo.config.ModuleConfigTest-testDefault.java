@Test public void testDefault() throws Exception {
  ModuleConfig module=new ModuleConfig();
  module.setDefault(true);
  assertThat(module.isDefault(),is(true));
}
