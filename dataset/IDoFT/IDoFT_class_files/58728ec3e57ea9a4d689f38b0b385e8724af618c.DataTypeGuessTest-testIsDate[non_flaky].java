/*
 * Copyright (c) 2009, GoodData Corporation. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided
 * that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice, this list of conditions and
 *        the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *        and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *     * Neither the name of the GoodData Corporation nor the names of its contributors may be used to endorse
 *        or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.gooddata.csv;

import com.gooddata.modeling.model.SourceColumn;
import com.gooddata.util.CSVReader;
import com.gooddata.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * GoodData
 *
 * @author zd <zd@gooddata.com>
 * @version 1.0
 */
public class DataTypeGuessTest extends TestCase {

    public void testIsInteger() {
        assertTrue(DataTypeGuess.isInteger("12"));
        assertFalse(DataTypeGuess.isInteger("12.0"));
        assertFalse(DataTypeGuess.isInteger("AAA"));
        assertFalse(DataTypeGuess.isInteger("1E3"));
    }

    public void testIsDecimal() {
        assertTrue(DataTypeGuess.isDecimal("1E3"));
        assertTrue(DataTypeGuess.isDecimal("12.3"));
        assertTrue(DataTypeGuess.isDecimal("12"));
    }

    public void testIsDate() {
        DataTypeGuess guesser = new DataTypeGuess(true);
        assertEquals("yyyy-MM-dd", DataTypeGuess.getDateFormat("2010-11-12"));
        assertNull(DataTypeGuess.getDateFormat("2010-13-12"));
        assertEquals("MM/dd/yyyy", DataTypeGuess.getDateFormat("11/12/2010"));
        assertNull(DataTypeGuess.getDateFormat("13/12/2010"));
    }

    public void testGuessCsvSchema() throws IOException {
        DataTypeGuess guesser = new DataTypeGuess(true);
        {
            CSVReader csvr = FileUtil.getResourceAsCsvReader("/com/gooddata/csv/quotes.csv");
            SourceColumn[] types = guesser.guessCsvSchema(csvr);
            final String[] expected = new String[]{"FACT", "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE",
                    "ATTRIBUTE", "ATTRIBUTE", "DATE", "FACT", "FACT", "FACT", "FACT", "FACT", "FACT"};
            assertEquals(expected.length, types.length);
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], types[i].getLdmType());
                if ("DATE".equals(types[i].getLdmType())) {
                    assertEquals("yyyy-MM-dd", types[i].getFormat());
                }
            }
        }
        {
            CSVReader csvr = FileUtil.getResourceAsCsvReader("/com/gooddata/csv/department.csv");
            SourceColumn[] types = guesser.guessCsvSchema(csvr);
            final String[] expected = new String[]{"ATTRIBUTE", "ATTRIBUTE"};
            assertEquals(expected.length, types.length);
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], types[i].getLdmType());
            }
        }
        {
            CSVReader csvr = new CSVReader(new InputStreamReader(getClass().getResource("/com/gooddata/csv/employee.csv").openStream()));
            SourceColumn[] types = guesser.guessCsvSchema(csvr);
            final String[] expected = new String[]{"ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE"};
            assertEquals(expected.length, types.length);
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], types[i].getLdmType());
            }
        }
        {
            CSVReader csvr = new CSVReader(new InputStreamReader(getClass().getResource("/com/gooddata/csv/salary.csv").openStream()));
            SourceColumn[] types = guesser.guessCsvSchema(csvr);
            final String[] expected = new String[]{"ATTRIBUTE", "ATTRIBUTE", "FACT", "DATE"};
            assertEquals(expected.length, types.length);
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], types[i].getLdmType());
                if ("DATE".equals(types[i].getLdmType())) {
                    assertEquals("yyyy-MM-dd", types[i].getFormat());
                }
            }
        }
    }

    public void testGuessCsvSchemaWithDefaultLdmType() throws IOException {
        DataTypeGuess guesser = new DataTypeGuess(true);
        guesser.setDefaultLdmType("ATTRIBUTE");
        {
            CSVReader csvr = FileUtil.getResourceAsCsvReader("/com/gooddata/csv/quotes.csv");
            SourceColumn[] types = guesser.guessCsvSchema(csvr);
            final String[] expected = new String[]{"ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE",
                    "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE", "ATTRIBUTE"};
            assertEquals(expected.length, types.length);
            for (int i = 0; i < expected.length; i++) {
                assertEquals("ATTRIBUTE", types[i].getLdmType());
            }
        }
    }

}
