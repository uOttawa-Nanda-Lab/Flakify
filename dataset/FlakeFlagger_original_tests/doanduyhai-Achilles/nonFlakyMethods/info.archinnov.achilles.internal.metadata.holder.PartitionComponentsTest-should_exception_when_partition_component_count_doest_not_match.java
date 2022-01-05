@Test public void should_exception_when_partition_component_count_doest_not_match() throws Exception {
  partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,String.class),null,null,null,null);
  exception.expect(AchillesException.class);
  exception.expectMessage("There should be exactly '2' partition components for querying on entity 'entityClass'");
  partitionComponents.validatePartitionComponents("entityClass",Arrays.<Object>asList(11L,"test",11));
}
