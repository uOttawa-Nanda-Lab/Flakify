@Test public void testHttpsCreateConnection() throws Exception {
  pool=new BasicConnPool((SSLSocketFactory)SSLSocketFactory.getDefault(),params);
  host=new HttpHost("localhost",sslServerPort,"https");
  conn=pool.createConnection(host);
  assertEquals(true,conn.isOpen());
  assertEquals(100,conn.getSocketTimeout());
}
