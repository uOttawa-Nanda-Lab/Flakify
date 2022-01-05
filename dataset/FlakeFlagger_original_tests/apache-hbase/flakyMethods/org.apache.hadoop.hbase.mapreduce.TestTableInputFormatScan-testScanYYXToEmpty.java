/** 
 * Tests a MR scan using specific start and stop rows.
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
@Test public void testScanYYXToEmpty() throws IOException, InterruptedException, ClassNotFoundException {
  testScan("yyx",null,"zzz");
}
