@Test public void should_return_allMetasExceptCounters_for_columnsMetaToLoad() throws Exception {
  final ArrayList<PropertyMeta> allMetasExceptCounters=new ArrayList<>();
  EntityMeta meta=new EntityMeta();
  meta.setAllMetasExceptCounters(allMetasExceptCounters);
  meta.setClusteredCounter(false);
  assertThat(meta.getColumnsMetaToLoad()).isEqualTo(allMetasExceptCounters);
}
