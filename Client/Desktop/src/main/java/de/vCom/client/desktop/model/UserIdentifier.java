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
package de.vCom.client.desktop.model;

public class UserIdentifier {
	
	/**
	 * Creates an {@code UserIdentifier} from the given string representation.
	 * @param representation The string representation of the identifier to create.
	 */
	public UserIdentifier(String representation) {
		// TODO
	}

	/**
	 * Gets the string representation of this identifier. The re4turned representation can be used
	 * for network transfers.
	 * @return The string representation of this identifier.
	 */
	public String getStringRepresentation() {
		return ""; // TODO
	}
}
