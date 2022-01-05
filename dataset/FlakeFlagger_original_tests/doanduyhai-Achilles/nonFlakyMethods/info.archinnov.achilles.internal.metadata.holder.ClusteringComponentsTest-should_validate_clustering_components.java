@Test public void should_validate_clustering_components() throws Exception {
  clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(String.class,Integer.class),null,null,null,null,null);
  clusteringComponents.validateClusteringComponents("entityClass",Arrays.<Object>asList("name",13));
}
