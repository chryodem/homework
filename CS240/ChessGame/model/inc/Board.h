/*
 * Board.h
 *
 *  Created on: May 30, 2012
 *      Author: andrei
 */

#ifndef BOARD_H_
#define BOARD_H_

#include <vector>
#include <algorithm>
#include "Piece.h"
#include <iostream>
#include "Rook.h"
#include "Queen.h"
#include "King.h"
#include "Bishop.h"
#include "Knight.h"
#include "Pawn.h"
#include <string>

using namespace std;

class Board{
public:
	Board();
	~Board();
	void CreateNewGame();
	void ClearBoard();
	std::string GetPieceType(int row,int column);
	//std::string GetPieceColor(int row,int column);
	Piece * GetPiece(int row,int column);
	bool isPiece(int row,int column);
	void PrintBoard();
	void MovePiece(int old_row,int old_col,int row,int col);
	void RestorePiece(int old_row,int old_col,int n_row, int n_col, Piece * my,Piece * killed);

	Piece * pieces[8][8];
private:

};


#endif /* BOARD_H_ */
