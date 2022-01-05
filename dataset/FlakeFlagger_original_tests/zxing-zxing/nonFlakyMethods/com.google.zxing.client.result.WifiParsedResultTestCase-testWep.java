@Test public void testWep(){
  doTest("WIFI:S:TenChars;P:0123456789;T:WEP;;","TenChars","0123456789","WEP");
  doTest("WIFI:S:TenChars;P:abcde56789;T:WEP;;","TenChars","abcde56789","WEP");
  doTest("WIFI:S:TenChars;P:hellothere;T:WEP;;","TenChars","hellothere","WEP");
  doTest("WIFI:S:Ten\\;\\;Chars;P:0123456789;T:WEP;;","Ten;;Chars","0123456789","WEP");
  doTest("WIFI:S:Ten\\:\\:Chars;P:0123456789;T:WEP;;","Ten::Chars","0123456789","WEP");
}
