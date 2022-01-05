@Test public void should_exception_when_partition_components_do_not_match() throws Exception {
  Whitebox.setInternalState(builder,"partitionComponents",Arrays.<Object>asList(10L,"type"));
  exception.expect(AchillesException.class);
  exception.expectMessage("Partition components '[11, type]' do not match previously set values '[10, type]'");
  builder.partitionComponentsInternal(11L,"type");
}
