/** 
 * Test <code>void write(int b)</code>.
 */
@Test public void writeTest1() throws IOException, InvalidPathException, FileAlreadyExistException {
  for (int k=MIN_LEN; k <= MAX_LEN; k+=DELTA) {
    for (    WriteType op : WriteType.values()) {
      writeTest1Util("/root/testFile_" + k + "_"+ op,op,k);
    }
  }
}
