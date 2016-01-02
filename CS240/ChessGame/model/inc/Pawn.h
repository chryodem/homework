/*
 * Pawn.h
 *
 *  Created on: Jun 1, 2012
 *      Author: andrei
 */

#ifndef PAWN_H_
#define PAWN_H_

#include "Piece.h"
#include "Board.h"

class Pawn: public Piece {
public:
	Pawn(std::string c);
	virtual ~Pawn();
	virtual std::set<BP> AttackMoves(int row, int column, Board * board);
	void Remove();
	bool isInCheck();
	virtual void CalcValidMoves();
	virtual std::set<BP> GetSpecial(int row, int column, Board * board);

	virtual std::set<BP> GetValidMoves(int row,int column, Board * board);
	virtual ImageName GetTypeE();
};

#endif /* PAWN_H_ */
