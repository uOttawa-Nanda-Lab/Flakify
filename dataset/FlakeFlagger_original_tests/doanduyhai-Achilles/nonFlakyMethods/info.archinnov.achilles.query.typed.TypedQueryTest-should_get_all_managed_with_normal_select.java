@Test public void should_get_all_managed_with_normal_select() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(ID).accessors().build();
  PropertyMeta nameMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").type(SIMPLE).accessors().build();
  PropertyMeta ageMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("age").type(SIMPLE).accessors().build();
  EntityMeta meta=buildEntityMeta(idMeta,nameMeta,ageMeta);
  String queryString=" select id, name   from  test";
  initBuilder(queryString,meta,meta.getPropertyMetas(),true);
  when(daoContext.execute(any(AbstractStatementWrapper.class)).all()).thenReturn(Arrays.asList(row));
  when(mapper.mapRowToEntityWithPrimaryKey(eq(meta),eq(row),Mockito.<Map<String,PropertyMeta>>any(),eq(true))).thenReturn(entity);
  when(contextFactory.newContext(entity)).thenReturn(context);
  when(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,entityFacade)).thenReturn(entity);
  List<CompleteBean> actual=builder.get();
  assertThat(actual).containsExactly(entity);
  verify(meta).intercept(entity,Event.POST_LOAD);
}
