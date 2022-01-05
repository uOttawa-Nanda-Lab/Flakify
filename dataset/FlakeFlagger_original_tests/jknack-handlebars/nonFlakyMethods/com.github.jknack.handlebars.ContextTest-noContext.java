@Test(expected=IllegalArgumentException.class) public void noContext(){
  Map<String,Object> model=new HashMap<String,Object>();
  Context context=Context.newContext(model);
  assertEquals(context,Context.newContext(context));
}
