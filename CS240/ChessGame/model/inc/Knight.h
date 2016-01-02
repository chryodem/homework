/*
 * Knight.h
 *
 *  Created on: Jun 1, 2012
 *      Author: andrei
 */

#ifndef KNIGHT_H_
#define KNIGHT_H_

#include "Piece.h"
#include "Board.h"
#include <string>
using namespace std;

class Knight: public Piece {
public:
	Knight(std::string c);
	virtual ~Knight();
	virtual std::set<BP> AttackMoves(int row, int column, Board * board);
	void Remove();
	virtual bool isInCheck();
	virtual void CalcValidMoves();
	virtual std::set<BP>  GetValidMoves(int row,int column, Board * board);
	virtual ImageName GetTypeE();
	virtual std::set<BP> GetSpecial(int row, int column, Board * board);

private:
	void GetMoves(int row, int column, Board* board);
	void GetAttackMovesBlack(int& row, int& column, Board*& board, string& t);
	void GetAttackMovesWhite(int row, int column, Board* board, string t);
    void SpecialBlackMoves(int & row, int & column, Board *& board, string & t);
    void SpecialMovesWhite(int row, int column, Board *board, string t);
};

#endif /* KNIGHT_H_ */
