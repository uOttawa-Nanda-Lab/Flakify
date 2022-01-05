public void testGetMetaDataForUnexistingTable(){
  TableMetaData metaData=managementService.getTableMetaData("unexistingtable");
  assertNull(metaData);
}
