/** 
 * Test empty HFile. Test all features work reasonably when hfile is empty of entries.
 * @throws IOException
 */
public void testEmptyHFile() throws IOException {
  Path f=new Path(ROOT_DIR,getName());
  Writer w=new Writer(this.fs,f);
  w.close();
  Reader r=new Reader(fs,f,null,false);
  r.loadFileInfo();
  assertNull(r.getFirstKey());
  assertNull(r.getLastKey());
}
