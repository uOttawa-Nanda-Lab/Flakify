@Test public void should_return_allMetasExceptIdAndCounters_for_columnsMetaToInsert() throws Exception {
  final ArrayList<PropertyMeta> allMetasExceptIdAndCounters=new ArrayList<>();
  EntityMeta meta=new EntityMeta();
  meta.setAllMetasExceptIdAndCounters(allMetasExceptIdAndCounters);
  meta.setClusteredCounter(false);
  assertThat(meta.getColumnsMetaToInsert()).isEqualTo(allMetasExceptIdAndCounters);
}
