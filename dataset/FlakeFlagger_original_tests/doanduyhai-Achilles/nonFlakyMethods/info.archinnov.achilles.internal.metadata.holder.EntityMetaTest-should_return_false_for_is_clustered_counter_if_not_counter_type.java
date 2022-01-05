@Test public void should_return_false_for_is_clustered_counter_if_not_counter_type() throws Exception {
  EntityMeta entityMeta=new EntityMeta();
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.ID).build();
  PropertyMeta nameMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  entityMeta.setClusteredEntity(true);
  entityMeta.setPropertyMetas(ImmutableMap.of("idMeta",idMeta,"nameMeta",nameMeta));
  assertThat(entityMeta.isClusteredCounter()).isFalse();
}
