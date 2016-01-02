/*
 * Pawn.cpp
 *
 *  Created on: Jun 1, 2012
 *      Author: andrei
 */

#include "Pawn.h"

Pawn::Pawn(std::string c) :
		Piece("PAWN", c) {

}

Pawn::~Pawn() {
}

std::set<BP> Pawn::AttackMoves(int row, int column, Board * board) {
	string t = "BLACK";
	attackMoves.clear();
	//find moves for a black Pawn

	if (board->GetPiece(row, column)->getColor() == t) {

		int x1 = row + 1;
		int x2 = row + 1;
		int y1 = column + 1;
		int y2 = column - 1;
		if (x1 < 8 && y1 < 8 && x1 > -1 && y1 > -1) {
			if (board->pieces[x1][y1] != NULL) {
				if (board->pieces[x1][y1]->getColor() != t) {
					if (!putInCheck(row, column, x1, y1, board)) {
						attackMoves.insert(BP(x1, y1));

					}
				}

			}

		}
		if (x2 < 8 && y2 < 8 && x2 > -1 && y2 > -1) {
			if (board->pieces[x2][y2] != NULL) {
				if (board->pieces[x2][y2]->getColor() != t) {
					if (!putInCheck(row, column, x2, y2, board)) {
						attackMoves.insert(BP(x2, y2));

					}
				}

			}

		}

		return attackMoves;
		//find moves for the white Pawn
	} else {

		int x1 = row - 1;
		int x2 = row - 1;
		int y1 = column + 1;
		int y2 = column - 1;

		if (x1 < 8 && y1 < 8 && x1 > -1 && y1 > -1) {
			if (board->pieces[x1][y1] != NULL) {
				if (board->pieces[x1][y1]->getColor() == t) {
					if (!putInCheck(row, column, x1, y1, board)) {
						attackMoves.insert(BP(x1, y1));

					}

				}

			}

		}
		if (x2 < 8 && y2 < 8 && x2 > -1 && y2 > -1) {
			if (board->pieces[x2][y2] != NULL) {
				if (board->pieces[x2][y2]->getColor() == t) {
					if (!putInCheck(row, column, x2, y2, board)) {
						attackMoves.insert(BP(x2, y2));

					}

				}

			}

		}

		return attackMoves;

	}

}

void Pawn::Remove() {
}

bool Pawn::isInCheck() {
}

void Pawn::CalcValidMoves() {
}

std::set<BP> Pawn::GetValidMoves(int row, int column, Board * board) {
	//if this is a black piece, check if it's the pawns first move
	validMoves.clear();
	Piece * temp = board->GetPiece(row, column);
	if (temp->getColor() == "BLACK") {
		int temp = row;
		if (row == 1) { //it's pawns first move
			//std::cout<<"first move + black"<<" "<<row<<" "<<column<<std::endl;
			for (int i = temp + 1; i < temp + 3; i++) {
				if (i < 8) {
					if (board->pieces[i][column] == NULL) {
						//	putInCheck(row,column,x1,y1,board);
						if (!putInCheck(row, column, i, column, board)) {
							validMoves.insert(BP(i, column));

						}
					}else{
						break;
					}
				}
			}

		} else { //it's not the first move
			if (temp + 1 < 8)
				if (board->pieces[temp + 1][column] == NULL) { //it's an empty cell
					if (!putInCheck(row, column, temp + 1, column, board)) {
						validMoves.insert(BP(temp + 1, column));

					}
				}

		}
		return validMoves;
	} else {
		int temp = row;
		if (row == 6) { //it's white pawns first move
//			std::cout << "first move + white " << row << std::endl;
			for (int i = temp - 1; i > temp - 3; --i) {
				if (i > -1) {

					if (board->pieces[i][column] == NULL) {
						if (!putInCheck(row, column, i, column, board)) {
							validMoves.insert(BP(i, column));

						}
					}else{
						break;
					}
				}
			}

		} else { //it's not the first move

			if (temp - 1 > -1) {
				if (board->pieces[temp - 1][column] == NULL) {
					if (!putInCheck(row, column, temp - 1, column, board)) {
						validMoves.insert(BP(temp - 1, column));

					}
				}
			}
		}

		return validMoves;
	}

}

std::set<BP> Pawn::GetSpecial(int row, int column, Board* board) {
	string t = "BLACK";
	specialAttack.clear();
	//find moves for a black Pawn

	if (board->GetPiece(row, column)->getColor() == t) {

		int x1 = row + 1;
		int x2 = row + 1;
		int y1 = column + 1;
		int y2 = column - 1;
		if (x1 < 8 && y1 < 8 && x1 > -1 && y1 > -1) {
			if (board->pieces[x1][y1] != NULL) {
				if (board->pieces[x1][y1]->getColor() != t) {
						specialAttack.insert(BP(x1, y1));

				}

			}

		}
		if (x2 < 8 && y2 < 8 && x2 > -1 && y2 > -1) {
			if (board->pieces[x2][y2] != NULL) {
				if (board->pieces[x2][y2]->getColor() != t) {
						specialAttack.insert(BP(x2, y2));

				}

			}

		}

		return specialAttack;
		//find moves for the white Pawn
	} else {

		int x1 = row - 1;
		int x2 = row - 1;
		int y1 = column + 1;
		int y2 = column - 1;

		if (x1 < 8 && y1 < 8 && x1 > -1 && y1 > -1) {
			if (board->pieces[x1][y1] != NULL) {
				if (board->pieces[x1][y1]->getColor() == t) {
					specialAttack.insert(BP(x1, y1));

				}

			}

		}
		if (x2 < 8 && y2 < 8 && x2 > -1 && y2 > -1) {
			if (board->pieces[x2][y2] != NULL) {
				if (board->pieces[x2][y2]->getColor() == t) {
						specialAttack.insert(BP(x2, y2));


				}

			}

		}

		return specialAttack;

	}
}

ImageName Pawn::GetTypeE() {
	if (c == "WHITE") {
		return W_PAWN;
	} else {
		return B_PAWN;
	}
}

