/*
 * Knight.cpp
 *
 *  Created on: Jun 1, 2012
 *      Author: andrei
 */

#include "Knight.h"

Knight::Knight(std::string c) :
	Piece("KNIGHT", c) {
}

Knight::~Knight() {
	// TODO Auto-generated destructor stub
}

void Knight::GetAttackMovesBlack(int& row, int& column, Board*& board,
		string& t) {
	if (row + 1 < 8 && column + 2 < 8) {
		if (board->pieces[row + 1][column + 2] != NULL) {
			if (board->pieces[row + 1][column + 2]->getColor() != t) {
				if (!putInCheck(row, column, row + 1, column + 2, board)) {

					attackMoves.insert(BP(row + 1, column + 2));
				}

			}
		}
	}
	if (row + 1 < 8 && column - 2 > -1) {
		if (board->pieces[row + 1][column - 2] != NULL) {
			if (board->pieces[row + 1][column - 2]->getColor() != t) {
				if (!putInCheck(row, column, row + 1, column - 2, board)) {

					attackMoves.insert(BP(row + 1, column - 2));
				}

			}
		}
	}
	if (row + 2 < 8 && column + 1 < 8) {
		if (board->pieces[row + 2][column + 1] != NULL) {
			if (board->pieces[row + 2][column + 1]->getColor() != t) {
				if (!putInCheck(row, column, row + 2, column + 1, board)) {
					attackMoves.insert(BP(row + 2, column + 1));

				}

			}
		}
	}
	if (row + 2 < 8 && column - 1 > -1) {
		if (board->pieces[row + 2][column - 1] != NULL) {
			if (board->pieces[row + 2][column - 1]->getColor() != t) {
				if (!putInCheck(row, column, row + 2, column - 1, board)) {
					attackMoves.insert(BP(row + 2, column - 1));

				}

			}
		}
	}
	if (row - 2 > -1 && column - 1 > -1) {
		if (board->pieces[row - 2][column - 1] != NULL) {
			if (board->pieces[row - 2][column - 1]->getColor() != t) {
				if (!putInCheck(row, column, row - 2, column - 1, board)) {
					attackMoves.insert(BP(row - 2, column - 1));

				}

			}
		}
	}
	if (row - 2 > -1 && column + 1 < 8) {
		if (board->pieces[row - 2][column + 1] != NULL) {
			if (board->pieces[row - 2][column + 1]->getColor() != t) {
				if (!putInCheck(row, column, row - 2, column + 1, board)) {
					attackMoves.insert(BP(row - 2, column + 1));

				}

			}
		}
	}
	if (row - 1 > -1 && column + 2 < 8) {
		if (board->pieces[row - 1][column + 2] != NULL) {
			if (board->pieces[row - 1][column + 2]->getColor() != t) {
				if (!putInCheck(row, column, row - 1, column + 2, board)) {
					attackMoves.insert(BP(row - 1, column + 2));

				}

			}
		}
	}
	if (row - 1 > -1 && column - 2 > -1) {
		if (board->pieces[row - 1][column - 2] != NULL) {
			if (board->pieces[row - 1][column - 2]->getColor() != t) {
				if (!putInCheck(row, column, row - 1, column - 2, board)) {
					attackMoves.insert(BP(row - 1, column - 2));

				}

			}
		}
	}
}

void Knight::SpecialBlackMoves(int & row, int & column, Board *& board,
		string & t) {
	if (row + 1 < 8 && column + 2 < 8) {
		if (board->pieces[row + 1][column + 2] != NULL) {
			if (board->pieces[row + 1][column + 2]->getColor() != t) {
				specialAttack.insert(BP(row + 1, column + 2));

			}
		}
	}
	if (row + 1 < 8 && column - 2 > -1) {
		if (board->pieces[row + 1][column - 2] != NULL) {
			if (board->pieces[row + 1][column - 2]->getColor() != t) {
				specialAttack.insert(BP(row + 1, column - 2));

			}
		}
	}
	if (row + 2 < 8 && column + 1 < 8) {
		if (board->pieces[row + 2][column + 1] != NULL) {
			if (board->pieces[row + 2][column + 1]->getColor() != t) {
				specialAttack.insert(BP(row + 2, column + 1));

			}
		}
	}
	if (row + 2 < 8 && column - 1 > -1) {
		if (board->pieces[row + 2][column - 1] != NULL) {
			if (board->pieces[row + 2][column - 1]->getColor() != t) {
				specialAttack.insert(BP(row + 2, column - 1));

			}
		}
	}
	if (row - 2 > -1 && column - 1 > -1) {
		if (board->pieces[row - 2][column - 1] != NULL) {
			if (board->pieces[row - 2][column - 1]->getColor() != t) {
				specialAttack.insert(BP(row - 2, column - 1));

			}
		}
	}
	if (row - 2 > -1 && column + 1 < 8) {
		if (board->pieces[row - 2][column + 1] != NULL) {
			if (board->pieces[row - 2][column + 1]->getColor() != t) {
				specialAttack.insert(BP(row - 2, column + 1));

			}
		}
	}
	if (row - 1 > -1 && column + 2 < 8) {
		if (board->pieces[row - 1][column + 2] != NULL) {
			if (board->pieces[row - 1][column + 2]->getColor() != t) {
				specialAttack.insert(BP(row - 1, column + 2));

			}
		}
	}
	if (row - 1 > -1 && column - 2 > -1) {
		if (board->pieces[row - 1][column - 2] != NULL) {
			if (board->pieces[row - 1][column - 2]->getColor() != t) {

				specialAttack.insert(BP(row - 1, column - 2));
			}
		}
	}
}

