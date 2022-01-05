@Test public void should_retrieve_not_null_properties_meta_for_insert() throws Exception {
  PropertyMeta pm1=mock(PropertyMeta.class);
  PropertyMeta pm2=mock(PropertyMeta.class);
  EntityMeta entityMeta=new EntityMeta();
  entityMeta.setInsertStrategy(NOT_NULL_FIELDS);
  List<PropertyMeta> pms=asList(pm1,pm2);
  entityMeta.setAllMetasExceptIdAndCounters(pms);
  CompleteBean entity=new CompleteBean();
  when(pm1.getValueFromField(entity)).thenReturn(null);
  when(pm2.getValueFromField(entity)).thenReturn("not null");
  final List<PropertyMeta> propertyMetas=entityMeta.retrievePropertyMetasForInsert(entity);
  assertThat(propertyMetas).containsExactly(pm2);
}
