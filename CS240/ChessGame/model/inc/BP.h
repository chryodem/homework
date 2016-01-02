/*
 * BoardPositionsSet.h
 *
 *  Created on: May 31, 2012
 *      Author: andrei
 */

#ifndef BP_H_
#define BP_H_

#include <iostream>
using namespace std;

class BP{
private:
	int x;
	int y;

public:
	BP(int column, int row){
		y= row;
		x = column;
	}
	~BP(){
		return;
	}

	int GetRow()const{
		return x;
	}
	int GetColumn()const{
		return y;
	}

	bool operator < (const BP & other)const{
		int a = y*8 +x;
		int b = other.y * 8 +other.x;
		return a<b;
	}

	bool operator ==(const BP & other)const{
		if(x==other.GetRow()&&y==other.GetColumn()){
			return true;
		}else{
			return false;
		}
	}

	void Print(){
		cout<<"row "<< x << " column " << y<<endl;
	}

};


#endif /* BP_H_ */