void Knight::SpecialMovesWhite(int row, int column, Board *board, string t) {
	if (row + 1 < 8 && column + 2 < 8) {
		if (board->pieces[row + 1][column + 2] != NULL) {
			if (board->pieces[row + 1][column + 2]->getColor() == t) {
				specialAttack.insert(BP(row + 1, column + 2));

			}
		}
	}
	if (row + 1 < 8 && column - 2 > -1) {
		if (board->pieces[row + 1][column - 2] != NULL) {
			if (board->pieces[row + 1][column - 2]->getColor() == t) {
				specialAttack.insert(BP(row + 1, column - 2));

			}
		}
	}
	if (row + 2 < 8 && column + 1 < 8) {
		if (board->pieces[row + 2][column + 1] != NULL) {
			if (board->pieces[row + 2][column + 1]->getColor() == t) {
				specialAttack.insert(BP(row + 2, column + 1));

			}
		}
	}
	if (row + 2 < 8 && column - 1 > -1) {
		if (board->pieces[row + 2][column - 1] != NULL) {
			if (board->pieces[row + 2][column - 1]->getColor() == t) {
				specialAttack.insert(BP(row + 2, column - 1));

			}
		}
	}
	if (row - 2 > -1 && column - 1 > -1) {
		if (board->pieces[row - 2][column - 1] != NULL) {
			if (board->pieces[row - 2][column - 1]->getColor() == t) {
				specialAttack.insert(BP(row - 2, column - 1));

			}
		}
	}
	if (row - 2 > -1 && column + 1 < 8) {
		if (board->pieces[row - 2][column + 1] != NULL) {
			if (board->pieces[row - 2][column + 1]->getColor() == t) {
				specialAttack.insert(BP(row - 2, column + 1));

			}
		}
	}
	if (row - 1 > -1 && column + 2 < 8) {
		if (board->pieces[row - 1][column + 2] != NULL) {
			if (board->pieces[row - 1][column + 2]->getColor() == t) {
				specialAttack.insert(BP(row - 1, column + 2));

			}
		}
	}
	if (row - 1 > -1 && column - 2 > -1) {
		if (board->pieces[row - 1][column - 2] != NULL) {
			if (board->pieces[row - 1][column - 2]->getColor() == t) {

				specialAttack.insert(BP(row - 1, column - 2));
			}
		}
	}
}

std::set<BP> Knight::GetSpecial(int row, int column, Board* board) {
	specialAttack.clear();

	string t = "BLACK";
	if (c == t) {
		SpecialBlackMoves(row, column, board, t);
	} else {
		SpecialMovesWhite(row, column, board, t);
	}

	return specialAttack;
}

