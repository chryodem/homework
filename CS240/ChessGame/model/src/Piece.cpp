#include "Piece.h"
#include <string>
#include <iostream>
#include "Board.h"
#include "BP.h"
#include <list>

using namespace std;
void Piece::KingsPositionChecker(Board *& board, string & color, list<BP> & kingsPosition)
{
    bool found = false;
    for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			Piece * temp = board->GetPiece(i, j);
			if (temp != NULL) {
				if (temp->GetType() == "KING" && temp->getColor() == color) { //that's my kings coordinates
					kingsPosition.push_front(BP(i, j));
					found = true;
					break;

				}

			}
			if(found){
				break;
			}
		}
		if(found){
			break;
		}

	}
}

bool Piece::putInCheck(int cur_row, int cur_col, int row, int col,
		Board* board) {

	string color = board->GetPiece(cur_row, cur_col)->getColor(); //found my color


//	King.Print();
	Piece * killed = board->GetPiece(row, col);
	board->MovePiece(cur_row, cur_col, row, col); //move the piece to its calculated future position
	list<BP> kingsPosition;
    KingsPositionChecker(board, color, kingsPosition);
    BP King = kingsPosition.front();

	//now that piece is moved check the opposing teams attack moves to see if there's a king in it
	if (color == "WHITE") {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece * p = board->GetPiece(i, j);

				if (p != NULL) {
					if (p->getColor() == "BLACK") { //it's the opposing team list
						//get this piece's temp attack moves
						set<BP> tempMoves = p->GetSpecial(i, j, board);
						//now check if any of them matches kings position

						if (tempMoves.size() != 0) {
							set<BP>::iterator iter;
							for (iter = tempMoves.begin();
									iter != tempMoves.end(); ++iter) {
								if (King == *iter) {
									//cout<<"true here"<<endl;
									if (killed == NULL) {
										board->MovePiece(row, col, cur_row,
												cur_col);

									} else {
										board->MovePiece(row, col, cur_row,
												cur_col);
										board->pieces[row][col] = killed;
										killed=NULL;
									}
									return true;
								}
							}

						}

					}

				}

			}

		}
	} else {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece * p = board->GetPiece(i, j);
				if (p != NULL) {
					if (p->getColor() == "WHITE") { //it's the opposing team list
						//get this piece's temp attack moves
						set<BP> tempMoves = p->GetSpecial(i, j, board);
						//now check if any of them matches kings position

						if (tempMoves.size() != 0) {
							set<BP>::iterator iter;
							for (iter = tempMoves.begin();
									iter != tempMoves.end(); ++iter) {
								if (King == *iter) {
									if (killed == NULL) {
										board->MovePiece(row, col, cur_row,
												cur_col);

									} else {
										board->MovePiece(row, col, cur_row,
												cur_col);
										board->pieces[row][col] = killed;
										killed=NULL;
									}
									return true;
								}
							}

						}

					}

				}

			}

		}

	}
	if (killed == NULL) {
		board->MovePiece(row, col, cur_row, cur_col);

	} else {
		board->MovePiece(row, col, cur_row, cur_col);
		board->pieces[row][col] = killed;
		killed=NULL;
	}
	return false;
}
