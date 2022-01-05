@Test public void should_exception_when_components_are_not_equal_case2() throws Exception {
  UUID uuid1=new UUID(10,11);
  List<Object> clusteringsFrom=Arrays.<Object>asList(uuid1,"a",null);
  List<Object> clusteringsTo=Arrays.<Object>asList(uuid1,"b",2);
  OrderingMode ordering=OrderingMode.ASCENDING;
  sliceQuery=new SliceQuery<>(Object.class,meta,Arrays.<Object>asList(10L),clusteringsFrom,clusteringsTo,ordering,BoundingMode.INCLUSIVE_BOUNDS,null,100,100,false);
  exception.expect(AchillesException.class);
  exception.expectMessage("2th component for clustering keys should be equal: [[" + uuid1 + ",a,],["+ uuid1+ ",b,2]");
  validator.validateComponentsForSliceQuery(sliceQuery);
}
