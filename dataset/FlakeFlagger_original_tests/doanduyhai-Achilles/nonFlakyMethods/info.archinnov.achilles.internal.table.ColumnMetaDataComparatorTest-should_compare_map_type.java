@Test public void should_compare_map_type() throws Exception {
  sourceColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.map(DataType.text(),DataType.text()));
  targetColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.map(DataType.text(),DataType.text()));
  assertThat(comparator.isEqual(sourceColumnMetadata,targetColumnMetadata)).isTrue();
}
