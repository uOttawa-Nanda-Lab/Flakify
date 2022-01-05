@Test public void should_validate_partition_components() throws Exception {
  partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,String.class),null,null,null,null);
  partitionComponents.validatePartitionComponents("classname",Arrays.<Object>asList(11L,"type"));
}
