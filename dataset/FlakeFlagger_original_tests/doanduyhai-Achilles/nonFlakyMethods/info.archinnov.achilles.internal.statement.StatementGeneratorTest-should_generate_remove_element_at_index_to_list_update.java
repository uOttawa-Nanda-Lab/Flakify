@Test public void should_generate_remove_element_at_index_to_list_update() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").accessors().type(ID).invoker(invoker).build();
  Long id=RandomUtils.nextLong();
  Object[] boundValues=new Object[]{"whatever"};
  EntityMeta meta=mock(EntityMeta.class);
  when(meta.getIdMeta()).thenReturn(idMeta);
  when(meta.encodeBoundValuesForTypedQueries(boundValues)).thenReturn(boundValues);
  CompleteBean entity=builder().id(id).buid();
  when(context.getEntity()).thenReturn(entity);
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getCasConditions()).thenReturn(asList(new CASCondition("name","John")));
  when(dirtyCheckChangeSet.getChangeType()).thenReturn(REMOVE_FROM_LIST_AT_INDEX);
  when(dirtyCheckChangeSet.generateUpdateForRemovedAtIndexElement(any(Conditions.class))).thenReturn(Pair.create(update(),boundValues));
  final Pair<Where,Object[]> pair=generator.generateCollectionAndMapUpdateOperation(context,dirtyCheckChangeSet);
  assertThat(pair.left.getQueryString()).isEqualTo("UPDATE table WHERE id=" + id + ";");
  assertThat(pair.right[0]).isEqualTo("whatever");
}
