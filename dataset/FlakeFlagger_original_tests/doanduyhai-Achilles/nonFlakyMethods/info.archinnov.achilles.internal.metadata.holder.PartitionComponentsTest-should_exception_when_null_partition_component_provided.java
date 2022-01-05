@Test public void should_exception_when_null_partition_component_provided() throws Exception {
  partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,String.class),null,null,null,null);
  exception.expect(AchillesException.class);
  exception.expectMessage("The '2th' partition component should not be null");
  partitionComponents.validatePartitionComponents("entityClass",Arrays.<Object>asList(10L,null));
}
