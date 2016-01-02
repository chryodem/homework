/*
 * Move.cpp
 *
 *  Created on: Jun 6, 2012
 *      Author: andrei
 */

#include "Move.h"

//Move::Move(int o_row,int o_col,int row,int col, ImageName type) {
//	old_row=o_row;
//	old_col=o_col;
//	n_row = row;
//	n_col = col;
//	myType = type;
//}

Move::Move(int o_row, int o_col, int row, int col, Piece * piece) {
	old_row = o_row;
	old_col = o_col;
	n_row = row;
	n_col = col;
	my = piece;
	captured = NULL;
}

Move::Move(int o_row, int o_col, int row, int col, Piece * piece,
		Piece * captur) {
	old_row = o_row;
	old_col = o_col;
	n_row = row;
	n_col = col;
	my = piece;
	captured = captur;
}

//Move::Move(int o_row,int o_col,int row,int col, ImageName type, ImageName type1){
//		old_row=o_row;
//		old_col=o_col;
//		n_row = row;
//		n_col = col;
//		myType = type;
//		captured = type1;
//
//}

Move::~Move() {
	// TODO Auto-generated destructor stub
}

string Move::ToXML() {
	string result = "";
	result += ("<move>\n");

	result += ("<piece ");
	result += ("type=");
	string type = my->GetType();
	StringUtil::ToLower(type);
	result += ("\"" + type + "\" ");
	string color = my->getColor();
	StringUtil::ToLower(color);
	result += ("color=");
	result += ("\"" + color + "\" ");
	stringstream s_old_col;
	result += ("column=");
	s_old_col << old_col;
	result += ("\"" + s_old_col.str() + "\" ");
	stringstream s_old_row;
	result += ("row=");
	s_old_row << old_row;
	result += ("\"" + s_old_row.str() + "\" ");
	result += ("/>\n");

	result += ("<piece ");
	result += ("type=");
	type = my->GetType();
	StringUtil::ToLower(type);
	result += ("\"" + type + "\" ");
	color = my->getColor();
	StringUtil::ToLower(color);
	result += ("color=");
	result += ("\"" + color + "\" ");
	stringstream s_n_col;
	result += ("column=");
	s_n_col << n_col;
	result += ("\"" + s_n_col.str() + "\" ");
	stringstream s_n_row;
	result += ("row=");
	s_n_row << n_row;
	result += ("\"" + s_n_row.str() + "\" ");
	result += ("/>\n");

	if (captured != NULL) {
		result += ("<piece ");
		result += ("type=");
		type = captured->GetType();
		StringUtil::ToLower(type);
		result += ("\"" + type + "\" ");
		color = captured->getColor();
		StringUtil::ToLower(color);
		result += ("color=");
		result += ("\"" + color + "\" ");
		//stringstream s_n_col;
		result += ("column=");
		//s_n_col << n_col;
		result += ("\"" + s_n_col.str() + "\" ");
		//stringstream s_n_row;
		result += ("row=");
		//s_n_row << n_row;
		result += ("\"" + s_n_row.str() + "\" ");
		result += ("/>\n");

	}

	result+=("</move>\n");

	return result;
}

/* namespace std */
