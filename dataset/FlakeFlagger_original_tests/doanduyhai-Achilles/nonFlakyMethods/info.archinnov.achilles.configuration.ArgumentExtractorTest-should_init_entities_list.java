@Test public void should_init_entities_list(){
  configMap.put(ENTITIES_LIST,Arrays.asList(Entity1.class));
  Collection<Class<?>> actual=extractor.initEntities(configMap,this.getClass().getClassLoader());
  assertThat(actual).contains(Entity1.class);
}
