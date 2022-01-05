@Test public void testBasic(){
  Throwable t=new Throwable();
  StackTraceElement[] steArray=t.getStackTrace();
  StackTraceElement[] cda=CallerData.extract(t,CallerDataTest.class.getName(),50,null);
  assertNotNull(cda);
  assertTrue(cda.length > 0);
  assertEquals(steArray.length - 1,cda.length);
}
