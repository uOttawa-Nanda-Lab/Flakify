@Test public void should_return_true_when_value_less() throws Exception {
  EntityMeta entityMeta=new EntityMeta();
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.ID).build();
  entityMeta.setPropertyMetas(ImmutableMap.of("idMeta",idMeta));
  assertThat(entityMeta.isValueless()).isTrue();
}
