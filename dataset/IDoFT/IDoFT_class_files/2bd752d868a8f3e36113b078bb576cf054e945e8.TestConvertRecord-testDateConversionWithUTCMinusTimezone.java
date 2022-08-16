/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.nifi.processors.standard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.avro.file.DataFileStream;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.lang3.SystemUtils;
import org.apache.nifi.avro.AvroRecordSetWriter;
import org.apache.nifi.avro.NonCachingDatumReader;
import org.apache.nifi.csv.CSVReader;
import org.apache.nifi.csv.CSVRecordSetWriter;
import org.apache.nifi.csv.CSVUtils;
import org.apache.nifi.json.JsonRecordSetWriter;
import org.apache.nifi.json.JsonTreeReader;
import org.apache.nifi.reporting.InitializationException;
import org.apache.nifi.schema.access.SchemaAccessUtils;
import org.apache.nifi.serialization.DateTimeUtils;
import org.apache.nifi.serialization.record.MockRecordParser;
import org.apache.nifi.serialization.record.MockRecordWriter;
import org.apache.nifi.serialization.record.RecordFieldType;
import org.apache.nifi.util.MockFlowFile;
import org.apache.nifi.util.TestRunner;
import org.apache.nifi.util.TestRunners;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xerial.snappy.SnappyInputStream;

public class TestConvertRecord {

    //Apparently pretty printing is not portable as these tests fail on windows
    @BeforeClass
    public static void setUpSuite() {
        Assume.assumeTrue("Test only runs on *nix", !SystemUtils.IS_OS_WINDOWS);
    }

    @Test
    public void testSuccessfulConversion() throws InitializationException {
        final MockRecordParser readerService = new MockRecordParser();
        final MockRecordWriter writerService = new MockRecordWriter("header", false);

        final TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);
        runner.addControllerService("reader", readerService);
        runner.enableControllerService(readerService);
        runner.addControllerService("writer", writerService);
        runner.enableControllerService(writerService);

        runner.setProperty(ConvertRecord.RECORD_READER, "reader");
        runner.setProperty(ConvertRecord.RECORD_WRITER, "writer");

        readerService.addSchemaField("name", RecordFieldType.STRING);
        readerService.addSchemaField("age", RecordFieldType.INT);

        readerService.addRecord("John Doe", 48);
        readerService.addRecord("Jane Doe", 47);
        readerService.addRecord("Jimmy Doe", 14);

