/*
 * Controller.cpp
 *
 *  Created on: May 29, 2012
 *      Author: andrei
 */

#include "Controller.h"
#include "Model.h"
#include <iostream>

using namespace std;

Controller::Controller(int pl1, int pl2) :
	old_row(0), old_col(0) {
	highlighted = false;
	model = new Model();
	p1 = pl1;
	p2 = pl2;
	whitesTurn = true;

}

Controller::~Controller() {
	delete model;
}

void Controller::UnhighlightSquares() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			view->UnHighlightSquare(i, j);
		}
	}

}

void Controller::ShowAttackMoves(int row, int col) {
	set<BP> Attack = piece->AttackMoves(row, col, board);
	if (Attack.size() != 0) {
		set<BP>::iterator i;
		for (i = Attack.begin(); i != Attack.end(); ++i) {
			view->UnHighlightSquare(i->GetRow(), i->GetColumn());
			view->HighlightSquare(i->GetRow(), i->GetColumn(), BLUE_SQUARE);
			validMoves.insert(BP(i->GetRow(), i->GetColumn()));
		}

	}
}

void Controller::FindKingsPos(string color) {
	kingsPos.clear();
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			if (board->GetPiece(i, j) != NULL) {
				if (board->GetPiece(i, j)->GetType() == "KING"
						&& board->GetPiece(i, j)->getColor() != color) {//it's the other king
					kingsPos.push_front(BP(i, j));

					return;
				}

			}
		}

	}

}

void Controller::ShowValidMoves(int row, int col) {
	set<BP> Valid = piece->GetValidMoves(row, col, board);
	old_row = row;
	old_col = col;
	validMoves = Valid;
	set<BP>::iterator it;
	view->HighlightSquare(row, col, RED_SQUARE);

	//check here the set as if the piece was to move to any of these positions,
	//would that put my king in check?
	//	CheckForCheck(it, Valid, row, col);
	if (Valid.size() != 0) {
		for (it = Valid.begin(); it != Valid.end(); ++it) {
			view->HighlightSquare(it->GetRow(), it->GetColumn(), YELLOW_SQUARE);
		}

	}

}

void Controller::FindIfCheck(string & myColor) {
	inCheck = false;
	stillMate = false;

	FindKingsPos(myColor);
	BP kingsP = kingsPos.front();
	kingsPos.clear();
	//iterate over pieces of my color

	Piece * temp;

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			temp = board->GetPiece(i, j);
			if (temp != NULL) {
				if (temp->getColor() == myColor) {//it's one of my pieces

					set<BP> tempAttackMoves = temp->GetSpecial(i, j, board);//find this pieces attack moves
					set<BP>::iterator it;
					//go over this moves set
					if (tempAttackMoves.size() != 0) {
						for (it = tempAttackMoves.begin(); it
								!= tempAttackMoves.end(); ++it) {
							if (kingsP == *it) {
								inCheck = true;
								return;
							}
						}

					}

				}
			}
		}
	}

}

void Controller::FindIfStillMate(string & myColor) {
	Piece *temp;
	stillMate = false;
	bool stillMoves = false;
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			temp = board->GetPiece(i, j);
			if (temp != NULL) { //if it's not null
				if (temp->getColor() != myColor) { //if it's a piece of another color
					set<BP> tempMoves = temp->GetValidMoves(i, j, board);
					if (tempMoves.size() != 0) {
						stillMoves = true;
					}
					set<BP> tempAttacks = temp->AttackMoves(i, j, board);
					if (tempAttacks.size() != 0) {
						stillMoves = true;
					}
				}

			}
		}
	}
	if (!stillMoves) {
		stillMate = true;
	}
}

void Controller::PerformChecking() {
	//check for check
	//check for stillmate
	string myColor;
	if (model->GetMoves().front().getMy()->getColor() == "WHITE") {
		//use this to know whose turn it was
		myColor = "WHITE"; //my color is white check if any of my moves
		FindIfCheck(myColor);
		FindIfStillMate(myColor);
		view->SetBottomLabel("BLACK's turn");
		whitesTurn = false;
		if (stillMate && inCheck) {
			view->SetTopLabel("BLACK's in CHECK MATE");
		} else if (stillMate) {
			view->SetTopLabel("BLACK's in STILL MATE");
		} else if (inCheck) {
			view->SetTopLabel("BLACK's in CHECK!");
		} else {
			view->SetTopLabel("");
		}

	} else {
		myColor = "BLACK";
		FindIfCheck(myColor);
		FindIfStillMate(myColor);
		view->SetBottomLabel("WHITE's turn");
		whitesTurn = true;
		if (stillMate && inCheck) {
			view->SetTopLabel("WHITE's in CHECK MATE");
		} else if (stillMate) {
			view->SetTopLabel("WHITE's in STILL MATE");
		} else if (inCheck) {
			view->SetTopLabel("WHITE's in CHECK!");
		} else {
			view->SetTopLabel("");
		}

	}

}

