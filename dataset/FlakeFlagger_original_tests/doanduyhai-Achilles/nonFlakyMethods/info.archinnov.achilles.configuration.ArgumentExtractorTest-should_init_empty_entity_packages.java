@Test public void should_init_empty_entity_packages() throws Exception {
  Collection<Class<?>> actual=extractor.initEntities(configMap,this.getClass().getClassLoader());
  assertThat(actual).isEmpty();
}
