/*
 * Queen.cpp
 *
 *  Created on: May 31, 2012
 *      Author: andrei
 */

#include "Queen.h"

Queen::Queen(string c) :
	Piece("QUEEN", c) {

}

Queen::~Queen() {
}

void Queen::DiagnalAttackBlack(int x, int y, int row, int column, Board* board,
		string t) {
	for (int i = 0; i < 8; i++) {
		x++;
		y++;
		if (x > 7 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					x = row;
					y = column;
					break;
				} else {
					x = row;
					y = column;
					break;
				}

			} else {
				x = row;
				y = column;
				break;
			}

		}
	}
	for (int i = 0; i < 8; i++) { //up right
		x--;
		y++;
		if (x < 0 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					x = row;
					y = column;
					break;
				} else {
					x = row;
					y = column;
					break;
				}
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
	for (int i = 0; i < 8; i++) { //down left
		x++;
		y--;
		if (x > 7 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					x = row;
					y = column;
					break;
				} else {
					x = row;
					y = column;
					break;
				}
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
	for (int i = 0; i < 8; i++) { //up left
		x--;
		y--;
		if (x < 0 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					x = row;
					y = column;
					break;
				} else {
					x = row;
					y = column;
					break;
				}
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
}

void Queen::DiagnalAttacksWhite(int x, int y, int row, int column,
		Board* board, string t) {
	for (int i = 0; i < 8; i++) {
		x++;
		y++;
		if (x > 7 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					x = row;
					y = column;
					break;
				} else {
					x = row;
					y = column;
					break;
				}
			} else {
				x = row;
				y = column;
				break;
			}

		}
	}
	for (int i = 0; i < 8; i++) { //up right
		x--;
		y++;
		if (x < 0 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					x = row;
					y = column;
					break;
				} else {
					x = row;
					y = column;
					break;
				}
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
	for (int i = 0; i < 8; i++) { //down left
		x++;
		y--;
		if (x > 7 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					x = row;
					y = column;
					break;
				} else {
					x = row;
					y = column;
					break;
				}
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
	for (int i = 0; i < 8; i++) { //up left
		x--;
		y--;
		if (x < 0 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					x = row;
					y = column;
					break;
				} else {
					x = row;
					y = column;
					break;
				}
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
}

void Queen::LinearAttackBlack(int& x, Board*& board, int& y, string& t,
		int& row, int& column) {
	x++;
	while (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					break;

				} else {
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
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					break;

				} else {
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
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					break;

				} else {
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
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					break;

				} else {
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

void Queen::DiagnalSpecialBlack(int& x, int& y, int& row, int& column,
		Board*& board, string& t) {
	for (int i = 0; i < 8; i++) {
		x++;
		y++;
		if (x > 7 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));
				x = row;
				y = column;
				break;
			} else {
				x = row;
				y = column;
				break;
			}

		}
	}
	for (int i = 0; i < 8; i++) { //up right
		x--;
		y++;
		if (x < 0 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));
				x = row;
				y = column;
				break;
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
	for (int i = 0; i < 8; i++) { //down left
		x++;
		y--;
		if (x > 7 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));
				x = row;
				y = column;
				break;
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
	for (int i = 0; i < 8; i++) { //up left
		x--;
		y--;
		if (x < 0 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() != t) {
				specialAttack.insert(BP(x, y));
				x = row;
				y = column;
				break;
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
}

void Queen::LinearSpecialBlack(int& x, Board*& board, int& y, string& t,
		int& row, int& column) {
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

void Queen::DiagnalSpecialWhite(int& x, int& y, int& row, int& column,
		Board*& board, string& t) {
	for (int i = 0; i < 8; i++) {
		x++;
		y++;
		if (x > 7 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));
				x = row;
				y = column;
				break;
			} else {
				x = row;
				y = column;
				break;
			}

		}
	}
	for (int i = 0; i < 8; i++) { //up right
		x--;
		y++;
		if (x < 0 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));
				x = row;
				y = column;
				break;
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
	for (int i = 0; i < 8; i++) { //down left
		x++;
		y--;
		if (x > 7 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));
				x = row;
				y = column;
				break;
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
	for (int i = 0; i < 8; i++) { //up left
		x--;
		y--;
		if (x < 0 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				specialAttack.insert(BP(x, y));
				x = row;
				y = column;
				break;
			} else {
				x = row;
				y = column;
				break;
			}

		}

	}
}

void Queen::LinearSpecialWhite(int& x, Board*& board, int& y, string& t,
		int& row, int& column) {
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

std::set<BP> Queen::GetSpecial(int row, int column, Board* board) {
	specialAttack.clear();
	int x = row;
	int y = column;
	string t = "BLACK";

	if (c == t) {
		DiagnalSpecialBlack(x, y, row, column, board, t);
		LinearSpecialBlack(x, board, y, t, row, column);

	} else {
		DiagnalSpecialWhite(x, y, row, column, board, t);
		LinearSpecialWhite(x, board, y, t, row, column);

	}
	return specialAttack;
}

void Queen::LinearAttackWhite(int x, Board* board, int y, string t, int row,
		int column) {
	x++;
	while (x < 8) {
		if (board->pieces[x][y] != NULL) {
			if (board->pieces[x][y]->getColor() == t) {
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					break;

				} else {
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
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					break;

				} else {
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
				if (!putInCheck(row, column, x, y, board)) {
					if (!putInCheck(row, column, x, y, board)) {
						attackMoves.insert(BP(x, y));
						break;

					} else {
						break;
					}

				} else {
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
				if (!putInCheck(row, column, x, y, board)) {
					attackMoves.insert(BP(x, y));
					break;

				} else {
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

std::set<BP> Queen::AttackMoves(int row, int column, Board * board) {
	int x = row;
	int y = column;
	string t = "BLACK";
	attackMoves.clear();

	if (c == t) {
		DiagnalAttackBlack(x, y, row, column, board, t);
		LinearAttackBlack(x, board, y, t, row, column);

	} else {
		DiagnalAttacksWhite(x, y, row, column, board, t);
		LinearAttackWhite(x, board, y, t, row, column);

	}

	return attackMoves;
}

void Queen::Remove() {
}

bool Queen::isInCheck() {
}

void Queen::CalcValidMoves() {
}

void Queen::GetDiagnalMoves(int x, int y, int row, int column, Board* board) {
	for (int i = 0; i < 8; i++) {
		x++;
		y++;
		if (x > 7 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			} else {
				x = row;
				y = column;
				break;
			}
		} else { //if it's not null and there's something there
			x = row;
			y = column;
			break;
		}

	}
	for (int i = 0; i < 8; i++) { //up right
		x--;
		y++;
		if (x < 0 || y > 7) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			} else {
				x = row;
				y = column;
				break;
			}
		} else { //if it's not null and there's something there
			x = row;
			y = column;
			break;
		}

	}
	for (int i = 0; i < 8; i++) { //down left
		x++;
		y--;
		if (x > 7 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			} else {
				x = row;
				y = column;
				break;
			}
		} else { //if it's not null and there's something there
			x = row;
			y = column;
			break;
		}

	}
	for (int i = 0; i < 8; i++) { //up left
		x--;
		y--;
		if (x < 0 || y < 0) {
			x = row;
			y = column;
			break;
		}
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));

			} else {
				x = row;
				y = column;
				break;
			}
		} else { //if it's not null and there's something there
			x = row;
			y = column;
			break;
		}

	}
}

void Queen::GetLineMoves(int& x, Board*& board, int& y, int& row, int& column) {
	x++;
	while (x < 8) {
		if (board->pieces[x][y] == NULL) {
			if (!putInCheck(row, column, x, y, board)) {
				validMoves.insert(BP(x, y));
				x++;

			} else {
				x = row;
				y = column;
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
				x = row;
				y = column;
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
				x = row;
				y = column;
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
				x = row;
				y = column;
				break;
			}
		} else {
			break;
		}
	}
	y = column;
}

std::set<BP> Queen::GetValidMoves(int row, int column, Board* board) {
	validMoves.clear();
	int x = row;
	int y = column;
	if (c == "BLACK") {
		GetDiagnalMoves(x, y, row, column, board);
		GetLineMoves(x, board, y, row, column);

	} else {
		GetDiagnalMoves(x, y, row, column, board);
		GetLineMoves(x, board, y, row, column);

	}
	return validMoves;
}

ImageName Queen::GetTypeE() {
	if (c == "WHITE") {
		return W_QUEEN;
	} else {
		return B_QUEEN;
	}
}

