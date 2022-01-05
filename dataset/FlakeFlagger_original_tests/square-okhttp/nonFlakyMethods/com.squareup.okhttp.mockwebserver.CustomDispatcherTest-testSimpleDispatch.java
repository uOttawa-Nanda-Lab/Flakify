public void testSimpleDispatch() throws Exception {
  mockWebServer.play();
  final List<RecordedRequest> requestsMade=new ArrayList<RecordedRequest>();
  final Dispatcher dispatcher=new Dispatcher(){
    @Override public MockResponse dispatch(    RecordedRequest request) throws InterruptedException {
      requestsMade.add(request);
      return new MockResponse();
    }
  }
;
  assertEquals(0,requestsMade.size());
  mockWebServer.setDispatcher(dispatcher);
  final URL url=mockWebServer.getUrl("/");
  final HttpURLConnection conn=(HttpURLConnection)url.openConnection();
  conn.getResponseCode();
  assertEquals(1,requestsMade.size());
}
