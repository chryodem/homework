/*
 * King.cpp
 *
 *  Created on: Jun 1, 2012
 *      Author: andrei
 */

#include "King.h"

King::King(std::string c) :
	Piece("KING", c) {
}

King::~King() {
	// TODO Auto-generated destructor stub
}

void King::GetBlackMoves(int x, int y, Board* board, string t, int row,
		int column) {
	x++;
	y++;
	if (x < 8 && y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}

	}
	x = row;
	y = column;
	y++;
	if (y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	y = column;
	y--;
	if (y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	y = column;
	y--;
	x++;
	if (y > -1 && x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	y = column;
	x--;
	y--;
	if (x > -1 && y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	y = column;
	x++;
	if (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	x--;
	if (x > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	x--;
	y++;
	if (x > -1 && y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	y = column;
}

void King::BlackMovesSpecial(int & x, int & y, Board *& board, string & t,
		int & row, int & column) {
	x++;
	y++;
	if (x < 8 && y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));

			}
		}

	}
	x = row;
	y = column;
	y++;
	if (y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
	y--;
	if (y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	y = column;
	y--;
	x++;
	if (y > -1 && x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
	x--;
	y--;
	if (x > -1 && y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
	x++;
	if (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	x--;
	if (x > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	x--;
	y++;
	if (x > -1 && y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
}

void King::WhiteMovesSpecial(int x, int y, Board *board, string t, int row,
		int column) {
	x++;
	y++;
	if (x < 8 && y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));

			}
		}

	}
	x = row;
	y = column;
	y++;
	if (y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
	y--;
	if (y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	y = column;
	y--;
	x++;
	if (y > -1 && x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
	x--;
	y--;
	if (x > -1 && y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
	x++;
	if (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	x--;
	if (x > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	x--;
	y++;
	if (x > -1 && y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
}

std::set<BP> King::GetSpecial(int row, int column, Board* board) {
	specialAttack.clear();

	int x = row;
	int y = column;
	string t = "BLACK";
	if (t == c) {
		BlackMovesSpecial(x, y, board, t, row, column);

	} else {
		WhiteMovesSpecial(x, y, board, t, row, column);

	}

	return specialAttack;
}

void King::GetWhiteMoves(int x, int y, Board* board, string t, int row,
		int column) {
	x++;
	y++;
	if (x < 8 && y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}

	}
	x = row;
	y = column;
	y++;
	if (y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	y = column;
	y--;
	if (y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	y = column;
	y--;
	x++;
	if (y > -1 && x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	y = column;
	x--;
	y--;
	if (x > -1 && y > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	y = column;
	x++;
	if (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	x--;
	if (x > -1) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	x--;
	y++;
	if (x > -1 && y < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));

				}

			}
		}
	}
	x = row;
	y = column;
}

std::set<BP> King::AttackMoves(int row, int column, Board * board) {
	int x = row;
	int y = column;
	string t = "BLACK";
	attackMoves.clear();
	if (t == c) {
		GetBlackMoves(x, y, board, t, row, column);
	} else {
		GetWhiteMoves(x, y, board, t, row, column);
	}

	return attackMoves;
}

void King::Remove() {
}

bool King::isInCheck() {
}

void King::CalcValidMoves() {
}

void King::GetMoves(int x, int y, Board* board, int row, int column) {
	x++;
	y++;
	if (x < 8 && y < 8) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			}
		}

	}
	x = row;
	y = column;
	y++;
	if (y < 8) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
	y--;
	if (y > -1) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			}
		}
	}
	y = column;
	y--;
	x++;
	if (y > -1 && x < 8) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
	x--;
	y--;
	if (x > -1 && y > -1) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
	x++;
	if (x < 8) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			}
		}
	}
	x = row;
	x--;
	if (x > -1) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			}
		}
	}
	x = row;
	x--;
	y++;
	if (x > -1 && y < 8) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			}
		}
	}
	x = row;
	y = column;
}

std::set<BP> King::GetValidMoves(int row, int column, Board* board) {
	int x = row;
	int y = column;
	validMoves.clear();
	if (c == "BLACK") {
		GetMoves(x, y, board, row, column);

	} else {
		GetMoves(x, y, board, row, column);
	}

	return validMoves;
}

ImageName King::GetTypeE() {
	if (c == "WHITE") {
		return W_KING;
	} else {
		return B_KING;
	}
}

