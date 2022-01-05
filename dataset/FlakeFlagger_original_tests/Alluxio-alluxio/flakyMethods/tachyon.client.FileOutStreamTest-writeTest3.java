/** 
 * Test <code>void write(byte[] b, int off, int len)</code>.
 */
@Test public void writeTest3() throws IOException, InvalidPathException, FileAlreadyExistException {
  for (int k=MIN_LEN; k <= MAX_LEN; k+=DELTA) {
    for (    WriteType op : WriteType.values()) {
      writeTest3Util("/root/testFile_" + k + "_"+ op,op,k);
    }
  }
}
