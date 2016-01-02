/*
 * Queen.h
 *
 *  Created on: May 31, 2012
 *      Author: andrei
 */

#ifndef QUEEN_H_
#define QUEEN_H_

#include "Board.h"
#include "Piece.h"
#include "BP.h"
#include <string>

using namespace std;

class Queen: public Piece {

public:
	Queen(std::string c);
	virtual ~Queen();
	virtual std::set<BP> AttackMoves(int row, int column, Board * board);
	void Remove();
	bool isInCheck();
	virtual void CalcValidMoves();
	virtual std::set<BP> GetValidMoves(int row,int column, Board * board);
	virtual ImageName GetTypeE();
	virtual std::set<BP> GetSpecial(int row, int column, Board * board);

private:
	void GetDiagnalMoves(int x, int y, int row, int column, Board* board);
	void GetLineMoves(int& x, Board*& board, int& y, int& row, int& column);
	void DiagnalAttackBlack(int x, int y, int row, int column, Board* board,
			string t);
	void DiagnalAttacksWhite(int x, int y, int row, int column, Board* board,
			string t);
	void LinearAttackBlack(int& x, Board*& board, int& y, string& t, int& row,
			int& column);
	void LinearAttackWhite(int x, Board* board, int y, string t, int row,
			int column);
	void DiagnalSpecialBlack(int& x, int& y, int& row, int& column,
			Board*& board, string& t);
	void LinearSpecialBlack(int& x, Board*& board, int& y, string& t, int& row,
			int& column);
	void DiagnalSpecialWhite(int& x, int& y, int& row, int& column,
			Board*& board, string& t);
	void LinearSpecialWhite(int& x, Board*& board, int& y, string& t, int& row,
			int& column);
};

#endif /* QUEEN_H_ */
