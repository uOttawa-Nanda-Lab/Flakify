@Test public void should_get_next_clustered_entity() throws Exception {
  ClusteredEntity entity=new ClusteredEntity();
  Row row=mock(Row.class);
  when(meta.<ClusteredEntity>getEntityClass()).thenReturn(ClusteredEntity.class);
  when(meta.instanciate()).thenReturn(entity);
  when(iterator.next()).thenReturn(row);
  when(cqlInvoker.invokeOnRowForType(row,String.class,"name")).thenReturn("name1");
  when(context.duplicate(entity)).thenReturn(context);
  when(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,entityFacade)).thenReturn(entity);
  ClusteredEntity actual=sliceIterator.next();
  assertThat(actual).isSameAs(entity);
  verify(meta).intercept(entity,Event.POST_LOAD);
  verify(mapper).setNonCounterPropertiesToEntity(row,meta,entity);
}
