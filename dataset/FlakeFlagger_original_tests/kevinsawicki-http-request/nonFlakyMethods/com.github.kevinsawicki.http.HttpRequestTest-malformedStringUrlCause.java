/** 
 * Create request with malformed URL
 */
@Test public void malformedStringUrlCause(){
  try {
    delete("\\m/");
    fail("Exception not thrown");
  }
 catch (  HttpRequestException e) {
    assertNotNull(e.getCause());
  }
}
