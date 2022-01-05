@Test public void should_get_first_raw_entity() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(ID).accessors().build();
  PropertyMeta nameMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").type(SIMPLE).accessors().build();
  EntityMeta meta=buildEntityMeta(idMeta,nameMeta);
  String queryString="select id from test";
  initBuilder(queryString,meta,meta.getPropertyMetas(),false);
  when(daoContext.execute(any(AbstractStatementWrapper.class)).one()).thenReturn(row);
  when(mapper.mapRowToEntityWithPrimaryKey(eq(meta),eq(row),Mockito.<Map<String,PropertyMeta>>any(),eq(false))).thenReturn(entity);
  CompleteBean actual=builder.getFirst();
  assertThat(actual).isSameAs(entity);
  verify(meta).intercept(entity,Event.POST_LOAD);
  verifyZeroInteractions(contextFactory,proxifier);
}