        runner.enqueue("");
        runner.run();

        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_SUCCESS, 1);
        final MockFlowFile out = runner.getFlowFilesForRelationship(ConvertRecord.REL_SUCCESS).get(0);

        out.assertAttributeEquals("record.count", "3");
        out.assertAttributeEquals("mime.type", "text/plain");
        out.assertContentEquals("header\nJohn Doe,48\nJane Doe,47\nJimmy Doe,14\n");
    }

    @Test
    public void testDropEmpty() throws InitializationException {
        final MockRecordParser readerService = new MockRecordParser();
        final MockRecordWriter writerService = new MockRecordWriter("header", false);

        final TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);
        runner.addControllerService("reader", readerService);
        runner.enableControllerService(readerService);
        runner.addControllerService("writer", writerService);
        runner.enableControllerService(writerService);

        runner.setProperty(ConvertRecord.INCLUDE_ZERO_RECORD_FLOWFILES, "false");
        runner.setProperty(ConvertRecord.RECORD_READER, "reader");
        runner.setProperty(ConvertRecord.RECORD_WRITER, "writer");

        readerService.addSchemaField("name", RecordFieldType.STRING);
        readerService.addSchemaField("age", RecordFieldType.INT);

        runner.enqueue("");
        runner.run();

        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_SUCCESS, 0);
        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_FAILURE, 0);

        readerService.addRecord("John Doe", 48);
        readerService.addRecord("Jane Doe", 47);
        readerService.addRecord("Jimmy Doe", 14);

        runner.enqueue("");
        runner.run();

        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_SUCCESS, 1);
        final MockFlowFile out = runner.getFlowFilesForRelationship(ConvertRecord.REL_SUCCESS).get(0);

        out.assertAttributeEquals("record.count", "3");
        out.assertAttributeEquals("mime.type", "text/plain");
        out.assertContentEquals("header\nJohn Doe,48\nJane Doe,47\nJimmy Doe,14\n");
    }

    @Test
    public void testReadFailure() throws InitializationException, IOException {
        final MockRecordParser readerService = new MockRecordParser(2);
        final MockRecordWriter writerService = new MockRecordWriter("header", false);

        final TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);
        runner.addControllerService("reader", readerService);
        runner.enableControllerService(readerService);
        runner.addControllerService("writer", writerService);
        runner.enableControllerService(writerService);

        runner.setProperty(ConvertRecord.RECORD_READER, "reader");
        runner.setProperty(ConvertRecord.RECORD_WRITER, "writer");

        readerService.addSchemaField("name", RecordFieldType.STRING);
        readerService.addSchemaField("age", RecordFieldType.INT);

        readerService.addRecord("John Doe", 48);
        readerService.addRecord("Jane Doe", 47);
        readerService.addRecord("Jimmy Doe", 14);

        final MockFlowFile original = runner.enqueue("hello");
        runner.run();

        // Original FlowFile should be routed to 'failure' relationship without modification
        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_FAILURE, 1);
        final MockFlowFile out = runner.getFlowFilesForRelationship(ConvertRecord.REL_FAILURE).get(0);
        out.assertContentEquals(original.toByteArray());
        out.assertAttributeEquals("record.error.message","Intentional Unit Test Exception because 2 records have been read");
    }


    @Test
    public void testWriteFailure() throws InitializationException, IOException {
        final MockRecordParser readerService = new MockRecordParser();
        final MockRecordWriter writerService = new MockRecordWriter("header", false, 2);

        final TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);
        runner.addControllerService("reader", readerService);
        runner.enableControllerService(readerService);
        runner.addControllerService("writer", writerService);
        runner.enableControllerService(writerService);

        runner.setProperty(ConvertRecord.RECORD_READER, "reader");
        runner.setProperty(ConvertRecord.RECORD_WRITER, "writer");

        readerService.addSchemaField("name", RecordFieldType.STRING);
        readerService.addSchemaField("age", RecordFieldType.INT);

        readerService.addRecord("John Doe", 48);
        readerService.addRecord("Jane Doe", 47);
        readerService.addRecord("Jimmy Doe", 14);

        final MockFlowFile original = runner.enqueue("hello");
        runner.run();

        // Original FlowFile should be routed to 'failure' relationship without modification
        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_FAILURE, 1);
        final MockFlowFile out = runner.getFlowFilesForRelationship(ConvertRecord.REL_FAILURE).get(0);
        out.assertContentEquals(original.toByteArray());
        out.assertAttributeEquals("record.error.message","Unit Test intentionally throwing IOException after 2 records were written");
    }

    @Test
    public void testJSONCompression() throws InitializationException, IOException {
        final TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);
        final JsonTreeReader jsonReader = new JsonTreeReader();
        runner.addControllerService("reader", jsonReader);

        final String inputSchemaText = new String(Files.readAllBytes(Paths.get("src/test/resources/TestConvertRecord/schema/person.avsc")));
        final String outputSchemaText = new String(Files.readAllBytes(Paths.get("src/test/resources/TestConvertRecord/schema/person.avsc")));

        runner.setProperty(jsonReader, SchemaAccessUtils.SCHEMA_ACCESS_STRATEGY, SchemaAccessUtils.SCHEMA_TEXT_PROPERTY);
        runner.setProperty(jsonReader, SchemaAccessUtils.SCHEMA_TEXT, inputSchemaText);
        runner.enableControllerService(jsonReader);

        final JsonRecordSetWriter jsonWriter = new JsonRecordSetWriter();
        runner.addControllerService("writer", jsonWriter);
        runner.setProperty(jsonWriter, SchemaAccessUtils.SCHEMA_ACCESS_STRATEGY, SchemaAccessUtils.SCHEMA_TEXT_PROPERTY);
        runner.setProperty(jsonWriter, SchemaAccessUtils.SCHEMA_TEXT, outputSchemaText);
        runner.setProperty(jsonWriter, "Pretty Print JSON", "true");
        runner.setProperty(jsonWriter, "Schema Write Strategy", "full-schema-attribute");
        runner.setProperty(jsonWriter, "compression-format", "snappy");
        runner.enableControllerService(jsonWriter);

        runner.enqueue(Paths.get("src/test/resources/TestConvertRecord/input/person.json"));

        runner.setProperty(ConvertRecord.RECORD_READER, "reader");
        runner.setProperty(ConvertRecord.RECORD_WRITER, "writer");

        runner.run();
        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_SUCCESS, 1);

        MockFlowFile flowFile = runner.getFlowFilesForRelationship(ConvertRecord.REL_SUCCESS).get(0);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (final SnappyInputStream sis = new SnappyInputStream(new ByteArrayInputStream(flowFile.toByteArray())); final OutputStream out = baos) {
            final byte[] buffer = new byte[8192]; int len;
            while ((len = sis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        }

        assertEquals(new String(Files.readAllBytes(Paths.get("src/test/resources/TestConvertRecord/input/person.json"))), baos.toString(StandardCharsets.UTF_8.name()));
    }

    @Test
    public void testCSVFormattingWithEL() throws InitializationException {
        TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);

        CSVReader csvReader = new CSVReader();
        runner.addControllerService("csv-reader", csvReader);
        runner.setProperty(csvReader, CSVUtils.VALUE_SEPARATOR, "${csv.in.delimiter}");
        runner.setProperty(csvReader, CSVUtils.QUOTE_CHAR, "${csv.in.quote}");
        runner.setProperty(csvReader, CSVUtils.ESCAPE_CHAR, "${csv.in.escape}");
        runner.setProperty(csvReader, CSVUtils.COMMENT_MARKER, "${csv.in.comment}");
        runner.enableControllerService(csvReader);

        CSVRecordSetWriter csvWriter = new CSVRecordSetWriter();
        runner.addControllerService("csv-writer", csvWriter);
        runner.setProperty(csvWriter, CSVUtils.VALUE_SEPARATOR, "${csv.out.delimiter}");
        runner.setProperty(csvWriter, CSVUtils.QUOTE_CHAR, "${csv.out.quote}");
        runner.setProperty(csvWriter, CSVUtils.QUOTE_MODE, CSVUtils.QUOTE_ALL);
        runner.enableControllerService(csvWriter);

        runner.setProperty(ConvertRecord.RECORD_READER, "csv-reader");
        runner.setProperty(ConvertRecord.RECORD_WRITER, "csv-writer");

        String ffContent = "~ comment\n" +
                "id|username|password\n" +
                "123|'John'|^|^'^^\n";

        Map<String, String> ffAttributes = new HashMap<>();
        ffAttributes.put("csv.in.delimiter", "|");
        ffAttributes.put("csv.in.quote", "'");
        ffAttributes.put("csv.in.escape", "^");
        ffAttributes.put("csv.in.comment", "~");
        ffAttributes.put("csv.out.delimiter", "\t");
        ffAttributes.put("csv.out.quote", "`");

        runner.enqueue(ffContent, ffAttributes);
        runner.run();

        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_SUCCESS, 1);

        MockFlowFile flowFile = runner.getFlowFilesForRelationship(ConvertRecord.REL_SUCCESS).get(0);

        String expected = "`id`\t`username`\t`password`\n" +
                "`123`\t`John`\t`|'^`\n";
        assertEquals(expected, new String(flowFile.toByteArray()));
    }

    @Test
    public void testJSONLongToInt() throws InitializationException, IOException {
        final TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);
        final JsonTreeReader jsonReader = new JsonTreeReader();
        runner.addControllerService("reader", jsonReader);

        final String inputSchemaText = new String(Files.readAllBytes(Paths.get("src/test/resources/TestConvertRecord/schema/person.avsc")));
        final String outputSchemaText = new String(Files.readAllBytes(Paths.get("src/test/resources/TestConvertRecord/schema/person.avsc")));

        runner.setProperty(jsonReader, SchemaAccessUtils.SCHEMA_ACCESS_STRATEGY, SchemaAccessUtils.SCHEMA_TEXT_PROPERTY);
        runner.setProperty(jsonReader, SchemaAccessUtils.SCHEMA_TEXT, inputSchemaText);
        runner.enableControllerService(jsonReader);

        final JsonRecordSetWriter jsonWriter = new JsonRecordSetWriter();
        runner.addControllerService("writer", jsonWriter);
        runner.setProperty(jsonWriter, SchemaAccessUtils.SCHEMA_ACCESS_STRATEGY, SchemaAccessUtils.SCHEMA_TEXT_PROPERTY);
        runner.setProperty(jsonWriter, SchemaAccessUtils.SCHEMA_TEXT, outputSchemaText);
        runner.setProperty(jsonWriter, "Pretty Print JSON", "true");
        runner.setProperty(jsonWriter, "Schema Write Strategy", "full-schema-attribute");
        runner.enableControllerService(jsonWriter);

        runner.enqueue(Paths.get("src/test/resources/TestConvertRecord/input/person_long_id.json"));

        runner.setProperty(ConvertRecord.RECORD_READER, "reader");
        runner.setProperty(ConvertRecord.RECORD_WRITER, "writer");

        runner.run();
        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_FAILURE, 1);
    }

    @Test
    public void testEnumBadValue() throws InitializationException, IOException {
        final TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);
        final JsonTreeReader jsonReader = new JsonTreeReader();
        runner.addControllerService("reader", jsonReader);

        final String inputSchemaText = new String(Files.readAllBytes(Paths.get("src/test/resources/TestConvertRecord/schema/person.avsc")));
        final String outputSchemaText = new String(Files.readAllBytes(Paths.get("src/test/resources/TestConvertRecord/schema/person.avsc")));

        runner.setProperty(jsonReader, SchemaAccessUtils.SCHEMA_ACCESS_STRATEGY, SchemaAccessUtils.SCHEMA_TEXT_PROPERTY);
        runner.setProperty(jsonReader, SchemaAccessUtils.SCHEMA_TEXT, inputSchemaText);
        runner.enableControllerService(jsonReader);

        final AvroRecordSetWriter avroWriter = new AvroRecordSetWriter();
        runner.addControllerService("writer", avroWriter);
        runner.setProperty(avroWriter, SchemaAccessUtils.SCHEMA_ACCESS_STRATEGY, SchemaAccessUtils.SCHEMA_TEXT_PROPERTY);
        runner.setProperty(avroWriter, SchemaAccessUtils.SCHEMA_TEXT, outputSchemaText);
        runner.enableControllerService(avroWriter);

        runner.enqueue(Paths.get("src/test/resources/TestConvertRecord/input/person_bad_enum.json"));

        runner.setProperty(ConvertRecord.RECORD_READER, "reader");
        runner.setProperty(ConvertRecord.RECORD_WRITER, "writer");

        runner.run();

        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_FAILURE, 1);
    }

    @Test
    public void testEnumUnionString() throws InitializationException, IOException {
        final TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);
        final JsonTreeReader jsonReader = new JsonTreeReader();
        runner.addControllerService("reader", jsonReader);

        final String inputSchemaText = new String(Files.readAllBytes(Paths.get("src/test/resources/TestConvertRecord/schema/person_with_union_enum_string.avsc")));
        final String outputSchemaText = new String(Files.readAllBytes(Paths.get("src/test/resources/TestConvertRecord/schema/person_with_union_enum_string.avsc")));

        runner.setProperty(jsonReader, SchemaAccessUtils.SCHEMA_ACCESS_STRATEGY, SchemaAccessUtils.SCHEMA_TEXT_PROPERTY);
        runner.setProperty(jsonReader, SchemaAccessUtils.SCHEMA_TEXT, inputSchemaText);
        runner.enableControllerService(jsonReader);

        final AvroRecordSetWriter avroWriter = new AvroRecordSetWriter();
        runner.addControllerService("writer", avroWriter);
        runner.setProperty(avroWriter, SchemaAccessUtils.SCHEMA_ACCESS_STRATEGY, SchemaAccessUtils.SCHEMA_TEXT_PROPERTY);
        runner.setProperty(avroWriter, SchemaAccessUtils.SCHEMA_TEXT, outputSchemaText);
        runner.enableControllerService(avroWriter);

        runner.enqueue(Paths.get("src/test/resources/TestConvertRecord/input/person_bad_enum.json"));

        runner.setProperty(ConvertRecord.RECORD_READER, "reader");
        runner.setProperty(ConvertRecord.RECORD_WRITER, "writer");

        runner.run();

        runner.assertAllFlowFilesTransferred(ConvertRecord.REL_SUCCESS, 1);
    }

    @Test
    public void testDateConversionWithUTCMinusTimezone() throws Exception {
        final String timezone = System.getProperty("user.timezone");
        System.setProperty("user.timezone", "EST");
        try {
            TestRunner runner = TestRunners.newTestRunner(ConvertRecord.class);

            JsonTreeReader jsonTreeReader = new JsonTreeReader();
            runner.addControllerService("json-reader", jsonTreeReader);
            runner.setProperty(jsonTreeReader, DateTimeUtils.DATE_FORMAT, "yyyy-MM-dd");
            runner.enableControllerService(jsonTreeReader);

            AvroRecordSetWriter avroWriter = new AvroRecordSetWriter();
            runner.addControllerService("avro-writer", avroWriter);
            runner.enableControllerService(avroWriter);

            runner.setProperty(ConvertRecord.RECORD_READER, "json-reader");
            runner.setProperty(ConvertRecord.RECORD_WRITER, "avro-writer");

            runner.enqueue("{ \"date\": \"1970-01-02\" }");

            runner.run();

            runner.assertAllFlowFilesTransferred(ConvertRecord.REL_SUCCESS, 1);

            MockFlowFile flowFile = runner.getFlowFilesForRelationship(ConvertRecord.REL_SUCCESS).get(0);
            DataFileStream<GenericRecord> avroStream = new DataFileStream<>(flowFile.getContentStream(), new NonCachingDatumReader<>());

            assertTrue(avroStream.hasNext());
            assertEquals(1, avroStream.next().get("date")); // see https://avro.apache.org/docs/1.10.0/spec.html#Date
        } finally {
            System.setProperty("user.timezone", timezone);
        }
    }
}
