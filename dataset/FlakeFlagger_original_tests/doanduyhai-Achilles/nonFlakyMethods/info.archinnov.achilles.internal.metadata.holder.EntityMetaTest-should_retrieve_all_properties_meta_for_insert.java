@Test public void should_retrieve_all_properties_meta_for_insert() throws Exception {
  EntityMeta entityMeta=new EntityMeta();
  List<PropertyMeta> pms=new ArrayList<>();
  entityMeta.setAllMetasExceptIdAndCounters(pms);
  entityMeta.setInsertStrategy(ALL_FIELDS);
  final List<PropertyMeta> propertyMetas=entityMeta.retrievePropertyMetasForInsert(new CompleteBean());
  assertThat(propertyMetas).isSameAs(pms);
}
