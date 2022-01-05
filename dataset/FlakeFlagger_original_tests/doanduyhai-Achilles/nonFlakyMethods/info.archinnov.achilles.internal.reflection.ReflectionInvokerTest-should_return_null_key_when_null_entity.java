@Test public void should_return_null_key_when_null_entity() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").accessors().build();
  assertThat(invoker.getPrimaryKey(null,idMeta)).isNull();
}
