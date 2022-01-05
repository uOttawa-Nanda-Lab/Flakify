@Test public void assertTransformWithException(){
  assertTrue(ExceptionUtil.transform(new Exception("Exception")).startsWith("java.lang.Exception"));
}
