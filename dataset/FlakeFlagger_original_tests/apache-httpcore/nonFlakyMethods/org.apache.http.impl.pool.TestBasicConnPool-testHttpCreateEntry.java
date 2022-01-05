@Test public void testHttpCreateEntry() throws Exception {
  host=new HttpHost("localhost",serverPort,"http");
  conn=pool.createConnection(host);
  BasicPoolEntry entry=pool.createEntry(host,conn);
  assertEquals(conn,entry.getConnection());
  assertEquals("localhost",entry.getRoute().getHostName());
  pool.closeEntry(entry);
}
