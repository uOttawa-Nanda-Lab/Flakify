@Test public void should_init_forceCFCreation() throws Exception {
  configMap.put(FORCE_TABLE_CREATION,true);
  boolean actual=extractor.initForceTableCreation(configMap);
  assertThat(actual).isTrue();
}
