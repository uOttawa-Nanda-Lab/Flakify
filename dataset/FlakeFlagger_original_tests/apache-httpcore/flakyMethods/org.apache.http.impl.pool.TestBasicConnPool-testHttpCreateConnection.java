@Test public void testHttpCreateConnection() throws Exception {
  host=new HttpHost("localhost",serverPort,"http");
  conn=pool.createConnection(host);
  assertEquals(true,conn.isOpen());
  assertEquals(100,conn.getSocketTimeout());
}
