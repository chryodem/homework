/*
 * Piece.h
 *
 *  Created on: May 30, 2012
 *      Author: andrei
 */

#ifndef PIECE_H_
#define PIECE_H_

class Board;
#include <string>
#include <iostream>
#include <set>
#include "BP.h"
#include "ChessGuiDefines.h"
#include <string>
#include <list>

class Piece{
public:
	Piece(std::string type,std::string color){
		t=type;
		c =color;

	}
	virtual ~Piece(){}
//	virtual void Moves()=0;
	virtual std::set<BP> AttackMoves(int row, int column, Board * board)=0;
	virtual std::set<BP> GetSpecial(int row, int column, Board * board)=0;
    virtual bool putInCheck(int cur_row,int cur_col,int row,int col, Board * board); //need to implement it here


	virtual void Remove()=0;
	virtual bool isInCheck()=0;
	virtual void CalcValidMoves()=0;
	virtual std::string GetType(){
		return t;
	}
	virtual std::string getColor(){
		return c;
	}
	virtual std::set<BP> GetValidMoves(int row,int column, Board * board)=0;

	virtual ImageName GetTypeE()=0;


	std::string t;
	std::string c;
	std::set<BP> validMoves;
	std::set<BP> attackMoves;
	std::set<BP> specialAttack;
private:
	void KingsPositionChecker(Board *& board, string & color, list<BP> & kingsPosition);
    void FindKingsPosition(Board*& board, string color, list<BP> kingsPosition);

};


#endif /* PIECE_H_ */
