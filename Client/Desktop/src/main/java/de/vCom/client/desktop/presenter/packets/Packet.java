/**
 * vCom is a chat service consisting of client and server application that encrypts every message
 * send and thus allows a secure and for third parties not readable communication.
 * Copyright (C) 2016 Kai Brandenbusch, Björn Lange, Christian Langer, Daniel Theis
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If
 * not, see <http://www.gnu.org/licenses/>.
 */
package de.vCom.client.desktop.presenter.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import de.vCom.client.desktop.model.User;
import de.vCom.client.desktop.model.UserIdentifier;
import de.vCom.client.desktop.model.UserList;

/**
 * Abstract parent class for all client packets.
 * @author Björn Lange
 */
public abstract class Packet {
	/**
	 * Reads a list of {@code UserIsentifier}s from the given stream.
	 * @param input The stream to read from.
	 * @return The read list.
	 * @throws NullPointerException If {@code input} is {@code null}.
	 * @throws IOException If an I/O error occurs.
	 */
	protected List<UserIdentifier> readUserIdentifierList(DataInputStream input)
			throws IOException {
		if (input == null) {
			throw new NullPointerException();
		}
		
		List<UserIdentifier> list = new LinkedList<>();
		int length = input.readInt();
		for (int i = 0; i < length; i++) {
			list.add(readUserIdentifier(input));
		}
		
		return list;
	}
	
	/**
	 * Reads an {@code UserIdentifier} from the given stream.
	 * @param input The stream to read from.
	 * @return The read {@code UserIdentifier}.
	 * @throws IOException If an I/O error occurs.
	 * @throws NullPointerException If {@code input} is {@code null}.
	 */
	protected UserIdentifier readUserIdentifier(DataInputStream input) throws IOException {
		if (input == null) {
			throw new NullPointerException();
		}
		
		int idLength = input.readInt();
		byte[] idData = new byte[idLength];
		input.readFully(idData);
		String strIdentifier = new String(idData, StandardCharsets.UTF_8);
		return new UserIdentifier(strIdentifier);
	}
	
	/**
	 * Reads a {@code byte[]} from the given stream. It is expected that the stream contains an
	 * {@code int} that specifies the number of {@code byte}s to read.
	 * @param input The stream to read from.
	 * @return The read {@code byte[]}.
	 * @throws NullPointerException If {@code input} is {@code null}.
	 * @throws IOException If an I/O error occurs.
	 */
	protected byte[] readByteArray(DataInputStream input) throws IOException {
		int length = input.readInt();
		byte[] data = new byte[length];
		input.readFully(data);
		return data;
	}
	
	/**
	 * Writes a list of {@code UserIdentifier}s to the given stream. The source for the identifiers
	 * is a list of {@code User} objects. Every objects identifier is written.
	 * @param output The stream to write to.
	 * @param list The list to write.
	 * @throws NullPointerException If {@code output} or {@code list} is {@code null}.
	 * @throws IOException If an I/O error occurs.
	 */
	protected void writeUserIdentifierList(DataOutputStream output, UserList list)
			throws IOException {
		if (output == null || list == null) {
			throw new NullPointerException();
		}
		
		output.writeInt(list.size());
		for (User user : list) {
			writeUserIdentifier(output, user.getUserIdentifier());
		}
	}
	
	/**
	 * Writes an {@code UserIdentifier} to the given stream.
	 * @param output The stream to write to.
	 * @param identifier The identifier to write.
	 * @throws NullPointerException If {@code output} or {@code identifier} is {@code null}.
	 * @throws IOException If an I/O error occurs.
	 */
	protected void writeUserIdentifier(DataOutputStream output, UserIdentifier identifier) throws IOException {
		if (output == null || identifier == null) {
			throw new NullPointerException();
		}
		
		String strIdentifier = identifier.getStringRepresentation();
		byte[] idData = strIdentifier.getBytes(StandardCharsets.UTF_8);
		output.writeInt(idData.length);
		output.write(idData);
	}
	
	/**
	 * Writes a {@code byte[]} to the given stream in the following form: First an {@code int} is
	 * written that is the length of the array. Then the arrays content is written.
	 * @param output The streamt o write to.
	 * @param data The array to write.
	 * @throws IOException If an I/O error occurs.
	 * @throws NullPointerException If {@code output} or {code data} is {@code null}.
	 */
	protected void writeByteArray(DataOutputStream output, byte[] data) throws IOException {
		if (output == null || data == null) {
			throw new NullPointerException();
		}
		
		output.writeInt(data.length);
		output.write(data);
	}
	
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
