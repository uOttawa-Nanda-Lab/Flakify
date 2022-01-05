@SuppressWarnings("unchecked") @Test public void should_set_partition_components() throws Exception {
  builder.partitionComponentsInternal(11L);
  verify(idMeta).validatePartitionComponents(Arrays.<Object>asList(11L));
  assertThat((List<Object>)Whitebox.getInternalState(builder,"partitionComponents")).containsExactly(11L);
}
