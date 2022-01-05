@Test public void should_get_proxy() throws Exception {
  when(loader.createEmptyEntity(context.entityFacade,CompleteBean.class)).thenReturn(entity);
  when(proxifier.buildProxyWithNoFieldLoaded(entity,context.entityFacade)).thenReturn(entity);
  CompleteBean found=facade.getProxy(CompleteBean.class);
  assertThat(found).isSameAs(entity);
}
