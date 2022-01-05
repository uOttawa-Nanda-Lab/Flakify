/** 
 * JMS message is send using connection factory with transactional = false. Message should be delivered - main reason of fix.
 */
@Test public void sendIgnoreJTA() throws Exception {
  sender.send(true,false);
  latch.await(MAX_WAIT_IN_SECONDS,SECONDS);
  assertEquals(0,latch.getCount());
}