void Controller::on_CellSelected(int row, int col, int button) {
	UnhighlightSquares();

	if (validMoves.size() != 0) {
		set<BP>::iterator iter;
		for (iter = validMoves.begin(); iter != validMoves.end(); ++iter) {
			if (row == iter->GetRow() && col == iter->GetColumn()) {
				if (old_row != row || old_col != col) {
					model->MovePiece(old_row, old_col, row, col);
					PerformChecking();
				}
				break;
			}
		}
		validMoves.clear();
		DrawPieces();
		return;
	}

	piece = NULL;
	piece = model->GetPiece(row, col);
	if (piece != NULL) {

		if (model->GetMoves().size() == 0) {
			if (piece->getColor() == "WHITE") {

				//view->SetBottomLabel("WHITE's turn");
				ShowValidMoves(row, col);
				ShowAttackMoves(row, col); //used to show attack moves
			}
		} else {
			Piece * temp = model->GetMoves().front().getMy();
			if (temp->getColor() != piece->getColor()) {
				//view->SetBottomLabel(piece->getColor()+ "'s turn");
				ShowValidMoves(row, col);
				ShowAttackMoves(row, col); //used to show attack moves

			}

		}

	}

}

void Controller::on_DragStart(int row, int col) {
}

bool Controller::on_DragEnd(int row, int col) {
}

void Controller::DrawPieces() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			view->ClearPiece(i, j);
			piece = NULL;
			piece = model->GetPiece(i, j);
			if (piece != NULL) {
				view->PlacePiece(i, j, piece->GetTypeE());
			}
		}
	}
}

void Controller::on_NewGame() {
	UnhighlightSquares();
	inCheck = false;
	whitesTurn = true;
	checkMate = false;
	stillMate = false;
	model->StartGame();
	model->GetMoves().clear();
	board = model->GetBoard();
	DrawPieces();

	view->SetBottomLabel("WHITE's turn");

}

void Controller::on_SaveGame() {
	whereToSave = view->SelectSaveFile();
	XMLParser parser(whereToSave, model);
	parser.SaveFile();

}

void Controller::on_SaveGameAs() {

}

void Controller::on_LoadGame() {

	string loaded_file = view->SelectLoadFile();
	//model->ReadFile(loaded_file);
	if (!loaded_file.empty()) {
		inCheck = false;
		whitesTurn = true;
		checkMate = false;
		stillMate = false;
		model->StartGame();
		model->GetMoves().clear();
		XMLParser parser(loaded_file, model);
		parser.StartParsing();
		DrawPieces();
		UnhighlightSquares();

	}
}

void Controller::on_UndoMove() {
	UnhighlightSquares();
	if (model->GetMoves().size() == 0) {
		view->SetTopLabel("Nothing to undo");
		view->SetBottomLabel("WHITE's turn");

	} else {
		view->SetTopLabel(" ");
		Move temp = model->GetMoves().front();

		int row;
		int col;
		int n_row;
		int n_col;

		row = temp.getOldRow();
		col = temp.getOldCol();
		n_row = temp.getNRow();
		n_col = temp.getNCol();

		board->RestorePiece(row, col, n_row, n_col, temp.getMy(), temp.getCap());
		model->GetMoves().pop_front();
		DrawPieces();
	}
}

void Controller::on_QuitGame() {
}

void Controller::on_TimerEvent() {
	if ((p1 == 0 && p2 == 0) || (p1 == 1 && p2 == 1)) {
		//do nothing invalid arguments, or just human players
	}
	if (p2 == 1 || p1 == 1) {

	}
	if (p2 == 2 && !whitesTurn) {
		string color = "BLACK";

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				piece = model->GetPiece(i, j);
				if (piece != NULL) {
					if (piece->getColor() == color) {

						ShowValidMoves(i, j);
						ShowAttackMoves(i, j);

						set<BP> temp_attacks = piece->AttackMoves(i, j, board);
						if (temp_attacks.size() != 0) {
							set<BP>::iterator it;

							it = temp_attacks.begin();
							//view->HighlightSquare(it->GetRow(),it->GetColumn(),YELLOW_SQUARE);
							model->MovePiece(i, j, it->GetRow(),
									it->GetColumn());
							PerformChecking();
							UnhighlightSquares();
							DrawPieces();
							return;
						} else {
							set<BP> temp_moves = piece->GetValidMoves(i, j,
									board);
							set<BP>::iterator it;
							if (temp_moves.size() != 0) {
								it = temp_moves.begin();

								model->MovePiece(i, j, it->GetRow(),
										it->GetColumn());
								PerformChecking();
								UnhighlightSquares();
								DrawPieces();
								return;

							}
						}
					}
				}
			}
		}

	}
	if (p1 == 2 && whitesTurn) {
		string color = "WHITE";
		//		int i = rand()%8;
		//		int j = rand()%8;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				piece = model->GetPiece(i, j);
				if (piece != NULL) {
					if (piece->getColor() == color) {

						//	ShowValidMoves(i, j);
						//	ShowAttackMoves(i, j);

						set<BP> temp_attacks = piece->AttackMoves(i, j, board);
						if (temp_attacks.size() != 0) {
							set<BP>::iterator it;

							it = temp_attacks.begin();
							//view->HighlightSquare(it->GetRow(),it->GetColumn(),YELLOW_SQUARE);
							model->MovePiece(i, j, it->GetRow(),
									it->GetColumn());
							PerformChecking();
							UnhighlightSquares();
							DrawPieces();
							return;
						} else {
							set<BP> temp_moves = piece->GetValidMoves(i, j,
									board);
							set<BP>::iterator it;
							if (temp_moves.size() != 0) {
								it = temp_moves.begin();

								model->MovePiece(i, j, it->GetRow(),
										it->GetColumn());
								PerformChecking();
								UnhighlightSquares();
								DrawPieces();
								return;

							}
						}
					}
				}
			}
		}

	}

}

void Controller::SetView(IChessView* v) {
	view = v;
}

void Controller::SetModel(Model* m) {
	model = m;
}
