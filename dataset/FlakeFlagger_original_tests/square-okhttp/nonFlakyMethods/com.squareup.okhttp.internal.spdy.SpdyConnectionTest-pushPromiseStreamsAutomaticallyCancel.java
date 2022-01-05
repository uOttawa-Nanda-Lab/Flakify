@Test public void pushPromiseStreamsAutomaticallyCancel() throws Exception {
  peer.setVariantAndClient(HTTP_20_DRAFT_09,false);
  peer.sendFrame().pushPromise(1,2,Arrays.asList(new Header(Header.TARGET_METHOD,"GET"),new Header(Header.TARGET_SCHEME,"https"),new Header(Header.TARGET_AUTHORITY,"squareup.com"),new Header(Header.TARGET_PATH,"/cached")));
  peer.sendFrame().synReply(true,2,Arrays.asList(new Header(Header.RESPONSE_STATUS,"200")));
  peer.acceptFrame();
  peer.play();
  connectionBuilder(peer,HTTP_20_DRAFT_09).pushObserver(PushObserver.CANCEL).build();
  MockSpdyPeer.InFrame rstStream=peer.takeFrame();
  assertEquals(TYPE_RST_STREAM,rstStream.type);
  assertEquals(2,rstStream.streamId);
  assertEquals(CANCEL,rstStream.errorCode);
}
