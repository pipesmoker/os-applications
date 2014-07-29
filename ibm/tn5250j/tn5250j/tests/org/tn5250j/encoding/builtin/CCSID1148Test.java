/**
 * $Id$
 *
 * Title: tn5250J
 * Copyright:   Copyright (c) 2001,2009
 * Company:
 * @author: master_jaf
 *
 * Description:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software; see the file COPYING.  If not, write to
 * the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307 USA
 *
 */
package org.tn5250j.encoding.builtin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.tn5250j.encoding.CharMappings;
import org.tn5250j.encoding.ICodePage;
import org.tn5250j.encoding.ToolboxCodePageProvider;

/**
 * Testing the correctness of {@link CCSID1148} and comparing with existing implementation.
 *
 * @author master_jaf
 */
public class CCSID1148Test {

	/**
	 * Correctness test for old implementation ....
	 * Testing byte -> Unicode -> byte
	 */
	@Ignore ("the new implementation seems to be correct, no need to fix this")
	@Test
	public void testOldConverter1148() {

		ICodePage cp = CharMappings.getCodePage("1148");
		assertNotNull("At least an ASCII Codepage should be available.", cp);

		for (int i=0; i<256; i++) {
			final byte beginvalue = (byte)i;
			final char converted = cp.ebcdic2uni(beginvalue);
			final byte afterall = cp.uni2ebcdic(converted);
			assertEquals("Testing item #" + i, beginvalue, afterall);
		}

	}

	/**
	 * Correctness test for new implementation ...
	 * Testing byte -> Unicode -> byte
	 */
	@Test
	public void testNewConverter1148() {
		CCSID1148 cp = new CCSID1148();
		cp.init();
		assertNotNull("At least an ASCII Codepage should be available.", cp);

		for (int i=0; i<256; i++) {
			final byte beginvalue = (byte)i;
			final char converted = cp.ebcdic2uni(beginvalue);
			final byte afterall = cp.uni2ebcdic(converted);
			assertEquals("Testing item #" + i, beginvalue, afterall);
		}
	}

	/**
	 * Testing for Correctness both implementations ...
	 * Testing byte -> Unicode -> byte
	 */
	@Ignore ("the new implementation seems to be correct, no need to fix this")
	@Test
	public void testBoth() {
		final ICodePage cp = CharMappings.getCodePage("1148");
		final CCSID1148 cpex = new CCSID1148();
		cpex.init();
		assertNotNull("At least an ASCII Codepage should be available.", cpex);

		for (int i=0; i<256; i++) {
			final byte beginvalue = (byte)i;
			assertEquals("Testing to EBCDIC item #" + i, cp.ebcdic2uni(beginvalue), cpex.ebcdic2uni(beginvalue));
			final char converted = cp.ebcdic2uni(beginvalue);
			assertEquals("Testing to UNICODE item #" + i, cp.uni2ebcdic(converted), cpex.uni2ebcdic(converted));
			final byte afterall = cp.uni2ebcdic(converted);
			assertEquals("Testing before and after item #" + i, beginvalue, afterall);
		}
	}

	/*
	 * ==============================================
	 */
	/**
	 * Testing for correctness new implementation and JTOpen ...
	 * Testing byte -> Unicode -> byte
	 */
	@Test
	public void testNewAndToolbox() {
		final ICodePage cp = ToolboxCodePageProvider.getCodePage("1148");
		final CCSID1148 cpex = new CCSID1148();
		cpex.init();
		assertNotNull("At least an ASCII Codepage should be available.", cpex);

		// According to
		// http://www-01.ibm.com/software/globalization/cp/cp01148.jsp
		// Characters less than 0x40 are not defined.
		
		for (int i=4*16; i<256; i++) {
			final byte beginvalue = (byte)i;
			assertEquals("Testing to EBCDIC item #" + i, cp.ebcdic2uni(beginvalue), cpex.ebcdic2uni(beginvalue));
			final char converted = cp.ebcdic2uni(beginvalue);
			assertEquals("Testing to UNICODE item #" + i, cp.uni2ebcdic(converted), cpex.uni2ebcdic(converted));
			final byte afterall = cp.uni2ebcdic(converted);
			assertEquals("Testing before and after item #" + i, beginvalue, afterall);
		}
	}

	/**
	 * Testing for correctness old implementation and JTOpen ...
	 * Testing byte -> Unicode -> byte
	 */
	@Test
	public void testOldAndToolbox() {
		final ICodePage cp = ToolboxCodePageProvider.getCodePage("1148");
		final ICodePage cpex = CharMappings.getCodePage("1148");
		assertNotNull("At least an ASCII Codepage should be available.", cpex);

		// According to
		// http://www-01.ibm.com/software/globalization/cp/cp01148.jsp
		// Characters less than 0x40 are not defined.
		
		for (int i=4*16; i<256; i++) {
			final byte beginvalue = (byte)i;
			assertEquals("Testing to EBCDIC item #" + i, cp.ebcdic2uni(beginvalue), cpex.ebcdic2uni(beginvalue));
			final char converted = cp.ebcdic2uni(beginvalue);
			assertEquals("Testing to UNICODE item #" + i, cp.uni2ebcdic(converted), cpex.uni2ebcdic(converted));
			final byte afterall = cp.uni2ebcdic(converted);
			assertEquals("Testing before and after item #" + i, beginvalue, afterall);
		}
	}
}
