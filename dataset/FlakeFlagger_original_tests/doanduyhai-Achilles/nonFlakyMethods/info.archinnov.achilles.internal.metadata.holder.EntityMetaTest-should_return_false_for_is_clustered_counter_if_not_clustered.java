@Test public void should_return_false_for_is_clustered_counter_if_not_clustered() throws Exception {
  EntityMeta entityMeta=new EntityMeta();
  PropertyMeta counterMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("count").type(COUNTER).build();
  entityMeta.setClusteredEntity(false);
  entityMeta.setPropertyMetas(ImmutableMap.of("count",counterMeta));
  assertThat(entityMeta.isClusteredCounter()).isFalse();
}
