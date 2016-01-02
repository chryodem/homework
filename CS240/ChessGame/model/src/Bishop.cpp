/*
 * Bishop.cpp
 *
 *  Created on: Jun 1, 2012
 *      Author: andrei
 */

#include "Bishop.h"

Bishop::Bishop(std::string c) :
	Piece("BISHOP", c) {
}

Bishop::~Bishop() {
	// TODO Auto-generated destructor stub
}

void Bishop::AttackMovesBlack(int x, int y, int row, int column, Board* board,
		string t) {
	//going down right
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

void Bishop::SpecialAttackBlack(int & x, int & y, int & row, int & column,
		Board *& board, string & t) {
	//going down right
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

std::set<BP> Bishop::GetSpecial(int row, int column, Board* board) {
	specialAttack.clear();

	int x = row;
	int y = column;
	string t = "BLACK";

	if (c == t) {
		SpecialAttackBlack(x, y, row, column, board, t);
	} else {
		SpecialAttackWhite(x, y, row, column, board, t);

	}

	return specialAttack;
}

void Bishop::AttackMovesWhite(int x, int y, int row, int column, Board* board,
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

void Bishop::SpecialAttackWhite(int x, int y, int row, int column,
		Board *board, string t) {
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

std::set<BP> Bishop::AttackMoves(int row, int column, Board * board) {
	int x = row;
	int y = column;
	string t = "BLACK";
	attackMoves.clear();

	if (c == t) {
		//going down right
		AttackMovesBlack(x, y, row, column, board, t);

	} else {
		AttackMovesWhite(x, y, row, column, board, t);
	}
	return attackMoves;
}

void Bishop::Remove() {
}

bool Bishop::isInCheck() {
}

void Bishop::CalcValidMoves() {
}

void Bishop::GetMoves(int x, int y, Board* board, int row, int column) {
	//going down right
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
				break;//can't make move in this direction then break
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
				break;//can't make move in this direction then break
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
				break;//can't make move in this direction then break
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
				break;//can't make move in this direction then break
			}
		} else { //if it's not null and there's something there
			x = row;
			y = column;
			break;
		}

	}
}

std::set<BP> Bishop::GetValidMoves(int row, int column, Board* board) {

	int x = row;
	int y = column;
	validMoves.clear();
	if (c == "BLACK") {
		//going down right
		GetMoves(x, y, board, row, column);

	} else {
		GetMoves(x, y, board, row, column);
	}
	return validMoves;
}

ImageName Bishop::GetTypeE() {
	if (c == "WHITE") {
		return W_BISHOP;
	} else {
		return B_BISHOP;
	}
}

