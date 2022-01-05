@Test public void doublePushPromise() throws Exception {
  peer.setVariantAndClient(HTTP_20_DRAFT_09,false);
  peer.sendFrame().pushPromise(1,2,headerEntries("a","android"));
  peer.acceptFrame();
  peer.sendFrame().pushPromise(1,2,headerEntries("b","banana"));
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connectionBuilder(peer,HTTP_20_DRAFT_09).build();
  connection.newStream(headerEntries("b","banana"),false,true);
  assertEquals(TYPE_HEADERS,peer.takeFrame().type);
  assertEquals(PROTOCOL_ERROR,peer.takeFrame().errorCode);
}
