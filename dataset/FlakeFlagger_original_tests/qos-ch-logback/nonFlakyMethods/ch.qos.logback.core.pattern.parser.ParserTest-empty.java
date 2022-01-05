@Test public void empty(){
  try {
    Parser p=new Parser("");
    p.parse();
    fail("");
  }
 catch (  ScanException e) {
  }
}
