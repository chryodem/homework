/*
 * Model.h
 *
 *  Created on: May 30, 2012
 *      Author: andrei
 */

//

#ifndef MODEL_H_
#define MODEL_H_

#include "Board.h"
#include "Controller.h"
#include <list>
#include "Move.h"
#include <string>
class XMLParser;
//#include "XMLParser.h"
class Piece;//to avoid circular pound include
class IChessPlayer;

using namespace std;

class Model{
public:

	Model();

	~Model();

	std::string GetPieceType(int row,int column);

	//std::string GetPieceColor(int row,int column);

	void StartGame();

	Piece * GetPiece(int row,int column);

	Board * GetBoard();

	void MovePiece(int old_row,int old_col,int row,int col);

//	void ReadFile(string fileName);

	list<Move> & GetMoves();




private:
	Board * board;
	Controller * controller;
	string next;
	list<Move> moves;
	void CheckBoardBeforeMove(int& row, int& col, int& old_row, int& old_col);

};



#endif /* MODEL_H_ */
