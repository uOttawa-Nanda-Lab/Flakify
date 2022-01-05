@Test public void testSetter() throws URISyntaxException {
  WebSocketClient client=new WebSocketClient(new URI("ws://localhost")){
    @Override public void onOpen(    ServerHandshake handshakedata){
    }
    @Override public void onMessage(    String message){
    }
    @Override public void onClose(    int code,    String reason,    boolean remote){
    }
    @Override public void onError(    Exception ex){
    }
  }
;
  assertNull(client.<WebSocketClient>getAttachment());
  client.setAttachment(client);
  assertEquals(client.<WebSocketClient>getAttachment(),client);
  client.setAttachment(null);
  assertNull(client.<WebSocketClient>getAttachment());
}
