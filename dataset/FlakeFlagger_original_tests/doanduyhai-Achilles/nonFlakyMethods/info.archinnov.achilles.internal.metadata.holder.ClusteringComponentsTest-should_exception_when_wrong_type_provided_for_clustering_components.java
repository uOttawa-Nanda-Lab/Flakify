@Test public void should_exception_when_wrong_type_provided_for_clustering_components() throws Exception {
  clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(String.class,Integer.class,UUID.class),null,null,null,null,null);
  exception.expect(AchillesException.class);
  exception.expectMessage("The type 'java.lang.Long' of clustering key '15' for querying on entity 'entityClass' is not valid. It should be 'java.lang.Integer'");
  clusteringComponents.validateClusteringComponents("entityClass",Arrays.<Object>asList("name",15L,UUID.randomUUID()));
}
