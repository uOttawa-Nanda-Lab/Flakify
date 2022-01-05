/** 
 * Test that MockWebServer blocks for a call to enqueue() if a request is made before a mock response is ready.
 */
public void testDispatchBlocksWaitingForEnqueue() throws Exception {
  server.play();
  new Thread(){
    @Override public void run(){
      try {
        Thread.sleep(1000);
      }
 catch (      InterruptedException ignored) {
      }
      server.enqueue(new MockResponse().setBody("enqueued in the background"));
    }
  }
.start();
  URLConnection connection=server.getUrl("/").openConnection();
  InputStream in=connection.getInputStream();
  BufferedReader reader=new BufferedReader(new InputStreamReader(in));
  assertEquals("enqueued in the background",reader.readLine());
}
