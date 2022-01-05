@Test public void assertTransformWithError(){
  assertTrue(ExceptionUtil.transform(new Error("Error")).startsWith("java.lang.Error"));
}
