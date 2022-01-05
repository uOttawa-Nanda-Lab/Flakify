@Test public void should_build_ThrowableAssert_with_throwable_thrown(){
  thenThrownBy(new ThrowingCallable(){
    @Override public void call() throws Throwable {
      throw new Throwable("something was wrong");
    }
  }
).isInstanceOf(Throwable.class).hasMessage("something was wrong");
}
