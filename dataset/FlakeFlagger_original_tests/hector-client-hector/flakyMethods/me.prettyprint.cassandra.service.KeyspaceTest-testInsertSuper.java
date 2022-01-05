/** 
 * Test insertion of a supercolumn using insert
 */
@Test public void testInsertSuper() throws IllegalArgumentException, NoSuchElementException, IllegalStateException, HNotFoundException, Exception {
  ColumnParent columnParent=new ColumnParent("Super1");
  columnParent.setSuper_column(StringSerializer.get().toByteBuffer("testInsertSuper_super"));
  Column column=new Column(StringSerializer.get().toByteBuffer("testInsertSuper_column"),StringSerializer.get().toByteBuffer("testInsertSuper_value"),connectionManager.createClock());
  keyspace.insert(StringSerializer.get().toByteBuffer("testInsertSuper_key"),columnParent,column);
  column.setName(StringSerializer.get().toByteBuffer("testInsertSuper_column2"));
  keyspace.insert(StringSerializer.get().toByteBuffer("testInsertSuper_key"),columnParent,column);
  ColumnPath cp2=new ColumnPath("Super1");
  cp2.setSuper_column(bytes("testInsertSuper_super"));
  SuperColumn sc=keyspace.getSuperColumn("testInsertSuper_key",cp2);
  assertNotNull(sc);
  assertEquals("testInsertSuper_super",string(sc.getName()));
  assertEquals(2,sc.getColumns().size());
  assertEquals("testInsertSuper_value",string(sc.getColumns().get(0).getValue()));
  keyspace.remove("testInsertSuper_super",cp2);
}
