/** 
 * Set request buffer size to negative value
 */
@Test(expected=IllegalArgumentException.class) public void negativeBufferSize(){
  get("http://localhost").bufferSize(-1);
}
