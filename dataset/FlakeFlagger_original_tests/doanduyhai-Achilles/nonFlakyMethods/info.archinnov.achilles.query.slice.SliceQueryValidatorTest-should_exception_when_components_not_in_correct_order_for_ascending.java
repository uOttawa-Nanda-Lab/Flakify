@Test public void should_exception_when_components_not_in_correct_order_for_ascending() throws Exception {
  List<Object> clusteringsFrom=Arrays.<Object>asList(11);
  List<Object> clusteringsTo=Arrays.<Object>asList(10);
  OrderingMode ordering=OrderingMode.DESCENDING;
  sliceQuery=new SliceQuery<>(Object.class,meta,Arrays.<Object>asList(10L),clusteringsFrom,clusteringsTo,ordering,BoundingMode.INCLUSIVE_BOUNDS,null,100,100,false);
  exception.expect(AchillesException.class);
  exception.expectMessage("For slice query with ascending order, start clustering last component should be 'lesser or equal' to end clustering last component: [[11],[10]");
  validator.validateComponentsForSliceQuery(sliceQuery);
}
