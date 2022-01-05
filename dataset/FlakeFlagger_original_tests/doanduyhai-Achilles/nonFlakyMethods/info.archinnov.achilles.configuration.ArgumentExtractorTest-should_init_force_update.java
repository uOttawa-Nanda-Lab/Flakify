@Test public void should_init_force_update() throws Exception {
  configMap.put(ENABLE_SCHEMA_UPDATE,true);
  boolean actual=extractor.initForceTableUpdate(configMap);
  assertThat(actual).isTrue();
}
