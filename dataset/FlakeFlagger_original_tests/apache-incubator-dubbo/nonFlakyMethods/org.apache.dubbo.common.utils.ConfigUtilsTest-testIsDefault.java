@Test public void testIsDefault() throws Exception {
  assertThat(ConfigUtils.isDefault("true"),is(true));
  assertThat(ConfigUtils.isDefault("TRUE"),is(true));
  assertThat(ConfigUtils.isDefault("default"),is(true));
  assertThat(ConfigUtils.isDefault("DEFAULT"),is(true));
}
