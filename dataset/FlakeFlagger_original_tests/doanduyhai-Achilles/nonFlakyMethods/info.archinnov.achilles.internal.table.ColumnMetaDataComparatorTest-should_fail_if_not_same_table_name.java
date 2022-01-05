@Test public void should_fail_if_not_same_table_name() throws Exception {
  TableMetadata tableMeta2=mock(TableMetadata.class);
  when(tableMeta2.getName()).thenReturn("table2");
  sourceColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.set(DataType.text()));
  targetColumnMetadata=ColumnMetadataBuilder.create(tableMeta2,"value",DataType.set(DataType.text()));
  assertThat(comparator.isEqual(sourceColumnMetadata,targetColumnMetadata)).isFalse();
}
