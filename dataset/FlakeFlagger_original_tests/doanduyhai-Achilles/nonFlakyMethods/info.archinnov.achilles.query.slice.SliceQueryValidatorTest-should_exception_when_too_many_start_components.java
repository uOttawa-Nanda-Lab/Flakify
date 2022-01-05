@Test public void should_exception_when_too_many_start_components() throws Exception {
  UUID uuid1=new UUID(10,11);
  List<Object> clusteringsFrom=Arrays.<Object>asList(uuid1,"a",1);
  List<Object> clusteringsTo=Arrays.<Object>asList(uuid1,null,null);
  OrderingMode ordering=OrderingMode.ASCENDING;
  sliceQuery=new SliceQuery<>(Object.class,meta,Arrays.<Object>asList(10L),clusteringsFrom,clusteringsTo,ordering,BoundingMode.INCLUSIVE_BOUNDS,null,100,100,false);
  exception.expect(AchillesException.class);
  exception.expectMessage("There should be no more than 1 component difference between clustering keys: [[" + uuid1 + ",a,1],["+ uuid1+ ",,]");
  validator.validateComponentsForSliceQuery(sliceQuery);
}
