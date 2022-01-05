@Test public void should_init_empty_entities_list(){
  Collection<Class<?>> actual=extractor.initEntities(configMap,this.getClass().getClassLoader());
  assertThat(actual).isEmpty();
}
