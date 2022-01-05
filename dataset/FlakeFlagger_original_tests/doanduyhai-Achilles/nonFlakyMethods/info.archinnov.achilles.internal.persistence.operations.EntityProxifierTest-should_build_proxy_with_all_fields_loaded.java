@Test public void should_build_proxy_with_all_fields_loaded() throws Exception {
  long primaryKey=RandomUtils.nextLong();
  PropertyMeta pm=mock(PropertyMeta.class);
  Object value=new Object();
  CompleteBean entity=CompleteBeanTestBuilder.builder().id(primaryKey).name("name").buid();
  proxifier=spy(proxifier);
  doReturn(interceptor).when(proxifier).buildInterceptor(eq(context),eq(entity),anySetOf(Method.class));
  when(context.getEntityMeta()).thenReturn(entityMeta);
  when(entityMeta.getIdMeta()).thenReturn(idMeta);
  when(entityMeta.getAllMetas()).thenReturn(Arrays.asList(pm));
  when(pm.getValueFromField(entity)).thenReturn(value);
  when(context.getConfigContext()).thenReturn(configContext);
  when(factory.createProxyClass(entity.getClass(),configContext)).thenReturn((Class)entity.getClass());
  when(instantiator.instantiate(Mockito.<Class<Factory>>any())).thenReturn(realProxy);
  Object proxy=proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,context);
  assertThat(proxy).isNotNull();
  assertThat(proxy).isInstanceOf(Factory.class);
  Factory factory=(Factory)proxy;
  assertThat(factory.getCallbacks()).hasSize(1);
  assertThat(factory.getCallback(0)).isInstanceOf(EntityInterceptor.class);
  verify(pm).getValueFromField(entity);
  verify(pm).setValueToField(realProxy,value);
}
