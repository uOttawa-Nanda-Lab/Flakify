/** 
 * Test memstore snapshots
 * @throws IOException
 */
public void testSnapshotting() throws IOException {
  final int snapshotCount=5;
  for (int i=0; i < snapshotCount; i++) {
    addRows(this.memstore);
    runSnapshot(this.memstore);
    KeyValueSkipListSet ss=this.memstore.getSnapshot();
    assertEquals("History not being cleared",0,ss.size());
  }
}