void Knight::GetAttackMovesWhite(int row, int column, Board* board, string t) {
	if (row + 1 < 8 && column + 2 < 8) {
		if (board->pieces[row + 1][column + 2] != NULL) {
			if (board->pieces[row + 1][column + 2]->getColor() == t) {
				if (!putInCheck(row, column, row + 1, column + 2, board)) {

					attackMoves.insert(BP(row + 1, column + 2));
				}

			}
		}
	}
	if (row + 1 < 8 && column - 2 > -1) {
		if (board->pieces[row + 1][column - 2] != NULL) {
			if (board->pieces[row + 1][column - 2]->getColor() == t) {
				if (!putInCheck(row, column, row + 1, column - 2, board)) {

					attackMoves.insert(BP(row + 1, column - 2));
				}

			}
		}
	}
	if (row + 2 < 8 && column + 1 < 8) {
		if (board->pieces[row + 2][column + 1] != NULL) {
			if (board->pieces[row + 2][column + 1]->getColor() == t) {
				if (!putInCheck(row, column, row + 2, column + 1, board)) {

					attackMoves.insert(BP(row + 2, column + 1));
				}

			}
		}
	}
	if (row + 2 < 8 && column - 1 > -1) {
		if (board->pieces[row + 2][column - 1] != NULL) {
			if (board->pieces[row + 2][column - 1]->getColor() == t) {
				if (!putInCheck(row, column, row + 2, column + 1, board)) {

					attackMoves.insert(BP(row + 2, column + 1));
				}

			}
		}
	}
	if (row - 2 > -1 && column - 1 > -1) {
		if (board->pieces[row - 2][column - 1] != NULL) {
			if (board->pieces[row - 2][column - 1]->getColor() == t) {
				if (!putInCheck(row, column, row - 2, column - 1, board)) {
					attackMoves.insert(BP(row - 2, column - 1));

				}

			}
		}
	}
	if (row - 2 > -1 && column + 1 < 8) {
		if (board->pieces[row - 2][column + 1] != NULL) {
			if (board->pieces[row - 2][column + 1]->getColor() == t) {
				if (!putInCheck(row, column, row - 2, column + 1, board)) {
					attackMoves.insert(BP(row - 2, column + 1));

				}

			}
		}
	}
	if (row - 1 > -1 && column + 2 < 8) {
		if (board->pieces[row - 1][column + 2] != NULL) {
			if (board->pieces[row - 1][column + 2]->getColor() == t) {
				if (!putInCheck(row, column, row - 1, column + 2, board)) {
					attackMoves.insert(BP(row - 1, column + 2));

				}

			}
		}
	}
	if (row - 1 > -1 && column - 2 > -1) {
		if (board->pieces[row - 1][column - 2] != NULL) {
			if (board->pieces[row - 1][column - 2]->getColor() == t) {
				if (!putInCheck(row, column, row - 1, column - 2, board)) {

					attackMoves.insert(BP(row - 1, column - 2));
				}
			}
		}
	}
}

std::set<BP> Knight::AttackMoves(int row, int column, Board * board) {
	string t = "BLACK";
	attackMoves.clear();
	if (c == t) {
		GetAttackMovesBlack(row, column, board, t);

	} else {
		GetAttackMovesWhite(row, column, board, t);

	}
	return attackMoves;

}

void Knight::Remove() {
}

bool Knight::isInCheck() {
}

void Knight::CalcValidMoves() {
}

void Knight::GetMoves(int row, int column, Board* board) {
	//if it's a black knight
	if (row + 1 < 8 && column + 2 < 8) {
		if (board->pieces[row + 1][column + 2] == NULL) {
			if (!putInCheck(row, column, row + 1, column + 2, board)) {
				validMoves.insert(BP(row + 1, column + 2));

			}
		}
	}
	if (row + 1 < 8 && column - 2 > -1) {
		if (board->pieces[row + 1][column - 2] == NULL) {
			if (!putInCheck(row, column, row + 1, column - 2, board)) {
				validMoves.insert(BP(row + 1, column - 2));

			}
		}
	}
	if (row + 2 < 8 && column + 1 < 8) {
		if (board->pieces[row + 2][column + 1] == NULL) {
			if (!putInCheck(row, column, row + 2, column + 1, board)) {
				validMoves.insert(BP(row + 2, column + 1));

			}
		}
	}
	if (row + 2 < 8 && column - 1 > -1) {
		if (board->pieces[row + 2][column - 1] == NULL) {
			if (!putInCheck(row, column, row + 2, column - 1, board)) {
				validMoves.insert(BP(row + 2, column - 1));

			}
		}
	}
	if (row - 2 > -1 && column - 1 > -1) {
		if (board->pieces[row - 2][column - 1] == NULL) {
			if (!putInCheck(row, column, row - 2, column - 1, board)) {
				validMoves.insert(BP(row - 2, column - 1));

			}
		}
	}
	if (row - 2 > -1 && column + 1 < 8) {
		if (board->pieces[row - 2][column + 1] == NULL) {
			if (!putInCheck(row, column, row - 2, column + 1, board)) {

				validMoves.insert(BP(row - 2, column + 1));
			}
		}
	}
	if (row - 1 > -1 && column + 2 < 8) {
		if (board->pieces[row - 1][column + 2] == NULL) {
			if (!putInCheck(row, column, row - 1, column + 2, board)) {
				validMoves.insert(BP(row - 1, column + 2));

			}
		}
	}
	if (row - 1 > -1 && column - 2 > -1) {
		if (board->pieces[row - 1][column - 2] == NULL) {
			if (!putInCheck(row, column, row - 1, column - 2, board)) {
				validMoves.insert(BP(row - 1, column - 2));

			}
		}
	}
}

std::set<BP> Knight::GetValidMoves(int row, int column, Board* board) {
	validMoves.clear();
	if (c == "BLACK") { //if it's a black knight
		GetMoves(row, column, board);
	} else {
		GetMoves(row, column, board);
	}
	return validMoves;
}

ImageName Knight::GetTypeE() {
	if (c == "WHITE") {
		return W_KNIGHT;
	} else {
		return B_KNIGHT;
	}

}

