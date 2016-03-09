/**
 * vCom is a chat service consisting of client and server application that encrypts every message send and thus allows a secure and for third parties not readable communication.
 * Copyright (C) 2016 Kai Brandenbusch, Björn Lange, Christian Langer, Daniel Theis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.vCom.client.desktop.presenter.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Abstract parent class for all client packets.
 * @author Björn Lange
 */
public abstract class Packet {
	/**
	 * Reads the packet body from the given input stream. The header int has already been read.
	 * @param input The input stream to read from.
	 * @throws IOException If an IO exception occurs.
	 */
	public abstract void readFrom(DataInputStream input) throws IOException;
	
	/**
	 * Writes the whole packet to the given output stream.
	 * @param output Output stream to write to.
	 * @throws IOException If an IO exception occurs.
	 */
	public abstract void writeTo(DataOutputStream output) throws IOException;
}
