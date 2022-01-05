@Test public void should_add_elements_to_map_with_ttl() throws Exception {
  bean.getPreferences().put(4,"Cassandra");
  bean.getPreferences().put(5,"CQL3");
  manager.update(bean,OptionsBuilder.withTtl(1));
  Thread.sleep(1000);
  manager.refresh(bean);
  assertThat(bean.getPreferences()).hasSize(3).contains(entry(1,"FR"),entry(2,"Paris"),entry(3,"75014"));
}
