@Test public void testCreateEntry() throws Exception {
  IOSession conn=pool.createConnection(route,session);
  BasicNIOPoolEntry entry=pool.createEntry(route,conn);
  pool.closeEntry(entry);
}
