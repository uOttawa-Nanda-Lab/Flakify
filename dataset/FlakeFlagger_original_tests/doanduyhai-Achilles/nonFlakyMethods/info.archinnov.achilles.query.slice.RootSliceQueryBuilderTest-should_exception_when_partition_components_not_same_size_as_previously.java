@Test public void should_exception_when_partition_components_not_same_size_as_previously() throws Exception {
  Whitebox.setInternalState(builder,"partitionComponents",Arrays.<Object>asList(10L,"type"));
  exception.expect(AchillesException.class);
  exception.expectMessage("Partition components '[11, type, 11]' do not match previously set values '[10, type]'");
  builder.partitionComponentsInternal(11L,"type",11);
}
