#include "Board.h"

using namespace std;

Board::Board() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			pieces[i][j] = NULL;
		}
	}

}

Board::~Board() {
	ClearBoard();

}

void Board::CreateNewGame() {

	ClearBoard();

	pieces[7][0] = new Rook("WHITE");
	pieces[7][7] = new Rook("WHITE");
	pieces[7][1] = new Knight("WHITE");
	pieces[7][2] = new Bishop("WHITE");
	pieces[7][3] = new Queen("WHITE");
	pieces[7][4] = new King("WHITE");
	pieces[7][5] = new Bishop("WHITE");
	pieces[7][6] = new Knight("WHITE");

	for (int i = 0; i < 8; i++) {
		pieces[6][i] = new Pawn("WHITE");
		pieces[1][i] = new Pawn("BLACK");
	}

	pieces[0][0] = new Rook("BLACK");
	pieces[0][7] = new Rook("BLACK");
	pieces[0][1] = new Knight("BLACK");
	pieces[0][2] = new Bishop("BLACK");
	pieces[0][3] = new Queen("BLACK");
	pieces[0][4] = new King("BLACK");
	pieces[0][5] = new Bishop("BLACK");
	pieces[0][6] = new Knight("BLACK");

}

void Board::ClearBoard() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			delete pieces[i][j];
			pieces[i][j] = NULL;
		}
	}
}

std::string Board::GetPieceType(int row, int column) {
//	std::cout<<row<< " "<<column<<std::endl;
//	std::cout<<pieces[row][column]->GetType()<<std::endl;
	return pieces[row][column]->GetType();
}

//std::string Board::GetPieceColor(int row, int column) {
//	return pieces[row][column]->getColor();
//}

Piece* Board::GetPiece(int row, int column) {
	return pieces[row][column];
}

bool Board::isPiece(int row, int column) {
	if (pieces[row][column] != NULL) {
		return true;
	} else {
		return false;
	}
}

void Board::PrintBoard() {
//	cout<<pieces[0][0]->GetType()<<endl;
	cout << endl;

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			Piece * p = NULL;
			p = pieces[i][j];
			if (p == NULL) {
				cout << "-\t";
			} else if (p != NULL) {
				cout << p->GetType() << "\t";
			}
		}
		cout << endl;
	}
	cout << endl;

}

void Board::MovePiece(int old_row, int old_col, int row, int col) {
	//delete pieces[row][col];
	pieces[row][col] = pieces[old_row][old_col];
	pieces[old_row][old_col] = NULL;

}

void Board::RestorePiece(int old_row,int old_col,int n_row, int n_col, Piece * my,Piece * killed) {
	if(killed==NULL){
	//cout<<"here"<<endl;
	pieces[old_row][old_col] = my;
	pieces[n_row][n_col] = NULL;

	}else{
		pieces[old_row][old_col] = my;
			pieces[n_row][n_col] = killed;
	}
}

