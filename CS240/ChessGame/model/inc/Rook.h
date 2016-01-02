/*
 * Rook.h
 *
 *  Created on: May 31, 2012
 *      Author: andrei
 */

#ifndef ROOK_H_
#define ROOK_H_

#include "Board.h"
#include "Piece.h"
#include "BP.h"
#include <string>

using namespace std;

class Rook: public Piece {

public:
	Rook(std::string color);
	virtual ~Rook();
	virtual std::set<BP> AttackMoves(int row, int column, Board * board);
	void Remove();
	virtual bool isInCheck();
	virtual void CalcValidMoves();
	virtual std::set<BP> GetValidMoves(int row,int column, Board * board);
	virtual ImageName GetTypeE();
	virtual std::set<BP> GetSpecial(int row, int column, Board * board);

private:
	void GetMoves(int x, Board* board, int y, int row, int column);
	void BlackAttackMoves(int x, Board* board, int y, string t, int row,
			int column);
	void WhiteAttackMoves(int x, Board* board, int y, string t, int row,
			int column);
	void BlackSpecial(int x, Board* board, int y, string t, int row,
			int column);
	void WhiteSpecial(int x, Board* board, int y, string t, int row,
			int column);
};

#endif /* ROOK_H_ */
