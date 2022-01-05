public void testBinarySearch() throws Exception {
  byte[][] arr={{1},{3},{5},{7},{9},{11},{13},{15}};
  byte[] key1={3,1};
  byte[] key2={4,9};
  byte[] key2_2={4};
  byte[] key3={5,11};
  assertEquals(1,Bytes.binarySearch(arr,key1,0,1,Bytes.BYTES_RAWCOMPARATOR));
  assertEquals(0,Bytes.binarySearch(arr,key1,1,1,Bytes.BYTES_RAWCOMPARATOR));
  assertEquals(-(2 + 1),Arrays.binarySearch(arr,key2_2,Bytes.BYTES_COMPARATOR));
  assertEquals(-(2 + 1),Bytes.binarySearch(arr,key2,0,1,Bytes.BYTES_RAWCOMPARATOR));
  assertEquals(4,Bytes.binarySearch(arr,key2,1,1,Bytes.BYTES_RAWCOMPARATOR));
  assertEquals(2,Bytes.binarySearch(arr,key3,0,1,Bytes.BYTES_RAWCOMPARATOR));
  assertEquals(5,Bytes.binarySearch(arr,key3,1,1,Bytes.BYTES_RAWCOMPARATOR));
}
