/** 
 * Lazy grow into a variable capacity bit set. 
 */
@Test public void hpackUseCase(){
  BitArray b=new BitArray.FixedCapacity();
  for (int i=0; i < 64; i++) {
    b.set(i);
  }
  assertTrue(b.get(0));
  assertTrue(b.get(1));
  assertTrue(b.get(63));
  try {
    b.get(64);
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
  b=((BitArray.FixedCapacity)b).toVariableCapacity();
  assertTrue(b.get(0));
  assertTrue(b.get(1));
  assertTrue(b.get(63));
  assertFalse(b.get(64));
  b.set(64);
  assertTrue(b.get(64));
}
