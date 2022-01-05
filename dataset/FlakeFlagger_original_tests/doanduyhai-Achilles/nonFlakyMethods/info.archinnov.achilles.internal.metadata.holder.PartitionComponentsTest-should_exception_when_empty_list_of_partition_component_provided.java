@Test public void should_exception_when_empty_list_of_partition_component_provided() throws Exception {
  partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,String.class),null,null,null,null);
  exception.expect(AchillesException.class);
  exception.expectMessage("There should be at least one partition key component provided for querying on entity 'entityClass'");
  partitionComponents.validatePartitionComponents("entityClass",Arrays.<Object>asList());
}
