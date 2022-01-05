@Test public void should_get_first_managed_entity() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.ID).accessors().build();
  PropertyMeta nameMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").type(PropertyType.SIMPLE).accessors().build();
  EntityMeta meta=buildEntityMeta(idMeta,nameMeta);
  String queryString="select id from test";
  initBuilder(queryString,meta,meta.getPropertyMetas(),true);
  when(daoContext.execute(any(AbstractStatementWrapper.class)).one()).thenReturn(row);
  when(mapper.mapRowToEntityWithPrimaryKey(eq(meta),eq(row),Mockito.<Map<String,PropertyMeta>>any(),eq(true))).thenReturn(entity);
  when(contextFactory.newContext(entity)).thenReturn(context);
  when(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,entityFacade)).thenReturn(entity);
  CompleteBean actual=builder.getFirst();
  assertThat(actual).isSameAs(entity);
  verify(meta).intercept(entity,Event.POST_LOAD);
}
