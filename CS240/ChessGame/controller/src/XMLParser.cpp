/*
 * XMLParser.cpp
 *
 *  Created on: Jun 8, 2012
 *      Author: rybin
 */

#include "XMLParser.h"
#include "URLInputStream.h"
#include "CS240Exception.h"
#include <string>
#include "Model.h"
#include "Piece.h"
#include <sstream>

void XMLParser::StartParsing() {
	string temp_file = "file://";
	temp_file.append(file);

	try{

	URLInputStream input(temp_file);
	HTMLTokenizer tokenizer(&input);
	//skip all the board stuff
	while (tokenizer.HasNextToken()) {
		HTMLToken tok = tokenizer.GetNextToken();
		//cout<<tok.GetValue()<<endl;
//		cout << tok.GetAttribute("type") << tok.GetAttribute("color");
		if (tok.GetType() == TAG_END && tok.GetValue() == "board") {
			//cout<<"here"<<endl;
			break;
		}
	}

	int old_row;
	int old_col;
	int n_row;
	int n_col;
	//now parse through the moves
	while (tokenizer.HasNextToken()) {
		HTMLToken tok = tokenizer.GetNextToken();

		if (tok.GetType() == TAG_START && tok.GetValue() == "move") {
			bool done = false;
			while (!done) {
				tok = tokenizer.GetNextToken();
				if (tok.GetValue() != "piece" && tok.GetType() != TAG_START) {

					tok = tokenizer.GetNextToken();
					old_row = atoi(tok.GetAttribute("row").c_str());
					old_col = atoi(tok.GetAttribute("column").c_str());

					tok = tokenizer.GetNextToken();//end tag?
					tok = tokenizer.GetNextToken();//new piece tag
					n_row = atoi(tok.GetAttribute("row").c_str());
					n_col = atoi(tok.GetAttribute("column").c_str());

					model->MovePiece(old_row,old_col,n_row,n_col);

				}

				if (tok.GetType() != TAG_END && tok.GetValue() != "move") {
					done = true;

				}

			}

		}

	}
	}catch(exception & e){
		cout<<e.what()<<endl;
	}catch(...){
		cout<<"file doesn't exist"<<endl;
	}

}

XMLParser::XMLParser(string fileName, Model * m) :
	file(fileName) {
	model = m;
//	StartParsing();
}

XMLParser::~XMLParser() {
	// TODO Auto-generated destructor stub
}

void XMLParser::SaveFile() {
	ofstream fi(file.c_str());

	fi<<"<chessgame>\n";
	fi<<"<board>\n";
	fi<< "<piece type=\"rook\" color=\"black\" column=\"0\" row=\"0\"/>\n";
	fi<< "<piece color=\"black\" type=\"knight\" column=\"1\" row=\"0\"/>\n";
	fi<<"<piece type=\"bishop\" color=\"black\"  row=\"0\" column=\"2\"/>\n";
	fi<<"<piece row=\"0\" type=\"queen\" color=\"white\" column=\"7\"/>\n";
	fi<<"<piece column=\"0\" type=\"pawn\" color=\"black\" row=\"1\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"black\" column=\"1\" row=\"1\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"black\" column=\"2\" row=\"1\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"black\" column=\"3\" row=\"1\"/>\n";
	fi<<"<piece type=\"king\" color=\"black\" column=\"4\" row=\"1\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"black\" column=\"5\" row=\"1\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"black\" column=\"7\" row=\"1\"/>\n";
	fi<<"<piece type=\"knight\" color=\"black\" column=\"5\" row=\"2\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"white\" column=\"6\" row=\"3\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"white\" column=\"0\" row=\"6\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"white\" column=\"1\" row=\"6\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"white\" column=\"2\" row=\"6\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"white\" column=\"4\" row=\"6\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"white\" column=\"5\" row=\"6\"/>\n";
	fi<<"<piece type=\"pawn\" color=\"white\" column=\"6\" row=\"6\"/>\n";
	fi<<"<piece type=\"rook\" color=\"white\" column =\"0\" row=\"7\"/>\n";
	fi<<"<piece type=\"knight\" color=\"white\" column=\"1\" row=\"7\"/>\n";
	fi<<"<piece type=\"king\" color=\"white\" column=\"4\" row=\"7\"/>\n";
	fi<<"<piece type=\"bishop\" color=\"white\" column=\"5\" row=\"7\"/>\n";
	fi<<"<piece type=\"knight\" color=\"white\" column=\"6\" row=\"7\"/>\n";
	fi<<"<piece type=\"rook\" color=\"white\" column=\"7\" row=\"7\"/>\n";
	fi<<"</board>\n";

	fi<<"<history>\n";

	list<Move>::iterator it;

	list<Move> temp_moves = model->GetMoves();
//	cout<<temp_moves.size()<<endl;
	string temp="";

	while(temp_moves.size()!=0){
		temp+=temp_moves.back().ToXML();
		temp_moves.pop_back();
	}

	fi<<temp;

	fi<<"</history>\n";


	fi<<"</chessgame>\n";
	fi.close();

}

