@Test public void should_return_false_for_is_clustered_counter_if_more_than_one_property() throws Exception {
  EntityMeta entityMeta=new EntityMeta();
  PropertyMeta nameMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  PropertyMeta counterMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("count").type(COUNTER).build();
  entityMeta.setClusteredEntity(true);
  entityMeta.setPropertyMetas(ImmutableMap.of("name",nameMeta,"count",counterMeta));
  assertThat(entityMeta.isClusteredCounter()).isFalse();
}
