/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.process;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;

import java.util.Random;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class CollectorOutputProcessorTest extends BaseOutputProcessorTestCase {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testCollectFail() throws Exception {
		testFailToRead(new CollectorOutputProcessor());
	}

	@Test
	public void testCollectSuccess() throws Exception, ProcessException {
		CollectorOutputProcessor collectorOutputProcessor =
			new CollectorOutputProcessor();

		Random random = new Random();

		byte[] stdErrData = new byte[1024];

		random.nextBytes(stdErrData);

		Assert.assertArrayEquals(
			stdErrData,
			collectorOutputProcessor.processStdErr(
				new UnsyncByteArrayInputStream(stdErrData)));

		byte[] stdOutData = new byte[1024];

		random.nextBytes(stdOutData);

		Assert.assertArrayEquals(
			stdOutData,
			collectorOutputProcessor.processStdErr(
				new UnsyncByteArrayInputStream(stdOutData)));
		Assert.assertArrayEquals(
			stdErrData,
			invokeProcessStdErr(
				collectorOutputProcessor,
				new UnsyncByteArrayInputStream(stdErrData)));
		Assert.assertArrayEquals(
			stdOutData,
			invokeProcessStdOut(
				collectorOutputProcessor,
				new UnsyncByteArrayInputStream(stdOutData)));
	}

}