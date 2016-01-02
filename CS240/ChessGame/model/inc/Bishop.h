/*
 * Bishop.h
 *
 *  Created on: Jun 1, 2012
 *      Author: andrei
 */

#ifndef BISHOP_H_
#define BISHOP_H_

#include "Piece.h"
#include "Board.h"
#include <string>
using namespace std;

class Bishop: public Piece {
public:
	Bishop(std::string c);
	virtual ~Bishop();
	virtual std::set<BP> AttackMoves(int row, int column, Board * board);
	void Remove();
	virtual bool isInCheck();
	virtual void CalcValidMoves();
	virtual std::set<BP> GetValidMoves(int row,int column, Board * board);
	virtual ImageName GetTypeE();
	virtual std::set<BP> GetSpecial(int row, int column, Board * board);

private:
	void GetMoves(int x, int y, Board* board, int row, int column);
	void AttackMovesBlack(int x, int y, int row, int column, Board* board,
			string t);
	void AttackMovesWhite(int x, int y, int row, int column, Board* board,
			string t);
    void SpecialAttackBlack(int row, int column, Board *board);
    void SpecialAttackBlack(int & x, int & y, int & row, int & column, Board *& board, string & t);
    void SpecialAttackWhite(int x, int y, int row, int column, Board *board, string t);

};

#endif /* BISHOP_H_ */
