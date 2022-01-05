@Test public void testSetAndGet(){
  final Integer testVal=10;
  final InternalThreadLocal<Integer> internalThreadLocal=new InternalThreadLocal<Integer>();
  internalThreadLocal.set(testVal);
  Assert.assertTrue("set is not equals get",Objects.equals(testVal,internalThreadLocal.get()));
}
