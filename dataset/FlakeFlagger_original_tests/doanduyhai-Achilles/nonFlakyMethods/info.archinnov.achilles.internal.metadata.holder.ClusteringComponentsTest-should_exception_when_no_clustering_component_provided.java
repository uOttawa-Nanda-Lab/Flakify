@Test public void should_exception_when_no_clustering_component_provided() throws Exception {
  clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(String.class,Integer.class),null,null,null,null,null);
  exception.expect(AchillesException.class);
  exception.expectMessage("There should be at least one clustering key provided for querying on entity 'entityClass'");
  clusteringComponents.validateClusteringComponents("entityClass",null);
}
