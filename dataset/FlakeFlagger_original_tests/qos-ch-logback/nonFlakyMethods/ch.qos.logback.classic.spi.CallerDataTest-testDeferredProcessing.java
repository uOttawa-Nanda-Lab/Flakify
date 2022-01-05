/** 
 * This test verifies that in case caller data cannot be extracted, CallerData.extract does not throw an exception
 */
@Test public void testDeferredProcessing(){
  StackTraceElement[] cda=CallerData.extract(new Throwable(),"com.inexistent.foo",10,null);
  assertNotNull(cda);
  assertEquals(0,cda.length);
}
