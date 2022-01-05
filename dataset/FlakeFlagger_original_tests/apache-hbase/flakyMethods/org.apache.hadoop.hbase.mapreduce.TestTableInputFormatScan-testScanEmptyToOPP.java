/** 
 * Tests a MR scan using specific start and stop rows.
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
@Test public void testScanEmptyToOPP() throws IOException, InterruptedException, ClassNotFoundException {
  testScan(null,"opp","opo");
}
