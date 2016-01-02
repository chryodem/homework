/*
 * Rook.cpp
 *
 *  Created on: May 31, 2012
 *      Author: andrei
 */

#include "Rook.h"

Rook::Rook(string c) :
		Piece("ROOK", c) {

}

Rook::~Rook() {
	return;
}

void Rook::BlackAttackMoves(int x, Board* board, int y, string t, int row,
		int column) {
	x++;
	while (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y,board)) {
					attackMoves.insert(BP(x, y));
					break;

				}
			} else {
				break;
			}
		}
		x++;
	}
	x = row;
	x--;
	while (x > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y,board)) {
					attackMoves.insert(BP(x, y));
					break;

				}
			} else {
				break;
			}
		}
		x--;
	}
	x = row;
	y++;
	while (y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y,board)) {
					attackMoves.insert(BP(x, y));
					break;

				}
			} else {
				break;
			}
		}
		y++;
	}
	y = column;
	y--;
	while (y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y,board)) {
					attackMoves.insert(BP(x, y));
					break;

				}
			} else {
				break;
			}
		}
		y--;
	}
	y = column;
}

void Rook::BlackSpecial(int x, Board* board, int y, string t, int row,
		int column) {
	x++;
	while (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));
				break;
			} else {
				break;
			}
		}
		x++;
	}
	x = row;
	x--;
	while (x > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));
				break;
			} else {
				break;
			}
		}
		x--;
	}
	x = row;
	y++;
	while (y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));
				break;
			} else {
				break;
			}
		}
		y++;
	}
	y = column;
	y--;
	while (y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));
				break;
			} else {
				break;
			}
		}
		y--;
	}
	y = column;
}

void Rook::WhiteSpecial(int x, Board* board, int y, string t, int row,
		int column) {
	x++;
	while (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));
				break;
			} else {
				break;
			}
		}
		x++;
	}
	x = row;
	x--;
	while (x > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));
				break;
			} else {
				break;
			}
		}
		x--;
	}
	x = row;
	y++;
	while (y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));
				break;
			} else {
				break;
			}
		}
		y++;
	}
	y = column;
	y--;
	while (y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));
				break;
			} else {
				break;
			}
		}
		y--;
	}
	y = column;
}

std::set<BP> Rook::GetSpecial(int row, int column, Board* board) {
	specialAttack.clear();
	int x = row;
	int y = column;
	string t = "BLACK";
	if (c == t) {
		BlackSpecial(x, board, y, t, row, column);

	} else {
		WhiteSpecial(x, board, y, t, row, column);
	}

	return specialAttack;
}

void Rook::WhiteAttackMoves(int x, Board* board, int y, string t, int row,
		int column) {
	x++;
	while (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y,board)) {
					attackMoves.insert(BP(x, y));
					break;

				}
			} else {
				break;
			}
		}
		x++;
	}
	x = row;
	x--;
	while (x > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y,board)) {
					attackMoves.insert(BP(x, y));
					break;

				}
			} else {
				break;
			}
		}
		x--;
	}
	x = row;
	y++;
	while (y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y,board)) {
					attackMoves.insert(BP(x, y));
					break;

				}
			} else {
				break;
			}
		}
		y++;
	}
	y = column;
	y--;
	while (y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y,board)) {
					attackMoves.insert(BP(x, y));
					break;

				}
			} else {
				break;
			}
		}
		y--;
	}
	y = column;
}

std::set<BP> Rook::AttackMoves(int row, int column, Board * board) {
	int x = row;
	int y = column;
	string t = "BLACK";
	attackMoves.clear();
	if (c == t) {
		BlackAttackMoves(x, board, y, t, row, column);
	} else {
		WhiteAttackMoves(x, board, y, t, row, column);
	}
	return attackMoves;
}

void Rook::Remove() {
}

bool Rook::isInCheck() {
}

void Rook::CalcValidMoves() {
}

void Rook::GetMoves(int x, Board* board, int y, int row, int column) {
	x++;
	while (x < 8) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));
				x++;
			} else {
				break;
			}
		} else {
			break;
		}
	}
	x = row;
	x--;
	while (x > -1) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));
				x--;

			} else {
				break;
			}
		} else {
			break;
		}
	}
	x = row;
	y++;
	while (y < 8) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));
				y++;

			} else {
				break;
			}
		} else {
			break;
		}
	}
	y = column;
	y--;
	while (y > -1) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));
				y--;

			} else {
				break;
			}
		} else {
			break;
		}
	}
	y = column;
}

std::set<BP> Rook::GetValidMoves(int row, int column, Board* board) {
	validMoves.clear();
	int x = row;
	int y = column;

	if (c == "BLACK") {
		GetMoves(x, board, y, row, column);

	} else {
		GetMoves(x, board, y, row, column);
	}

	return validMoves;

}

ImageName Rook::GetTypeE() {
	if (c == "WHITE") {
		return W_ROOK;
	} else {
		return B_ROOK;
	}
}

