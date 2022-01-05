@Test public void should_init_entity_packages() throws Exception {
  configMap.put(ENTITY_PACKAGES,"info.archinnov.achilles.test.sample.entity,info.archinnov.achilles.test.more.entity");
  Collection<Class<?>> actual=extractor.initEntities(configMap,this.getClass().getClassLoader());
  assertThat(actual).containsOnly(Entity1.class,Entity2.class,Entity3.class);
}
