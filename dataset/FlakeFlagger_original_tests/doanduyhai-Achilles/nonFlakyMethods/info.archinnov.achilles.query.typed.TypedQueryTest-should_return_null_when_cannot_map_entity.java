@Test public void should_return_null_when_cannot_map_entity() throws Exception {
  EntityMeta meta=buildEntityMeta();
  String queryString="select id from test";
  initBuilder(queryString,meta,meta.getPropertyMetas(),false);
  when(daoContext.execute(any(AbstractStatementWrapper.class)).one()).thenReturn(row);
  when(mapper.mapRowToEntityWithPrimaryKey(eq(meta),eq(row),Mockito.<Map<String,PropertyMeta>>any(),eq(true))).thenReturn(null);
  CompleteBean actual=builder.getFirst();
  assertThat(actual).isNull();
  verifyZeroInteractions(contextFactory,proxifier);
}
