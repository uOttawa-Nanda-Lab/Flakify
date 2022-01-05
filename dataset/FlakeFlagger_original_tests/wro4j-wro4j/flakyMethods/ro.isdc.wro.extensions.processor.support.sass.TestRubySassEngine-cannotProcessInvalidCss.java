@Test(expected=WroRuntimeException.class) public void cannotProcessInvalidCss(){
  Assert.assertEquals(StringUtils.EMPTY,engine.process("invalidCss"));
}
