@Test public void should_return_false_for_is_clustered_counter_if_value_less() throws Exception {
  EntityMeta entityMeta=new EntityMeta();
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.ID).build();
  entityMeta.setClusteredEntity(false);
  entityMeta.setPropertyMetas(ImmutableMap.of("idMeta",idMeta));
  assertThat(entityMeta.isClusteredCounter()).isFalse();
}
