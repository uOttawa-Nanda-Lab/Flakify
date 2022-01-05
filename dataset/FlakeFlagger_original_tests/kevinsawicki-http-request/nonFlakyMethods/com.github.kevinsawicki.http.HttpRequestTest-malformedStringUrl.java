/** 
 * Create request with malformed URL
 */
@Test(expected=HttpRequestException.class) public void malformedStringUrl(){
  get("\\m/");
}
