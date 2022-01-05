@Test public void should_get_all_skipping_null_entity() throws Exception {
  EntityMeta meta=buildEntityMeta();
  initBuilder("select * from test",meta,meta.getPropertyMetas(),true);
  when(daoContext.execute(any(AbstractStatementWrapper.class)).all()).thenReturn(Arrays.asList(row));
  when(mapper.mapRowToEntityWithPrimaryKey(eq(meta),eq(row),Mockito.<Map<String,PropertyMeta>>any(),eq(true))).thenReturn(null);
  List<CompleteBean> actual=builder.get();
  assertThat(actual).isEmpty();
  verify(meta,never()).intercept(entity,Event.POST_LOAD);
}
