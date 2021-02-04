/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The contents of this file are subject to the terms of either the Universal Permissive License
 * v 1.0 as shown at http://oss.oracle.com/licenses/upl
 *
 * or the following license:
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.openjdk.jmc.flightrecorder.internal.parser.v0;

import org.openjdk.jmc.flightrecorder.internal.InvalidJfrFileException;
import org.openjdk.jmc.flightrecorder.internal.parser.v0.model.DataType;
import org.openjdk.jmc.flightrecorder.internal.util.DataInputToolkit;
import org.openjdk.jmc.flightrecorder.parser.ByteBufferWrapper;

class NumberReaders {

	static long readLong(ByteBufferWrapper bytes, Offset offset) throws InvalidJfrFileException {
		return DataInputToolkit.readLong(bytes, offset.getAndIncrease(DataInputToolkit.LONG_SIZE));
	}

	static int readInt(ByteBufferWrapper bytes, Offset offset) throws InvalidJfrFileException {
		return DataInputToolkit.readInt(bytes, offset.getAndIncrease(DataInputToolkit.INTEGER_SIZE));
	}

	static byte readByte(ByteBufferWrapper bytes, Offset offset) throws InvalidJfrFileException {
		return DataInputToolkit.readByte(bytes, offset.getAndIncrease(DataInputToolkit.BYTE_SIZE));
	}

	static short readShort(ByteBufferWrapper bytes, Offset offset) throws InvalidJfrFileException {
		return DataInputToolkit.readShort(bytes, offset.getAndIncrease(DataInputToolkit.SHORT_SIZE));
	}

	static float readFloat(ByteBufferWrapper bytes, Offset offset) throws InvalidJfrFileException {
		return DataInputToolkit.readFloat(bytes, offset.getAndIncrease(DataInputToolkit.FLOAT_SIZE));
	}

	static double readDouble(ByteBufferWrapper bytes, Offset offset) throws InvalidJfrFileException {
		return DataInputToolkit.readDouble(bytes, offset.getAndIncrease(DataInputToolkit.DOUBLE_SIZE));
	}

	static long readKey(ByteBufferWrapper data, Offset offset, DataType keyType) throws InvalidJfrFileException {
		switch (keyType) {
		case U1:
		case BYTE:
			return readByte(data, offset);
		case SHORT:
		case U2:
			return readShort(data, offset);
		case INTEGER:
		case U4:
		case FLOAT:
			return readInt(data, offset);
		case LONG:
		case U8:
		case DOUBLE:
			return readLong(data, offset);
		default:
			throw new InvalidJfrFileException();
		}
	}

}
