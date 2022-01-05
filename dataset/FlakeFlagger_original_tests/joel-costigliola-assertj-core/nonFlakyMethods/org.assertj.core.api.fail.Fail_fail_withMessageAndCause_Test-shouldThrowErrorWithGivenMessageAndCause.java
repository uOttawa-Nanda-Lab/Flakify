@Test public void shouldThrowErrorWithGivenMessageAndCause(){
  String message="Some Throwable";
  Throwable cause=new Throwable();
  try {
    Fail.fail(message,cause);
    fail("AssertionError should have been thrown");
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),message);
    assertSame(e.getCause(),cause);
  }
}
