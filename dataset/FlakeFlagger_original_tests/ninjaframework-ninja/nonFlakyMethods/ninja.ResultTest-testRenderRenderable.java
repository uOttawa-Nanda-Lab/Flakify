@Test public void testRenderRenderable(){
  Renderable renderable=new Renderable(){
    @Override public void render(    Context context,    Result result) throws Exception {
    }
  }
;
  Result result=new Result(200);
  result.render(renderable);
  assertEquals(renderable,result.getRenderable());
  boolean gotException=true;
  try {
    result.render(new TestObject());
  }
 catch (  IllegalArgumentException e) {
    gotException=true;
  }
  assertTrue(gotException);
}
