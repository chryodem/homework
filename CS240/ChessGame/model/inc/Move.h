/*
 * Move.h
 *
 *  Created on: Jun 6, 2012
 *      Author: andrei
 */

#ifndef MOVE_H_
#define MOVE_H_


#include "Piece.h"
#include "ChessGuiDefines.h"
#include "StringUtil.h"
#include <sstream>

using namespace std;

class Move {

private:
	int old_row;
	int old_col;
	int n_row;
	int n_col;
//	ImageName myType;
//	ImageName captured;
	Piece * my;
	Piece * captured;
public:

	//Move(int o_row,int o_col,int row, int col, ImageName type );
	Move(int o_row,int o_col,int row, int col, Piece * piece );
	//Move(int old_row,int old_col,int row,int col, ImageName type, ImageName type1 );
	Move(int old_row,int old_col,int row,int col, Piece * piece, Piece * captur );
	virtual ~Move();
	int getOldRow(){
		return old_row;
	}
	int getOldCol(){
		return old_col;
	}
	int getNRow(){
		return n_row;
	}
	int getNCol(){
		return n_col;
	}
	Piece * getMy(){
		return my;
	}
	Piece * getCap(){
		return captured;
	}

	string ToXML();
};

 /* namespace std */
#endif /* MOVE_H_ */
