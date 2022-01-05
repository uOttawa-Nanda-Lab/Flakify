@SuppressWarnings("unchecked") @Test public void should_return_entity_meta_entry_set() throws Exception {
  Entry<Class<?>,EntityMeta> entry=mock(Entry.class);
  when(entityMetaMap.entrySet()).thenReturn(Sets.newHashSet(entry));
  Set<Entry<Class<?>,EntityMeta>> actual=context.entityMetaEntrySet();
  assertThat(actual).containsOnly(entry);
}
