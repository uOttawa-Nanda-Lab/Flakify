/** 
 * Try to append with wrong number of arguments
 */
@Test(expected=IllegalArgumentException.class) public void appendOddNumberOfParams(){
  HttpRequest.append("http://test.com","1");
}
