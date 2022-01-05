@Test public void should_get_indexed_query() throws Exception {
  final IndexCondition indexCondition=new IndexCondition("column","value");
  when(entityMetaMap.get(CompleteBean.class)).thenReturn(meta);
  when(entityMetaMap.containsKey(CompleteBean.class)).thenReturn(true);
  when(meta.isClusteredEntity()).thenReturn(false);
  when(meta.getTableName()).thenReturn("table");
  when(meta.encodeBoundValuesForTypedQueries(any(Object[].class))).thenReturn(new Object[]{"value"});
  TypedQuery<CompleteBean> typedQuery=manager.indexedQuery(CompleteBean.class,indexCondition);
  assertThat(Whitebox.<Object[]>getInternalState(typedQuery,"encodedBoundValues")).contains("value");
  verify(meta).encodeIndexConditionValue(indexCondition);
  verify(typedQueryValidator).validateTypedQuery(CompleteBean.class,"SELECT * FROM table WHERE column=:column;",meta);
}
