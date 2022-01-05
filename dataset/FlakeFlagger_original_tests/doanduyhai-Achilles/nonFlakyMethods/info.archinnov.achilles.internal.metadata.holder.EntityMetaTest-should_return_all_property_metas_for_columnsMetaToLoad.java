@Test public void should_return_all_property_metas_for_columnsMetaToLoad() throws Exception {
  final PropertyMeta propertyMeta=new PropertyMeta();
  propertyMeta.setEntityClassName("CompleteBean");
  propertyMeta.setPropertyName("property");
  propertyMeta.setType(PropertyType.SIMPLE);
  EntityMeta meta=new EntityMeta();
  meta.setPropertyMetas(ImmutableMap.of("property",propertyMeta));
  meta.setClusteredCounter(true);
  assertThat(meta.getColumnsMetaToLoad()).containsExactly(propertyMeta);
}
