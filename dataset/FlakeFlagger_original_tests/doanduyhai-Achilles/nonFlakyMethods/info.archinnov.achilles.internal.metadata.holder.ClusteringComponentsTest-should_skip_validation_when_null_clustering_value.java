@Test public void should_skip_validation_when_null_clustering_value() throws Exception {
  clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(Long.class,String.class,Integer.class,UserBean.class),null,null,null,null,null);
  clusteringComponents.validateClusteringComponents("entityClass",Arrays.<Object>asList(null,null,null));
}
