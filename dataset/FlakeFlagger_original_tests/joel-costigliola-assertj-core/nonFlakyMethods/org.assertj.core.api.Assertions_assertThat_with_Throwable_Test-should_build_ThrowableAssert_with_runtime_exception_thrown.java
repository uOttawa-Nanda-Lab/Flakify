@Test public void should_build_ThrowableAssert_with_runtime_exception_thrown(){
  assertThatThrownBy(new ThrowingCallable(){
    @Override public void call(){
      throw new IllegalArgumentException("something was wrong");
    }
  }
).isInstanceOf(IllegalArgumentException.class).hasMessage("something was wrong");
}
