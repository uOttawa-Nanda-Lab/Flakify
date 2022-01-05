@Test public void maxFrameSizeHonored() throws Exception {
  peer.setVariantAndClient(HTTP_20_DRAFT_09,false);
  byte[] buff=new byte[HTTP_20_DRAFT_09.maxFrameSize() + 1];
  Arrays.fill(buff,(byte)'*');
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("a","android"));
  peer.acceptFrame();
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,HTTP_20_DRAFT_09);
  SpdyStream stream=connection.newStream(headerEntries("b","banana"),true,true);
  BufferedSink out=Okio.buffer(stream.getSink());
  out.write(buff);
  out.flush();
  out.close();
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  MockSpdyPeer.InFrame data=peer.takeFrame();
  assertEquals(HTTP_20_DRAFT_09.maxFrameSize(),data.data.length);
  data=peer.takeFrame();
  assertEquals(1,data.data.length);
}
