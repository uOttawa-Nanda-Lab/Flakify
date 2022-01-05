@Test public void should_get_primary_key() throws Exception {
  Long id=RandomUtils.nextLong();
  CompleteBean bean=new CompleteBean(id);
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).type(ID).field("id").accessors().build();
  Object key=invoker.getPrimaryKey(bean,idMeta);
  assertThat(key).isEqualTo(id);
}
