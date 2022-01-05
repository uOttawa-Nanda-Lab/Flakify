@Test public void should_build_meta_with_consistency_levels() throws Exception {
  Map<String,PropertyMeta> propertyMetas=new HashMap<>();
  PropertyMeta nameMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").type(PropertyType.SIMPLE).accessors().build();
  propertyMetas.put("name",nameMeta);
  when(idMeta.<Long>getValueClass()).thenReturn(Long.class);
  Pair<ConsistencyLevel,ConsistencyLevel> consistencyLevels=Pair.create(ConsistencyLevel.ONE,ConsistencyLevel.TWO);
  EntityMeta meta=entityMetaBuilder(idMeta).className("Bean").propertyMetas(propertyMetas).columnFamilyName("toto").consistencyLevels(consistencyLevels).build();
  assertThat(meta.getConsistencyLevels()).isSameAs(consistencyLevels);
}
