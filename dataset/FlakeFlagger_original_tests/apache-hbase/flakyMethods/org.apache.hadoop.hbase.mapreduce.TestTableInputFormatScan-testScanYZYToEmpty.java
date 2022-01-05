/** 
 * Tests a MR scan using specific start and stop rows.
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
@Test public void testScanYZYToEmpty() throws IOException, InterruptedException, ClassNotFoundException {
  testScan("yzy",null,"zzz");
}
