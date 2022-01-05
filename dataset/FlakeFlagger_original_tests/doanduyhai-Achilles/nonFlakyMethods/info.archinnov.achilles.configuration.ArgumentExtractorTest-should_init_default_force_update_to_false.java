@Test public void should_init_default_force_update_to_false() throws Exception {
  boolean actual=extractor.initForceTableUpdate(configMap);
  Map<String,Boolean> tables=extractor.initForceTableUpdateMap(configMap);
  assertThat(actual).isFalse();
  assertThat(tables.isEmpty());
}
