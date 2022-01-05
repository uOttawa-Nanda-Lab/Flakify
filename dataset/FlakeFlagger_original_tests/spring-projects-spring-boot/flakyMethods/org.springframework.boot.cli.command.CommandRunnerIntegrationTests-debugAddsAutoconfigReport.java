@Test public void debugAddsAutoconfigReport(){
  CommandRunner runner=new CommandRunner("spring");
  runner.addCommand(new RunCommand());
  runner.runAndHandleErrors("run","samples/app.groovy","-d");
  assertTrue(this.output.toString().contains("Negative matches:"));
}
