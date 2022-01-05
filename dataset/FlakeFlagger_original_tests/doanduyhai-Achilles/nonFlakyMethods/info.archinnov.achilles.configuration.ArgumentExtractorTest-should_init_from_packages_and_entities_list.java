@Test public void should_init_from_packages_and_entities_list(){
  configMap.put(ENTITIES_LIST,Arrays.asList(Entity1.class));
  configMap.put(ENTITY_PACKAGES,"info.archinnov.achilles.test.more.entity");
  Collection<Class<?>> actual=extractor.initEntities(configMap,this.getClass().getClassLoader());
  assertThat(actual).containsOnly(Entity1.class,Entity3.class);
}
