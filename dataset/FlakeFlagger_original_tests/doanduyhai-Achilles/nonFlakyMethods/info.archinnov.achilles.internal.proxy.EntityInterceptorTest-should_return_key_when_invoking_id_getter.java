@Test public void should_return_key_when_invoking_id_getter() throws Throwable {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").accessors().build();
  interceptor.setIdGetter(idMeta.getGetter());
  Object id=interceptor.intercept(target,idMeta.getGetter(),args,null);
  assertThat(id).isEqualTo(key);
}
