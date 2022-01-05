/** 
 * JMS message is send using connection factory with transactional = true. Messaging behaves as a part of JTA transaction, message should be delivered.
 */
@Test public void sendInJTA() throws Exception {
  sender.send(false,false);
  latch.await(MAX_WAIT_IN_SECONDS,SECONDS);
  assertEquals(0,latch.getCount());
}
