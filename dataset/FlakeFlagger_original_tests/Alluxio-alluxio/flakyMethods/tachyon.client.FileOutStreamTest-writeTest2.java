/** 
 * Test <code>void write(byte b[])</code>.
 */
@Test public void writeTest2() throws IOException, InvalidPathException, FileAlreadyExistException {
  for (int k=MIN_LEN; k <= MAX_LEN; k+=DELTA) {
    for (    WriteType op : WriteType.values()) {
      writeTest2Util("/root/testFile_" + k + "_"+ op,op,k);
    }
  }
}
