@Test public void should_exception_when_incorrect_type_of_partition_component_provided() throws Exception {
  partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,Long.class),null,null,null,null);
  exception.expect(AchillesException.class);
  exception.expectMessage("The type 'java.lang.String' of partition key component 'name' for querying on entity 'entityClass' is not valid. It should be 'java.lang.Long'");
  partitionComponents.validatePartitionComponents("entityClass",Arrays.<Object>asList(11L,"name"));
}
