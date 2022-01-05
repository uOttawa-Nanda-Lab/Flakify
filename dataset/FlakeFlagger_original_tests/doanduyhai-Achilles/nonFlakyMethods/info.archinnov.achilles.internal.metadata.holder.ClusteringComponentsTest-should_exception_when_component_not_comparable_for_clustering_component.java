@Test public void should_exception_when_component_not_comparable_for_clustering_component() throws Exception {
  clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(String.class,Integer.class,UserBean.class),null,null,null,null,null);
  UserBean userBean=new UserBean();
  exception.expect(AchillesException.class);
  exception.expectMessage("The type '" + UserBean.class.getCanonicalName() + "' of clustering key '"+ userBean+ "' for querying on entity 'entityClass' should implement the Comparable<T> interface");
  clusteringComponents.validateClusteringComponents("entityClass",Arrays.asList("name",15,userBean));
}
