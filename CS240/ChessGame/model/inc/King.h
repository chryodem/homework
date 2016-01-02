/*
 * King.h
 *
 *  Created on: Jun 1, 2012
 *      Author: andrei
 */

#ifndef KING_H_
#define KING_H_

#include "Piece.h"
#include "Board.h"
#include <string>

using namespace std;

class King: public Piece {
public:
	King(std::string c);
	virtual ~King();
	virtual std::set<BP> AttackMoves(int row, int column, Board * board);
	void Remove();
	virtual bool isInCheck();
	virtual void CalcValidMoves();
	virtual std::set<BP>GetValidMoves(int row,int column, Board * board);
	virtual ImageName GetTypeE();
	virtual std::set<BP> GetSpecial(int row, int column, Board * board);

private:
	void GetMoves(int x, int y, Board* board, int row, int column);
	void GetBlackMoves(int x, int y, Board* board, string t, int row,
			int column);
	void GetWhiteMoves(int x, int y, Board* board, string t, int row,
			int column);
    void BlackMovesSpecial(int & x, int & y, Board *& board, string & t, int & row, int & column);
    void WhiteMovesSpecial(int x, int y, Board *board, string t, int row, int column);
};

#endif /* KING_H_ */
