public void testAddArgumentWithBothQuotes(){
  CommandLine cmdl=new CommandLine("test");
  try {
    cmdl.addArgument("b\"a'r");
    fail("IllegalArgumentException should be thrown");
  }
 catch (  IllegalArgumentException e) {
  }
}
