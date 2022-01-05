@Test public void should_get_all_raw_entities() throws Exception {
  EntityMeta meta=mock(EntityMeta.class);
  Map<String,PropertyMeta> propertyMetas=new HashMap<String,PropertyMeta>();
  String queryString="select * from test";
  initBuilder(queryString,meta,propertyMetas,false);
  when(daoContext.execute(any(AbstractStatementWrapper.class)).all()).thenReturn(Arrays.asList(row));
  when(mapper.mapRowToEntityWithPrimaryKey(meta,row,propertyMetas,false)).thenReturn(entity);
  List<CompleteBean> actual=builder.get();
  assertThat(actual).containsExactly(entity);
  verify(meta).intercept(entity,Event.POST_LOAD);
  verifyZeroInteractions(contextFactory,proxifier);
}
