@Test public void testOnRemove(){
  final Integer[] valueToRemove={null};
  final InternalThreadLocal<Integer> internalThreadLocal=new InternalThreadLocal<Integer>(){
    @Override protected void onRemoval(    Integer value) throws Exception {
      valueToRemove[0]=value + 1;
    }
  }
;
  internalThreadLocal.set(1);
  Assert.assertTrue("get method false!",internalThreadLocal.get() == 1);
  internalThreadLocal.remove();
  Assert.assertTrue("onRemove method failed!",valueToRemove[0] == 2);
}
