/** 
 * test connection sharing
 */
@Test public void test_share_connect(){
  init(0);
  Assert.assertEquals(demoClient.getLocalAddress(),helloClient.getLocalAddress());
  Assert.assertEquals(demoClient,helloClient);
  destoy();
}
