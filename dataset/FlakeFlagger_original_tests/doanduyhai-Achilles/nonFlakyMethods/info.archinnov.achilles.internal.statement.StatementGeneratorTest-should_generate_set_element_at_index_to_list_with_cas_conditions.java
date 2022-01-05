@Test public void should_generate_set_element_at_index_to_list_with_cas_conditions() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").accessors().type(ID).invoker(invoker).build();
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  meta.setPropertyMetas(ImmutableMap.of("id",idMeta));
  meta.setIdMeta(idMeta);
  Long id=RandomUtils.nextLong();
  Object[] boundValues=new Object[]{"whatever"};
  CompleteBean entity=builder().id(id).buid();
  when(context.getEntity()).thenReturn(entity);
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getIdMeta()).thenReturn(idMeta);
  when(dirtyCheckChangeSet.getChangeType()).thenReturn(SET_TO_LIST_AT_INDEX);
  when(dirtyCheckChangeSet.generateUpdateForSetAtIndexElement(any(Conditions.class))).thenReturn(Pair.create(update(),boundValues));
  final Pair<Where,Object[]> pair=generator.generateCollectionAndMapUpdateOperation(context,dirtyCheckChangeSet);
  assertThat(pair.left.getQueryString()).isEqualTo("UPDATE table WHERE id=" + id + ";");
  assertThat(pair.right[0]).isEqualTo("whatever");
}
