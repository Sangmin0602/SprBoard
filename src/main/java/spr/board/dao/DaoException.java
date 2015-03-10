package spr.board.dao;

import java.sql.SQLException;

import spr.board.BoardException;

public class DaoException extends BoardException {

	public DaoException ( String message) {
		super(message);
	}

	public DaoException(String message, SQLException cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}
}
