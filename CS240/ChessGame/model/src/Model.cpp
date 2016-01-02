#include "Model.h"
#include "Piece.h"
#include "XMLParser.h"

Model::Model() {
	board = new Board();
}

Model::~Model() {
	delete board;

	list<Move>::iterator it;
//	for(it=moves.begin();it!=moves.end();++it){
//		delete it->getMy();
//		delete it->getCap();
//	}
}

std::string Model::GetPieceType(int row, int column) {
	return board->GetPieceType(row, column);

}

//std::string Model::GetPieceColor(int row, int column) {
//	return board->GetPieceColor(row, column);
//}

void Model::StartGame() {
	board->CreateNewGame();
}

Piece* Model::GetPiece(int row, int column) {
	return board->GetPiece(row, column);
}

Board* Model::GetBoard() {
	return board;
}

void Model::CheckBoardBeforeMove(int& row, int& col, int& old_row, int& old_col) {
	if (board->pieces[row][col] == NULL) { //moving to an empty spot
		moves.push_front(Move(old_row, old_col, row, col, board->GetPiece(
				old_row, old_col)));
	} else { //else attacking a piece
		moves.push_front(Move(old_row, old_col, row, col, board->GetPiece(
				old_row, old_col), board->GetPiece(row, col)));
	}
}

void Model::MovePiece(int old_row, int old_col, int row, int col) {
	if (moves.size() == 0) {
		Piece * p;
		p = board->GetPiece(old_row, old_col);
		if (p != NULL) {
			if (p->getColor() == "WHITE") {
				CheckBoardBeforeMove(row, col, old_row, old_col);
				board->MovePiece(old_row, old_col, row, col);

			}

		}
	} else { //moves' size !=0
		Piece * temp = moves.front().getMy();
		Piece * p;
		p = board->GetPiece(old_row, old_col);
		if (temp->getColor() == "WHITE") { //if last was white now it's black's turn
			if (p != NULL) {
				if (p->getColor() == "BLACK") {
					CheckBoardBeforeMove(row, col, old_row, old_col);
					board->MovePiece(old_row, old_col, row, col);

				}

			}
		} else {
			if (p != NULL) {
				if (p->getColor() == "WHITE") {
					CheckBoardBeforeMove(row, col, old_row, old_col);
					board->MovePiece(old_row, old_col, row, col);

				}

			}
		}
	}

}

list<Move>& Model::GetMoves() {
	return moves;
}

//void Model::ReadFile(string fileName){
//	XMLParser tok(fileName);
//}

