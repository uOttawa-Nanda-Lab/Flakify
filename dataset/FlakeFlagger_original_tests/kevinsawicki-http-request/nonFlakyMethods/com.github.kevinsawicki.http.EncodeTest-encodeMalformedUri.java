/** 
 * Encoding malformed URI
 */
@Test(expected=HttpRequestException.class) public void encodeMalformedUri(){
  HttpRequest.encode("\\m/");
}
