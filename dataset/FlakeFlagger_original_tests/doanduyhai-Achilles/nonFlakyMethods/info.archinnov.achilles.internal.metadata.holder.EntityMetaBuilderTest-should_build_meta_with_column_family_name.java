@Test public void should_build_meta_with_column_family_name() throws Exception {
  Map<String,PropertyMeta> propertyMetas=new HashMap<>();
  PropertyMeta simpleMeta=new PropertyMeta();
  simpleMeta.setType(SIMPLE);
  propertyMetas.put("name",simpleMeta);
  when(idMeta.<Long>getValueClass()).thenReturn(Long.class);
  List<PropertyMeta> eagerMetas=new ArrayList<>();
  eagerMetas.add(simpleMeta);
  EntityMeta meta=entityMetaBuilder(idMeta).className("Bean").propertyMetas(propertyMetas).columnFamilyName("toto").build();
  assertThat(meta.getClassName()).isEqualTo("Bean");
  assertThat(meta.getTableName()).isEqualTo("toto");
}
