/** 
 * Put in checks for the length of the password for wep.
 */
@Test public void testWpa(){
  doTest("WIFI:S:TenChars;P:wow;T:WPA;;","TenChars","wow","WPA");
  doTest("WIFI:S:TenChars;P:space is silent;T:WPA;;","TenChars","space is silent","WPA");
  doTest("WIFI:S:TenChars;P:hellothere;T:WEP;;","TenChars","hellothere","WEP");
  doTest("WIFI:S:TenChars;P:hello\\;there;T:WEP;;","TenChars","hello;there","WEP");
  doTest("WIFI:S:TenChars;P:hello\\:there;T:WEP;;","TenChars","hello:there","WEP");
}
