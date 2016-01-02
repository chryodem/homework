/*
 * Controller.h
 *
 *  Created on: May 30, 2012
 *      Author: andrei
 */

#ifndef CONTROLLER_H_
#define CONTROLLER_H_

#include "IChessController.h"
#include <iostream>
#include "Piece.h"
#include "Board.h"
#include "BP.h"
#include <set>
#include <string>
#include "Move.h"
#include <list>
#include "XMLParser.h"
//#include "IChessPlayer.h"

using namespace std;
class Model;

class Controller: public IChessController {
public:
	Controller(int p1, int p2);
	virtual ~Controller();
    virtual void on_CellSelected(int row, int col, int button);
    virtual void on_DragStart(int row, int col);
    virtual bool on_DragEnd(int row, int col);
    virtual void on_NewGame();
    virtual void on_SaveGame();
    virtual void on_SaveGameAs();
    virtual void on_LoadGame();
    virtual void on_UndoMove();
    virtual void on_QuitGame();
    virtual void on_TimerEvent();
    virtual void SetView(IChessView *view);
    void SetModel(Model *m);
private:
    IChessView *view;
    int column;
    int row;
    int button;
    Model *model;
    bool highlighted;
    Piece *piece;
    Board *board;
    std::set<BP> validMoves;
    std::set<BP> attackMoves;
    int old_row;
    int old_col;
    void ShowValidMoves(int row, int col);
    void ShowAttackMoves(int row, int col);
    void UnhighlightSquares();
    void DrawPieces();
    void FindKingsPos(string color);
    void FindIfCheck(string & myColor);
    void FindIfStillMate(string & myColor);
    void PerformChecking();
	string whereToSave;
	list<Move> moves;
	bool inCheck;
	bool whitesTurn;
	bool stillMate;
	bool checkMate;
	list<BP> kingsPos;
	int p1;
	int p2;
};

#endif /* CONTROLLER_H_ */
