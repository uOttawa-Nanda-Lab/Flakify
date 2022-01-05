public void testDeleteTable() throws Exception {
  assertTrue(remoteAdmin.isTableAvailable(TABLE_2));
  remoteAdmin.deleteTable(TABLE_2);
  assertFalse(remoteAdmin.isTableAvailable(TABLE_2));
}
