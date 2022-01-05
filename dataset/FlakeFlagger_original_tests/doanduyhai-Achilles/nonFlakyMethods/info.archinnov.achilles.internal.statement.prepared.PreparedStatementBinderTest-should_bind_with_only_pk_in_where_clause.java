@Test public void should_bind_with_only_pk_in_where_clause() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").accessors().type(ID).transcoder(transcoder).invoker(invoker).build();
  entityMeta.setIdMeta(idMeta);
  long primaryKey=RandomUtils.nextLong();
  when(context.getIdMeta()).thenReturn(idMeta);
  when(context.getPrimaryKey()).thenReturn(primaryKey);
  when(transcoder.encode(idMeta,primaryKey)).thenReturn(primaryKey);
  when(overrider.getWriteLevel(context)).thenReturn(ALL);
  when(ps.bind(Matchers.anyVararg())).thenReturn(bs);
  BoundStatementWrapper actual=binder.bindStatementWithOnlyPKInWhereClause(context,ps,info.archinnov.achilles.type.ConsistencyLevel.ALL);
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  assertThat(asList(actual.getValues())).containsExactly(primaryKey);
}
