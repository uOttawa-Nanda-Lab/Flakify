@Test public void optionHelp(){
  OptionHandler handler=new OptionHandler();
  handler.option("bar","Bar");
  OptionParsingCommand command=new OptionParsingCommand("foo","Foo",handler){
  }
;
  assertThat(command.getHelp(),containsString("--bar"));
}
